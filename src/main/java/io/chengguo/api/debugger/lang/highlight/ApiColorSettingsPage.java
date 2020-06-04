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
            new AttributesDescriptor("Method Type", ApiSyntaxHighlighter.Companion.getMETHOD_TYPE()),
            new AttributesDescriptor("Header Field Name", ApiSyntaxHighlighter.Companion.getHEADER_FIELD_NAME()),
            new AttributesDescriptor("Header Field Value", ApiSyntaxHighlighter.Companion.getHEADER_FIELD_VALUE()),
            new AttributesDescriptor("Query Parameter Key", ApiSyntaxHighlighter.Companion.getQUERY_PARAMETER_KEY()),
            new AttributesDescriptor("Query Parameter Value", ApiSyntaxHighlighter.Companion.getQUERY_PARAMETER_VALUE()),
            new AttributesDescriptor("Request Body", ApiSyntaxHighlighter.Companion.getREQUEST_BODY()),
            new AttributesDescriptor("Variable Name", ApiSyntaxHighlighter.Companion.getVARIABLE_NAME()),
            new AttributesDescriptor("Api Block Separator", ApiSyntaxHighlighter.Companion.getAPI_BLOCK_SEPARATOR()),
            new AttributesDescriptor("Line Comment", ApiSyntaxHighlighter.Companion.getLINE_COMMENT()),
            new AttributesDescriptor("Block Comment", ApiSyntaxHighlighter.Companion.getBLOCK_COMMENT()),
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
        return "\n" +
                "Post\n" +
                "\n" +
                "OPTIONS https://echo.tenon.dev:8080/post?nickname=tenon&age=18&dsfsf=asdfasf\n" +
                "Content-Type: application/json\n" +
                "Accept: application/json\n" +
                "\n" +
                "{}\n" +
                "\n" +
                "---\n" +
                "\n" +
                "{{username}}\n" +
                "\n" +
                "// This line comment\n" +
                "\n" +
                "/**\n" +
                " * This block comment\n" +
                " */\n";
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