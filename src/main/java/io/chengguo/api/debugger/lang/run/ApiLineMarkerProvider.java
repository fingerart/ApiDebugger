package io.chengguo.api.debugger.lang.run;

import com.intellij.execution.lineMarker.RunLineMarkerContributor;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.TokenSet;
import io.chengguo.api.debugger.ApiDebuggerBundle;
import io.chengguo.api.debugger.actions.RunApiRequestAction;
import io.chengguo.api.debugger.lang.ApiPsiUtils;
import io.chengguo.api.debugger.lang.environment.ApiEnvironmentIndex;
import io.chengguo.api.debugger.lang.psi.ApiApiBlock;
import io.chengguo.api.debugger.lang.psi.ApiPsiTreeUtil;
import io.chengguo.api.debugger.lang.psi.ApiRequest;
import io.chengguo.api.debugger.lang.psi.ApiTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Function;

import static io.chengguo.api.debugger.lang.ApiPsiUtils.isOfTypes;

public class ApiLineMarkerProvider extends RunLineMarkerContributor {
    private static final Function<PsiElement, String> TOOLTIP = psiElement -> ApiDebuggerBundle.message("api.debugger.editor.action.run.current");

    @Nullable
    @Override
    public Info getInfo(@NotNull PsiElement element) {
        if (isApiRunElement(element)) {
            ApiApiBlock apiBlock = ApiPsiTreeUtil.getParentOfType(element, ApiApiBlock.class);
            Collection<String>  envs = ApiEnvironmentIndex.getAllEnvironments(element.getProject());
            ArrayList<AnAction> actions = new ArrayList<>();
            for (String env : envs) {
                actions.add(new RunApiRequestAction.WithEnv(apiBlock, env));
            }
            return new Info(AllIcons.Actions.Execute, actions.toArray(AnAction.EMPTY_ARRAY), TOOLTIP);
        }
        return null;
    }

    private boolean isApiRunElement(PsiElement element) {
        return isOfTypes(element, TokenSet.create(
                ApiTypes.Api_GET,
                ApiTypes.Api_POST,
                ApiTypes.Api_HEAD,
                ApiTypes.Api_OPTIONS,
                ApiTypes.Api_PUT,
                ApiTypes.Api_DELETE,
                ApiTypes.Api_TRACE,
                ApiTypes.Api_CONNECT
        ));
    }
}