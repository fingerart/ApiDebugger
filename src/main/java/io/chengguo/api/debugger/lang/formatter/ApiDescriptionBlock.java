package io.chengguo.api.debugger.lang.formatter;

import com.intellij.formatting.Block;
import com.intellij.formatting.Spacing;
import com.intellij.formatting.SpacingBuilder;
import com.intellij.lang.ASTNode;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.formatter.common.SettingsAwareBlock;
import io.chengguo.api.debugger.lang.ApiLanguage;
import io.chengguo.api.debugger.lang.psi.ApiTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ApiDescriptionBlock extends ApiBaseBlock implements SettingsAwareBlock {

    private final CodeStyleSettings mSettings;

    public ApiDescriptionBlock(ASTNode node, CodeStyleSettings settings) {
        super(node);
        mSettings = settings;
    }

    @Override
    protected Block createBlockOf(ASTNode node) {
        if (node.getElementType() == ApiTypes.Api_COLON) {
            System.out.println("ApiDescriptionBlock.createBlockOf: "+node);
            return new ApiLeafBlock(node, newSpacingBuilder()
                    .before(ApiTypes.Api_COLON)
                    .spaces(0)
                    .after(ApiTypes.Api_COLON)
                    .spaces(1)
            );
        }
        return new ApiDescriptionBlock(node, mSettings);
    }

    @NotNull
    private SpacingBuilder newSpacingBuilder() {
        return new SpacingBuilder(getSettings(), ApiLanguage.INSTANCE);
    }

    @Override
    public Spacing getSpacing(@Nullable Block child1, @NotNull Block child2) {
        return newSpacingBuilder()
                .after(ApiTypes.Api_DESCRIPTION)
                .blankLines(1)
                .getSpacing(this, child1, child2);
    }

    @Override
    public boolean isLeaf() {
        return getNode().getFirstChildNode() == null;
    }

    @NotNull
    @Override
    public CodeStyleSettings getSettings() {
        return mSettings;
    }
}
