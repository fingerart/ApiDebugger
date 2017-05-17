package io.chengguo.apidebugger.ui.window;

import javax.swing.*;
import java.awt.*;

/**
 * Created by fingerart on 17/5/17.
 */
public class RequestBodyWidget {
    public JPanel container;
    private JRadioButton mRbFormData;
    private JRadioButton mRbXWwwFormUrlencoded;
    private JRadioButton mRbRaw;
    private JRadioButton mRbBinary;
    private JPanel bodyContainer;
    private JButton button1;
    private JTextField textField1;

    public RequestBodyWidget() {
    }

    private void createUIComponents() {
        textField1 = new JTextField("hell9");
        System.out.println("RequestBodyWidget.createUIComponents");
//        BodyTypeFormData formData = new BodyTypeFormData();
//        bodyContainer.add(formData.container);
        bodyContainer.add(new TextField("hello"));
    }
}
