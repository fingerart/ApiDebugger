package me.fingerart.idea.engine.component;

import com.intellij.openapi.components.*;
import com.intellij.openapi.project.Project;
import me.fingerart.idea.engine.bean.AttachAttribute;
import me.fingerart.idea.engine.utils.CommonUtil;
import org.apache.batik.transcoder.wmf.tosvg.TextureFactory;
import org.apache.http.util.TextUtils;
import org.jdom.Content;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by FingerArt on 16/10/7.
 */
@State(name = "ApiDebugger", storages = {@Storage(StoragePathMacros.PROJECT_FILE)})
public class StateProjectComponent extends AbstractProjectComponent implements PersistentStateComponent<Element> {

    public static final String ELEMENT_ROOT_ITEM_NAME = "component";
    public static final String ELEMENT_ROOT_NAME = "root";
    public static final String ELEMENT_NAME_URL = "url";
    public static final String ELEMENT_NAME_METHOD = "method";

    public static final String ELEMENT_NAME_PARAM = "param";
    public static final String ELEMENT_NAME_HEADER = "header";
    public static final String ELEMENT_NAME_COOKIE = "cookie";
    public static final String ELEMENT_NAME_FILE = "fiel";

    public static final String ATTRIBUTE_NAME_KEY = "key";
    public static final String ATTRIBUTE_NAME_VAL = "value";

    private String method;
    private LinkedHashMap<String, AttachAttribute> attach = new LinkedHashMap<>();
    private static StateProjectComponent instance;

    protected StateProjectComponent(Project project) {
        super(project);
        instance = this;
    }

    public static StateProjectComponent getInstance() {
        return instance;
    }

    @Nullable
    @Override
    public Element getState() {
        System.out.println("StateProjectComponent.getState");
        //init element
        Element rootElement = new Element(ELEMENT_ROOT_NAME);
        Element elementUrl = new Element(ELEMENT_NAME_URL);
        Element elementMethod = new Element(ELEMENT_NAME_METHOD);

        //init attribute
        elementMethod.setText(method);

        for (Map.Entry<String, AttachAttribute> entry : attach.entrySet()) {
            String url = entry.getKey();
            elementUrl.setAttribute(ATTRIBUTE_NAME_VAL, url);
            if (TextUtils.isEmpty(url)) continue;

            AttachAttribute attributes = entry.getValue();
            buildAttachElement(attributes.params, ELEMENT_NAME_PARAM, elementUrl);
            buildAttachElement(attributes.headers, ELEMENT_NAME_HEADER, elementUrl);
            buildAttachElement(attributes.cookies, ELEMENT_NAME_COOKIE, elementUrl);
            buildAttachElement(attributes.files, ELEMENT_NAME_FILE, elementUrl);
        }

        //merge element
        rootElement
                .addContent(elementMethod)
                .addContent(elementUrl);
        System.out.println(rootElement);
        return rootElement;
    }

    @Override
    public void loadState(Element element) {
        System.out.println("element = [" + element + "]");
        if (ELEMENT_ROOT_ITEM_NAME.equals(element.getName())) {
            Iterator<Content> iterator = element.getDescendants();
            while (iterator.hasNext()) {
                Object content = iterator.next();
                if (!(content instanceof Element)) {
                    continue;
                }
                Element e = (Element) content;
                if (ELEMENT_NAME_METHOD.equals(e.getName())) {
                    method = e.getText();
                } else if (ELEMENT_NAME_URL.equals(e.getName())) {
                    String url = e.getAttributeValue(ATTRIBUTE_NAME_VAL);
                    if (TextUtils.isEmpty(url)) continue;

                    AttachAttribute attribute = new AttachAttribute();
                    for (Element ce : e.getChildren()) {
                        String key = ce.getAttributeValue(ATTRIBUTE_NAME_KEY);
                        String val = ce.getAttributeValue(ATTRIBUTE_NAME_VAL);
                        if (TextUtils.isEmpty(key) || TextUtils.isEmpty(val)) continue;
                        if (ELEMENT_NAME_PARAM.equals(ce.getName())) {
                            attribute.params.put(key, val);
                        } else if (ELEMENT_NAME_HEADER.equals(ce.getName())) {
                            attribute.headers.put(key, val);
                        } else if (ELEMENT_NAME_COOKIE.equals(ce.getName())) {
                            attribute.cookies.put(key, val);
                        } else if (ELEMENT_NAME_FILE.equals(ce.getName())) {
                            attribute.files.put(key, val);
                        }
                    }
                }
            }
        }
        System.out.println(method + " -- " + attach);
    }

    private void buildAttachElement(HashMap<String, String> list, String eName, Element pElement) {
        for (Map.Entry<String, String> entry : list.entrySet()) {
            if (TextUtils.isEmpty(entry.getKey())) continue;
            Element p = new Element(eName)
                    .setAttribute(ATTRIBUTE_NAME_KEY, entry.getKey())
                    .setAttribute(ATTRIBUTE_NAME_VAL, entry.getValue());
            pElement.addContent(p);
        }
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public LinkedHashMap<String, AttachAttribute> getAttach() {
        return attach;
    }

    public void setAttach(LinkedHashMap<String, AttachAttribute> attach) {
        this.attach = attach;
    }

    @Override
    public void projectOpened() {
        System.out.println("StateProjectComponent.projectOpened");
    }

    @Override
    public void projectClosed() {
        System.out.println("StateProjectComponent.projectClosed");
    }

    @Override
    public void initComponent() {
        System.out.println("StateProjectComponent.initComponent");
    }

    @Override
    public void disposeComponent() {
        System.out.println("StateProjectComponent.disposeComponent");
    }

    @NotNull
    @Override
    public String getComponentName() {
        return "ApiDebugger";
    }
}
