package io.chengguo.api.debugger.lang.psi;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileFactory;
import io.chengguo.api.debugger.lang.ApiFileType;
import io.chengguo.api.debugger.lang.ApiPsiUtils;
import io.chengguo.api.debugger.lang.psi.impl.ApiVariableNameImpl;
import org.jetbrains.annotations.NotNull;

public class ApiElementGenerator {

    private final Project mProject;

    public ApiElementGenerator(Project project) {
        mProject = project;
    }

    public ApiVariableName createVariableName(String name) {
        PsiFile dummyFile = createDummyFile("- title:value\r\n\r\nGET {{" + name + "}}\r\n\r\nbody");
        ApiApiBlock firstApiBlock = ApiPsiUtils.getFirstApiBlock(dummyFile);
        return firstApiBlock.getRequest().getRequestLine().getRequestTarget().getHost().getVariableList().get(0).getVariableName();
    }

    @NotNull
    public PsiFile createDummyFile(@NotNull final String content) {
        String fileName = "dummy." + ApiFileType.INSTANCE.getDefaultExtension();
        return PsiFileFactory.getInstance(mProject).createFileFromText(fileName, ApiFileType.INSTANCE, content);
    }
}
