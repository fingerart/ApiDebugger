package io.chengguo.api.debugger.ui.header;

import com.intellij.openapi.util.text.*;
import com.google.gson.*;
import org.jetbrains.annotations.*;

public class HttpHeaderDocumentation
{
    private static final String CC_LICENCE = " is licensed under <a href=\"https://creativecommons.org/licenses/by-sa/2.5/\">CC-BY-SA 2.5</a>.";
    private static final String URL_PREFIX = "https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/";
    private static final String RFC_PREFIX = "https://tools.ietf.org/html/rfc";
    private final String mName;
    private final String mRfc;
    private final String mRfcTitle;
    private final String mDescription;
    private final boolean mIsDeprecated;
    
    private HttpHeaderDocumentation(String name, String rfc, String rfcTitle, String description, boolean isDeprecated) {
        mName = name;
        mRfc = rfc;
        mRfcTitle = rfcTitle;
        mDescription = description;
        mIsDeprecated = isDeprecated;
    }

    @Nullable
    public static HttpHeaderDocumentation read(@NotNull final JsonObject obj) {
        String name = getAsString(obj, "name");
        if (StringUtil.isNotEmpty(name)) {
            String rfcTitle = getAsString(obj, "rfc-title");
            String rfcRef = getAsString(obj, "rfc-ref");
            String descr = getAsString(obj, "descr");
            JsonElement obsolete = obj.get("obsolete");
            boolean isObsolete = obsolete != null && obsolete.isJsonPrimitive() && obsolete.getAsBoolean();
            return new HttpHeaderDocumentation(name, rfcRef, rfcTitle, descr, isObsolete);
        }
        return null;
    }
    
    @NotNull
    private static String getAsString(@NotNull final JsonObject obj, @NotNull final String name) {
        JsonElement element = obj.get(name);
        return  (element != null && element.isJsonPrimitive()) ? element.getAsString() : "";
    }
    
    @Nullable
    public String generateDoc() {
        if (StringUtil.isNotEmpty(mDescription)) {
            StringBuilder out = new StringBuilder().append(mDescription);
            if (StringUtil.isNotEmpty(mRfc) && StringUtil.isNotEmpty(mRfcTitle)) {
                out.append("<br/><br/>");
                out.append("<a href=\"").append("https://tools.ietf.org/html/rfc").append(mRfc).append("\">").append(mRfcTitle).append("</a>");
            }
            String url = this.getUrl();
            out.append("<br/><br/>");
            out.append("<a href=\"").append(url).append("\">").append(getName()).append("</a> by ");
            out.append("<a href=\"").append(url).append("$history").append("\">").append("Mozilla Contributors").append("</a>");
            out.append(" is licensed under <a href=\"https://creativecommons.org/licenses/by-sa/2.5/\">CC-BY-SA 2.5</a>.");
            return out.toString();
        }
        return null;
    }
    
    @NotNull
    public String getName() {
        return mName;
    }
    
    public boolean isDeprecated() {
        return mIsDeprecated;
    }
    
    @NotNull
    public String getDescription() {
        return mDescription;
    }
    
    @NotNull
    public String getUrl() {
        return  "https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/" + this.getName();
    }
}