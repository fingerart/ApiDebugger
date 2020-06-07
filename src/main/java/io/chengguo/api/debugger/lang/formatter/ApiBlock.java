package io.chengguo.api.debugger.lang.formatter;

import com.intellij.formatting.*;
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
        return sb.getSpacing(this, child1, child2);
    }

    @Nullable
    @Override
    public Alignment getAlignment() {
        return Alignment.createAlignment();
    }

    @Nullable
    @Override
    public Wrap getWrap() {
        return Wrap.createWrap(WrapType.NONE, false);
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
