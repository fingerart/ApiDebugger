package io.chengguo.apidebugger.ui.window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static io.chengguo.apidebugger.engine.utils.ViewUtil.setCursor;

/**
 * Created by fingerart on 17/5/27.
 */
public class ResponseBodyWidget {
    private CardLayout mPreviewTypeCardLayout;
    public JPanel container;
    private JRadioButton mRbPretty;
    private JRadioButton mRbRaw;
    private JRadioButton mRbPreview;
    private JPanel previewTypeContainer;
    private JComboBox mCbFormatType;
    private JButton mBtnWrapLine;
    private JTextPane previewTextPane;

    public ResponseBodyWidget() {
        mPreviewTypeCardLayout = ((CardLayout) previewTypeContainer.getLayout());

        setCursor(Cursor.HAND_CURSOR, mRbPretty, mRbRaw, mRbPreview, mCbFormatType, mBtnWrapLine);

        mRbPretty.addActionListener(mPreviewType);
        mRbRaw.addActionListener(mPreviewType);
        mRbPreview.addActionListener(mPreviewType);
    }

    private final ActionListener mPreviewType = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            mPreviewTypeCardLayout.show(previewTypeContainer, e.getActionCommand());
        }
    };
}
