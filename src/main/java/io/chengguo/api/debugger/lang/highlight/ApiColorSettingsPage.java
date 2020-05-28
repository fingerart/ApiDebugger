package io.chengguo.api.debugger.lang.highlight;

import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.openapi.options.colors.ColorDescriptor;
import com.intellij.openapi.options.colors.ColorSettingsPage;
import io.chengguo.api.debugger.ApiDebuggerBundle;
import io.chengguo.api.debugger.ApiDebuggerIcons;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Map;

public class ApiColorSettingsPage implements ColorSettingsPage {

    private static final AttributesDescriptor[] DESCRIPTORS = new AttributesDescriptor[]{
            new AttributesDescriptor("Identifier", ApiSyntaxHighlighter.Companion.getID()),
            new AttributesDescriptor("String", ApiSyntaxHighlighter.Companion.getSTRING()),
            new AttributesDescriptor("Tag", ApiSyntaxHighlighter.Companion.getTAG()),
            new AttributesDescriptor("Semicolon", ApiSyntaxHighlighter.Companion.getSEMICOLON()),
            new AttributesDescriptor("Line comment", ApiSyntaxHighlighter.Companion.getLINE_COMMENT()),
            new AttributesDescriptor("Block comment", ApiSyntaxHighlighter.Companion.getBLOCK_COMMENT()),
    };

    @Nullable
    @Override
    public Icon getIcon() {
        return ApiDebuggerIcons.API_FILE_TYPE;
    }

    @NotNull
    @Override
    public SyntaxHighlighter getHighlighter() {
        return new ApiSyntaxHighlighter();
    }

    @NotNull
    @Override
    public String getDemoText() {
        return "--- 用户名\n" +
                "\n" +
                "\"\"\"\n" +
                "    这里是接口描述\n" +
                "\"\"\"\n" +
                "\n" +
                "- get: get\n" +
                "- get: get\n" +
                "\n" +
                "post\n" +
                "\n" +
                "// 我是单行注释\n" +
                "\n" +
                "/**\n" +
                " * 我是多行注释\n" +
                " */";
    }

    @Nullable
    @Override
    public Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap() {
        return null;
    }

    @NotNull
    @Override
    public AttributesDescriptor[] getAttributeDescriptors() {
        return DESCRIPTORS;
    }

    @NotNull
    @Override
    public ColorDescriptor[] getColorDescriptors() {
        return ColorDescriptor.EMPTY_ARRAY;
    }

    @NotNull
    @Override
    public String getDisplayName() {
        return ApiDebuggerBundle.message("api.debugger.action.name");
    }
}