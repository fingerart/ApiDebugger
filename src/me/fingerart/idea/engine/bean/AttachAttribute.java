package me.fingerart.idea.engine.bean;

import java.util.LinkedHashMap;

/**
 * 附加属性
 * Created by fingerart on 16/10/19.
 */
public class AttachAttribute {
    public LinkedHashMap<String, String> params = new LinkedHashMap<>();
    public LinkedHashMap<String, String> headers = new LinkedHashMap<>();
    public LinkedHashMap<String, String> cookies = new LinkedHashMap<>();
    public LinkedHashMap<String, String> files = new LinkedHashMap<>();

    @Override
    public String toString() {
        return "AttachAttribute{" +
                "params=" + params +
                ", headers=" + headers +
                ", cookies=" + cookies +
                ", files=" + files +
                '}';
    }
}
