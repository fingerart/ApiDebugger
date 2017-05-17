package io.chengguo.apidebugger.ui.window;

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
    private JPanel bodyContainer;
    private JTabbedPane tabbedPane1;

    private void createUIComponents() {
        BodyTypeFormData formData = new BodyTypeFormData();
        bodyContainer.add(formData.container);
    }
}
