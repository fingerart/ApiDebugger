package io.chengguo.api.debugger.lang.documentation;

import com.intellij.lang.documentation.DocumentationProviderEx;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.PsiWhiteSpace;
import io.chengguo.api.debugger.lang.ApiPsiFile;
import io.chengguo.api.debugger.lang.ApiPsiUtil;
import io.chengguo.api.debugger.lang.lexer.ApiTokenTypes;
import io.chengguo.api.debugger.lang.psi.*;
import io.chengguo.api.debugger.ui.header.HttpHeaderDocumentation;
import io.chengguo.api.debugger.ui.header.HttpHeadersDictionary;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;

public class ApiHeaderDocumentationProvider extends DocumentationProviderEx {
    @Nullable
    @Override
    public List<String> getUrlFor(PsiElement element, PsiElement originalElement) {
        HttpHeaderDocumentation documentation = getDocument(element);
        return documentation != null ? Collections.singletonList(documentation.getUrl()) : null;
    }

    @Nullable
    @Override
    public String generateDoc(PsiElement element, @Nullable PsiElement originalElement) {
        HttpHeaderDocumentation documentation = getDocument(element);
        return documentation != null ? documentation.generateDoc() : null;
    }

    @Nullable
    private HttpHeaderDocumentation getDocument(PsiElement element) {
        if (element instanceof ApiHeaderField) {
            String key = ((ApiHeaderField) element).getKey();
            return HttpHeadersDictionary.getDocumentation(key);
        }
        return null;
    }

    @Nullable
    @Override
    public PsiElement getDocumentationElementForLookupItem(PsiManager psiManager, Object object, PsiElement element) {
        if (!(object instanceof HttpHeaderDocumentation)) return null;
        Project project = psiManager != null ? psiManager.getProject() : null;
        String name = ((HttpHeaderDocumentation) object).getName();
        if (StringUtil.isEmpty(name) || project == null) {
            return element;
        }
        PsiFile dummyFile = ApiPsiGenerator.createDummyFile(project, "- title: localhost\nGET 127.0.0.1\n" + name);
        ApiRequest apiRequest = ApiPsiUtil.findFirstApiRequest(dummyFile);
        ApiHeaderField headerField = apiRequest != null ? apiRequest.getFirstHeader() : null;
        assert headerField != null;
        return headerField;
    }

    @Nullable
    @Override
    public PsiElement getCustomDocumentationElement(@NotNull Editor editor, @NotNull PsiFile file, @Nullable PsiElement contextElement) {
        if (file instanceof ApiPsiFile && contextElement != null) {
            while (contextElement instanceof PsiWhiteSpace || (contextElement != null && ApiPsiUtil.isOfType(contextElement, ApiTypes.Api_COLON))) {
                contextElement = contextElement.getPrevSibling();
            }
            if (contextElement != null && ApiPsiUtil.isOfTypes(contextElement, ApiTokenTypes.HEADER)) {
                contextElement = contextElement.getParent();
            }
            if (contextElement instanceof ApiHeaderFieldKey || contextElement instanceof ApiHeaderFieldVal) {
                return contextElement.getParent();
            }
        }

        return null;
    }
}
