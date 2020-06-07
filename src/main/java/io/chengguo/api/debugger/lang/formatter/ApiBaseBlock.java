package io.chengguo.api.debugger.lang.formatter;

import com.intellij.formatting.*;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.TokenType;
import com.intellij.psi.formatter.FormatterUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public abstract class ApiBaseBlock implements ASTBlock {
    private ASTNode myNode;
    private Boolean myIncomplete;

    public ApiBaseBlock(ASTNode node) {
        assert node != null;
        this.myNode = node;
    }

    @NotNull
    @Override
    public ASTNode getNode() {
        return myNode;
    }

    @NotNull
    @Override
    public TextRange getTextRange() {
        return myNode.getTextRange();
    }

    @NotNull
    @Override
    public List<Block> getSubBlocks() {
        ArrayList<Block> blocks = new ArrayList<>();
        for (ASTNode child = myNode.getFirstChildNode(); child != null; child = child.getTreeNext()) {
            if (child.getElementType() != TokenType.WHITE_SPACE) {
                blocks.add(createBlockOf(child));
            }
        }
        return blocks;
    }

    protected Block createBlockOf(ASTNode node) {
        return new ApiLeafBlock(node);
    }

    @Nullable
    @Override
    public Wrap getWrap() {
        return Wrap.createWrap(WrapType.NONE, false);
    }

    @Nullable
    @Override
    public Indent getIndent() {
        return Indent.getNoneIndent();
    }

    @Nullable
    @Override
    public Alignment getAlignment() {
        return Alignment.createAlignment();
    }

    @Nullable
    @Override
    public Spacing getSpacing(@Nullable Block child1, @NotNull Block child2) {
        return null;
    }

    @NotNull
    @Override
    public ChildAttributes getChildAttributes(int newChildIndex) {
        return new ChildAttributes(Indent.getAbsoluteNoneIndent(), null);
    }

    @Override
    public boolean isIncomplete() {
        if (myIncomplete == null) {
            myIncomplete = FormatterUtil.isIncomplete(getNode());
        }
        return myIncomplete;
    }

    @Override
    public boolean isLeaf() {
        return true;
    }

    @Override
    public String toString() {
        return myNode.getText() + " " + getTextRange();
    }
}
