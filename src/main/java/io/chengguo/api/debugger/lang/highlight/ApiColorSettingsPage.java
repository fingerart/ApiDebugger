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
            new AttributesDescriptor("Key", ApiSyntaxHighlighter.Companion.getKEY()),
            new AttributesDescriptor("Value", ApiSyntaxHighlighter.Companion.getSTRING()),
            new AttributesDescriptor("Separator", ApiSyntaxHighlighter.Companion.getSEPARATOR()),
            new AttributesDescriptor("Bad Character", ApiSyntaxHighlighter.Companion.getBAD_CHARACTER()),
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
        return "# You are reading the \".properties\" entry.\n" +
                "! The exclamation mark can also mark text as comments.\n" +
                "website = https://en.wikipedia.org/\n" +
                "language = English\n" +
                "# The backslash below tells the application to continue reading\n" +
                "# the value onto the next line.\n" +
                "message = Welcome to \\\n" +
                "          Wikipedia!\n" +
                "# Add spaces to the key\n" +
                "key\\ with\\ spaces = This is the value that could be looked up with the key \"key with spaces\".\n" +
                "# Unicode\n" +
                "tab : \\u0009";
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