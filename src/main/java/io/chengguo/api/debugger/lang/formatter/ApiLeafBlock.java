package io.chengguo.api.debugger.lang.formatter;

import com.intellij.formatting.Block;
import com.intellij.formatting.Spacing;
import com.intellij.formatting.SpacingBuilder;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;

public class ApiLeafBlock extends ApiBaseBlock {

    private final SpacingBuilder mSpacingBuilder;

    public ApiLeafBlock(ASTNode node) {
        this(node, null);
    }

    public ApiLeafBlock(ASTNode node, SpacingBuilder spacingBuilder) {
        super(node);
        mSpacingBuilder = spacingBuilder;
    }

    @NotNull
    @Override
    public List<Block> getSubBlocks() {
        return Collections.emptyList();
    }

    @Nullable
    @Override
    public Spacing getSpacing(@Nullable Block child1, @NotNull Block child2) {
        return mSpacingBuilder == null ? null : mSpacingBuilder.getSpacing(this, child1, child2);
    }

    @Override
    public boolean isLeaf() {
        return true;
    }
}
