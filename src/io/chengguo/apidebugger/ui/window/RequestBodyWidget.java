package io.chengguo.apidebugger.ui.window;

import com.intellij.ui.components.JBPanel;
import com.intellij.ui.components.JBTabbedPane;
import com.intellij.ui.components.JBTextField;

import javax.swing.*;

/**
 * Created by fingerart on 17/5/17.
 */
public class RequestBodyWidget {
    public JPanel container;
    private JRadioButton mRbFormData;
    private JRadioButton mRbXWwwFormUrlencoded;
    private JRadioButton mRbRaw;
    private JRadioButton mRbBinary;
    private JBTabbedPane bodyContainer;

    public RequestBodyWidget() {
        bodyContainer.addTab("hello", new JBTextField("helloasdfsadf"));
    }

    private void createUIComponents() {
        System.out.println("RequestBodyWidget.createUIComponents");
//        BodyTypeFormData formData = new BodyTypeFormData();
//        bodyContainer.add(formData.container);
    }
}
