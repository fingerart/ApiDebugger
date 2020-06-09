package io.chengguo.api.debugger.lang.run;

import com.intellij.execution.lineMarker.RunLineMarkerContributor;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.TokenSet;
import io.chengguo.api.debugger.ApiDebuggerBundle;
import io.chengguo.api.debugger.actions.RunApiRequestAction;
import io.chengguo.api.debugger.lang.psi.ApiTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.function.Function;

import static io.chengguo.api.debugger.lang.ApiPsiUtils.isOfTypes;

public class ApiLineMarkerProvider extends RunLineMarkerContributor {
    private static final Function<PsiElement, String> TOOLTIP = psiElement -> ApiDebuggerBundle.message("api.debugger.editor.action.run.current");

    @Nullable
    @Override
    public Info getInfo(@NotNull PsiElement element) {
        if (isApiRunElement(element)) {
            ArrayList<AnAction> actions = new ArrayList<>();
            actions.add(new RunApiRequestAction.WithEnvDefault());
            actions.add(new RunApiRequestAction.WithEnvTest());
            return new Info(AllIcons.RunConfigurations.TestState.Run, actions.toArray(AnAction.EMPTY_ARRAY), TOOLTIP);
        }
        return null;
    }

    private boolean isApiRunElement(PsiElement element) {
        return isOfTypes(element, TokenSet.create(
                ApiTypes.Api_GET,
                ApiTypes.Api_POST,
                ApiTypes.Api_GET,
                ApiTypes.Api_HEAD,
                ApiTypes.Api_OPTIONS,
                ApiTypes.Api_PUT,
                ApiTypes.Api_DELETE,
                ApiTypes.Api_TRACE
        ));
    }
}