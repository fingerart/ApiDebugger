package me.fingerart.idea.engine.component;

import com.intellij.openapi.components.*;
import com.intellij.openapi.project.Project;
import me.fingerart.idea.engine.utils.CommonUtil;
import org.apache.http.util.TextUtils;
import org.jdom.Content;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by FingerArt on 16/10/7.
 */
@State(name = "FileUpload", storages = {@Storage(StoragePathMacros.PROJECT_FILE)})
public class StateProjectComponent extends AbstractProjectComponent implements PersistentStateComponent<Element> {

    public static final String ELEMENT_ROOT_ITEM_NAME = "component";
    public static final String ELEMENT_ROOT_NAME = "file_upload";
    public static final String ELEMENT_NAME_URL = "url";
    public static final String ELEMENT_NAME_MODULE = "module";
    public static final String ELEMENT_NAME_PATH = "path";

    public static final String ELEMENT_ROOT_NAME_PARAMS = "params";
    public static final String ELEMENT_NAME_PARAM = "param";

    public static final String ATTRIBUTE_NAME_KEY = "key";
    public static final String ATTRIBUTE_NAME_VAL = "value";

    private String url;
    private String module;
    private String path;
    private LinkedHashMap<String, String> params = new LinkedHashMap<>();
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
        //init element
        Element rootElement = new Element(ELEMENT_ROOT_NAME);
        Element elementUrl = new Element(ELEMENT_NAME_URL);
        Element elementModule = new Element(ELEMENT_NAME_MODULE);
        Element elementPath = new Element(ELEMENT_NAME_PATH);

        Element elementRootParams = new Element(ELEMENT_ROOT_NAME_PARAMS);

        //init attribute
        elementUrl.setText(url);
        elementModule.setText(module);
        elementPath.setText(path);

        for (Map.Entry<String, String> entry : params.entrySet()) {
            String key = entry.getKey();
            if (TextUtils.isEmpty(key)) continue;
            String value = CommonUtil.nullToEmpty(entry.getValue());

            Element p = new Element(ELEMENT_NAME_PARAM)
                    .setAttribute(ATTRIBUTE_NAME_KEY, key)
                    .setAttribute(ATTRIBUTE_NAME_VAL, value);
            elementRootParams.addContent(p);
        }

        //merge element
        rootElement
                .addContent(elementUrl)
                .addContent(elementModule)
                .addContent(elementPath)
                .addContent(elementRootParams);

        return rootElement;
    }

    @Override
    public void loadState(Element element) {
        if (ELEMENT_ROOT_ITEM_NAME.equals(element.getName())) {
            Iterator<Content> iterator = element.getDescendants();
            while (iterator.hasNext()) {
                Object content = iterator.next();
                if (!(content instanceof Element)) {
                    continue;
                }
                Element e = (Element) content;
                if (ELEMENT_NAME_URL.equals(e.getName())) {
                    url = e.getText();
                } else if (ELEMENT_NAME_MODULE.equals(e.getName())) {
                    module = e.getText();
                } else if (ELEMENT_NAME_PATH.equals(e.getName())) {
                    path = e.getText();
                } else if (ELEMENT_NAME_PARAM.equals(e.getName())) {
                    String key = e.getAttributeValue(ATTRIBUTE_NAME_KEY);
                    String value = e.getAttributeValue(ATTRIBUTE_NAME_VAL);
                    params.put(key, value);
                }
            }
        }
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public LinkedHashMap<String, String> getParams() {
        return params;
    }

    public void setParams(LinkedHashMap<String, String> params) {
        this.params = params;
    }

    public void delParam(String key) {
        params.remove(key);
    }

    public void addOrModParam(String key, String value) {
        if (!TextUtils.isEmpty(key)) {
            params.put(key, value);
        }
    }

    @Override
    public String toString() {
        return "StateProjectComponent{" +
                "url='" + url + '\'' +
                ", module='" + module + '\'' +
                ", path='" + path + '\'' +
                ", params=" + params +
                '}';
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
        return "FileUpload";
    }
}
