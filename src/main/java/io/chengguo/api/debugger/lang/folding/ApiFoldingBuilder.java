package io.chengguo.api.debugger.lang.folding;

import com.intellij.lang.ASTNode;
import com.intellij.lang.folding.FoldingBuilder;
import com.intellij.lang.folding.FoldingDescriptor;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.project.DumbAware;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import io.chengguo.api.debugger.lang.ApiPsiUtils;
import io.chengguo.api.debugger.lang.psi.ApiTypes;
import io.chengguo.api.debugger.lang.psi.impl.ApiPsiImplUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class ApiFoldingBuilder implements DumbAware, FoldingBuilder {
    @NotNull
    @Override
    public FoldingDescriptor[] buildFoldRegions(@NotNull ASTNode node, @NotNull Document document) {
        ArrayList<FoldingDescriptor> descriptors = new ArrayList<>();
        collectDescriptors(node, descriptors);
        return descriptors.toArray(FoldingDescriptor.EMPTY);
    }

    private void collectDescriptors(ASTNode node, ArrayList<FoldingDescriptor> descriptors) {
        ASTNode[] apiBlocks = node.getChildren(TokenSet.create(ApiTypes.Api_API_BLOCK));
        for (ASTNode apiBlockNode : apiBlocks) {
            ASTNode requestNode = apiBlockNode.findChildByType(ApiTypes.Api_REQUEST);
            if (requestNode != null) {
                if (requestNode.findChildByType(ApiTypes.Api_REQUEST_LINE) != null) {
                    descriptors.add(new FoldingDescriptor(requestNode, requestNode.getTextRange()));
                }
            }
        }
    }

    @Nullable
    @Override
    public String getPlaceholderText(@NotNull ASTNode node) {
        IElementType type = node.getElementType();
        if (type == ApiTypes.Api_REQUEST) {
            ASTNode apiRequestLineNode = node.findChildByType(ApiTypes.Api_REQUEST_LINE);
            if (apiRequestLineNode != null) {
                ASTNode apiMethodNode = apiRequestLineNode.findChildByType(ApiTypes.Api_METHOD);
                ASTNode apiRequestTargetNode = apiRequestLineNode.findChildByType(ApiTypes.Api_REQUEST_TARGET);
                if (apiMethodNode != null) {
                    return apiMethodNode.getText() + ((apiRequestTargetNode != null) ? (" " + apiRequestTargetNode.getText()) : "");
                }
            }
        }
        return "...";
    }

    @Override
    public boolean isCollapsedByDefault(@NotNull ASTNode node) {
        return false;
    }
}
