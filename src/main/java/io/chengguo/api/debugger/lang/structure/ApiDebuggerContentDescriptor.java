package io.chengguo.api.debugger.lang.structure;

import org.jetbrains.annotations.Nullable;

public interface ApiDebuggerContentDescriptor extends ApiDebuggerCompositeElement
{
//    @NotNull
//    List<HttpHeaderField> getHeaderFieldList();
    
//    @Nullable
//    HttpHeaderField getHeaderField(@NotNull final String p0);
    
//    @NotNull
//    ContentType getContentType(@NotNull final HttpRequestVariableSubstitutor p0);
    
    @Nullable
    String getMimeType();
}