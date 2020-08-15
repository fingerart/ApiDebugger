package io.chengguo.api.debugger.lang.formatter;

import com.intellij.formatting.Block;
import com.intellij.formatting.Spacing;
import com.intellij.formatting.SpacingBuilder;
import com.intellij.lang.ASTNode;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import io.chengguo.api.debugger.lang.ApiLanguage;
import io.chengguo.api.debugger.lang.psi.ApiTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ApiBlock extends ApiBaseBlock {

    private final CodeStyleSettings mSettings;

    public ApiBlock(ASTNode node, CodeStyleSettings settings) {
        super(node);
        assert settings != null;
        this.mSettings = settings;
    }

    @Override
    protected Block createBlockOf(ASTNode node) {
        return new ApiBlock(node, mSettings);
    }

    @Nullable
    @Override
    public Spacing getSpacing(@Nullable Block child1, @NotNull Block child2) {
        SpacingBuilder sb = newSpacingBuilder();
        sb.around(ApiTypes.Api_SEPARATOR).blankLines(1);
        sb.around(ApiTypes.Api_DESCRIPTION).blankLines(1);
        sb.afterInside(ApiTypes.Api_COLON, ApiTypes.Api_DESCRIPTION_ITEM).spaces(1);
        sb.afterInside(ApiTypes.Api_HYPHEN, ApiTypes.Api_DESCRIPTION_ITEM).spaces(1);
        sb.between(ApiTypes.Api_DESCRIPTION_ITEM, ApiTypes.Api_DESCRIPTION_ITEM).blankLines(0);
        sb.after(ApiTypes.Api_METHOD).spaces(1);
        sb.around(ApiTypes.Api_REQUEST_MESSAGE_GROUP).blankLines(1);
        sb.afterInside(ApiTypes.Api_COLON, ApiTypes.Api_HEADER_FIELD).spaces(1);
        return sb.getSpacing(this, child1, child2);
    }

    @Override
    public boolean isLeaf() {
        return getNode().getFirstChildNode() == null;
    }

    @NotNull
    private SpacingBuilder newSpacingBuilder() {
        return new SpacingBuilder(mSettings, ApiLanguage.INSTANCE);
    }
}
