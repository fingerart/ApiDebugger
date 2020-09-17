package io.chengguo.api.debugger.lang;

import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.util.Pair;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vfs.VfsUtilCore;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileManager;
import com.intellij.psi.PsiFile;
import com.intellij.psi.impl.source.resolve.reference.impl.providers.FileReferenceUtil;
import com.intellij.util.LineSeparator;
import com.intellij.util.PathUtil;
import com.intellij.util.containers.ContainerUtil;
import io.chengguo.api.debugger.lang.psi.*;
import io.chengguo.api.debugger.lang.replacer.ApiVariableReplacer;
import io.chengguo.api.debugger.lang.run.ApiRequestInvalidException;
import io.chengguo.api.debugger.ui.ApiDebuggerRequest;
import io.chengguo.api.debugger.ui.ApiFormBodyPart;
import org.apache.http.entity.ContentType;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.intellij.util.io.URLUtil.FILE_PROTOCOL;
import static io.chengguo.api.debugger.constants.HeaderFields.*;

/**
 * ApiBlock实体与Psi之间的转换
 */
public class ApiBlockConverter {

    public static ApiDebuggerRequest toApiBlock(ApiApiBlock element, ApiVariableReplacer replacer) throws ApiRequestInvalidException {
        ApiRequest requestElement = element.getRequest();
        ApiRequestLine reqLineElement = requestElement.getRequestLine();
        ApiRequestTarget reqTargetElement = reqLineElement.getRequestTarget();
        ApiDebuggerRequest request = new ApiDebuggerRequest(reqLineElement.getMethod().getText(), reqTargetElement.getUrl(replacer));
        request.addParameter(pairToMap(reqTargetElement.getParameters(replacer)));
        request.addAllHeader(pairToMap(requestElement.getHeaders(replacer)));
        ApiBodyMessageElement bodyMessageElement = requestElement.getBody();

        if (bodyMessageElement instanceof ApiRequestMessageGroup) {
            List<ApiRequestMessageElement> requestMessages = ((ApiRequestMessageGroup) bodyMessageElement).getRequestMessageList();
            if (!requestMessages.isEmpty()) {
                if (requestMessages.size() == 1 && requestMessages.get(0) instanceof ApiInputFile) {
                    // 单个文件，记录文件的路径
                    String filePath = PathUtil.toSystemIndependentName(getFileToSend(((ApiInputFile) requestMessages.get(0)), replacer).getPath());
                    request.setFileToSend(filePath);
                } else {
                    // 文件和文本的混合，将其解析为文本
                    String text = getTextToSend(element.getContainingFile(), requestMessages, replacer);
                    request.setTextToSend(text);
                }
            }
        } else if (bodyMessageElement instanceof ApiMultipartMessage) {
            request.setMultipartBoundary(getMultipartBoundary(requestElement, replacer));
            request.setFormBody(getFormBodyParts(((ApiMultipartMessage) bodyMessageElement), replacer));
        }
        return request;
    }

    private static List<ApiFormBodyPart> getFormBodyParts(ApiMultipartMessage multipartMessage, ApiVariableReplacer replacer) throws ApiRequestInvalidException {
        List<ApiFormBodyPart> result = ContainerUtil.newSmartList();
        List<ApiMultipartField> multipartFields = multipartMessage.getMultipartFieldList();
        for (ApiMultipartField field : multipartFields) {
            ApiFormBodyPart formBodyPart;
            ApiHeaderField contentDispositionField = field.getContentDispositionField();
            String index = String.valueOf(multipartFields.indexOf(field));
            String fieldName = contentDispositionField != null ? contentDispositionField.getHeaderValueItem(MULTIPART_NAME, replacer) : index;
            fieldName = StringUtil.defaultIfEmpty(fieldName, index);
            String fileName = contentDispositionField != null ? contentDispositionField.getHeaderValueItem(MULTIPART_FILENAME, replacer) : null;
            ContentType contentType = field.getContentType(replacer);
            if (StringUtil.isNotEmpty(fileName)) {
                File file = getFileToUpload(field, replacer);
                formBodyPart = ApiFormBodyPart.create(fieldName, contentType, file);
            } else {
                String content = getTextToSend(field.getContainingFile(), field.getRequestMessages(), replacer);
                formBodyPart = ApiFormBodyPart.create(fieldName, contentType, content);
            }
            for (ApiHeaderField headerField : field.getHeaderFieldList()) {
                formBodyPart.addHeader(headerField.getKey(replacer), headerField.getValue(replacer));
            }
            result.add(formBodyPart);
        }
        return result;
    }

    private static File getFileToUpload(ApiMultipartField field, ApiVariableReplacer replacer) throws ApiRequestInvalidException {
        List<ApiRequestMessageElement> requestMessages = field.getRequestMessages();
        if (requestMessages.size() == 1 && requestMessages.get(0) instanceof ApiInputFile) {
            // 单个文件
            return getFileToSend((ApiInputFile) requestMessages.get(0), replacer);
        }else {
            // 多个文件
        }
        return null;
    }

    private static String getTextToSend(PsiFile psiFile, List<ApiRequestMessageElement> messages, ApiVariableReplacer replacer) throws ApiRequestInvalidException {
        String lineSeparator = (psiFile == null || messages.size() < 2) ? LineSeparator.getSystemLineSeparator().getSeparatorString() : FileDocumentManager.getInstance().getLineSeparator(psiFile.getVirtualFile(), psiFile.getProject());
        StringBuilder sb = new StringBuilder();
        // TODO 支持变量替换
        for (ApiRequestMessageElement message : messages) {
            if (message instanceof ApiMessageBody) {
                sb.append(((ApiMessageBody) messages).getText());
            } else if (message instanceof ApiInputFile) {
                sb.append(loadFileContent(resolveFileUrl((ApiInputFile) message)));
            }
            if (ContainerUtil.getLastItem(messages) != message) {
                sb.append(lineSeparator);
            }
        }
        return sb.toString();
    }

    private static File getFileToSend(ApiInputFile element, ApiVariableReplacer replacer) throws ApiRequestInvalidException {
        String url = resolveFileUrl(element);
        // TODO 当文件是一个文本文件时，需要替换其内部的变量，再保存在临时目录下
        String path = VfsUtilCore.urlToPath(url);
        return new File(path);
    }

    @NotNull
    private static String resolveFileUrl(ApiInputFile element) throws ApiRequestInvalidException {
        ApiFilePath filePathElement = element.getFilePath();
        if (filePathElement == null) {
            throw new ApiRequestInvalidException("文档中未定义路径");
        }
        PsiFile file = FileReferenceUtil.findFile(filePathElement);
        if (file != null) {
            return file.getVirtualFile().getUrl();
        }
        String path = filePathElement.getText();
        if (FileUtil.isAbsolutePlatformIndependent(path)) {
            return VfsUtilCore.pathToUrl(path);
        }
        throw new ApiRequestInvalidException("找不到定义的文件: " + path);
    }

    private static Map<String, String> pairToMap(List<Pair<String, String>> pairs) {
        HashMap<String, String> result = new HashMap<>();
        for (Pair<String, String> pair : pairs) {
            result.put(pair.first, pair.second);
        }
        return result;
    }

    public static String loadFileContent(String url) throws ApiRequestInvalidException {
        try {
            String protocol = VirtualFileManager.extractProtocol(url);
            if (!FILE_PROTOCOL.equals(protocol)) {
                VirtualFile virtualFile = VirtualFileManager.getInstance().findFileByUrl(url);
                if (virtualFile != null) {
                    return VfsUtilCore.loadText(virtualFile);
                }
            }
            return FileUtil.loadFile(new File(PathUtil.toSystemDependentName(VfsUtilCore.urlToPath(url))));
        } catch (IOException e) {
            throw new ApiRequestInvalidException(e.getMessage());
        }
    }

    private static String getMultipartBoundary(@NotNull ApiRequest request, ApiVariableReplacer replacer) {
        ApiHeaderField contentTypeField = request.getHeaderField(CONTENT_TYPE);
        return contentTypeField != null ? contentTypeField.getHeaderValueItem(MULTIPART_BOUNDARY, replacer) : null;
    }

}
