package io.chengguo.api.debugger.lang.psi;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileFactory;
import io.chengguo.api.debugger.lang.ApiFileType;
import io.chengguo.api.debugger.lang.ApiPsiUtils;
import org.jetbrains.annotations.NotNull;

public final class ApiPsiGenerator {

    private ApiPsiGenerator() {
    }

    public static ApiVariable createVariable(Project project, String variableName) {
        PsiFile dummyFile = createDummyFile(project, "- title:value\r\n\r\nGET {{" + variableName + "}}\r\n\r\nbody");
        return ApiPsiUtils.findFirstVariable(dummyFile);
    }

    @NotNull
    public static PsiFile createDummyFile(Project project, @NotNull String content) {
        String fileName = "dummy." + ApiFileType.INSTANCE.getDefaultExtension();
        return PsiFileFactory.getInstance(project).createFileFromText(fileName, ApiFileType.INSTANCE, content);
    }
}
