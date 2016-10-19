package me.fingerart.idea.ui.window;

import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import com.intellij.util.Consumer;
import me.fingerart.idea.engine.bean.AttachAttribute;
import me.fingerart.idea.engine.component.StateProjectComponent;
import me.fingerart.idea.engine.utils.CommonUtil;
import me.fingerart.idea.engine.utils.ViewUtil;
import me.fingerart.idea.presenter.MainPresenter;
import me.fingerart.idea.ui.iview.IMainWindowView;
import org.apache.http.util.TextUtils;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.plaf.TextUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.event.*;
import java.util.LinkedHashMap;

/**
 * Created by FingerArt on 16/10/1.
 */
public class MainWindow extends IMainWindowView implements ToolWindowFactory, ActionListener {
    private JPanel mToolWindow;
    private JComboBox mCbMethod;
    private JComboBox mCbUrl;
    private JButton mBtnParamAdd;
    private JButton mBtnParamDel;
    private JButton mBtnExecute;
    private JTable mTableParams;
    private JProgressBar mProgressBar;
    private JPanel mJpCookies;
    private JPanel mJpHeaders;
    private JButton mBtnHeaderAdd;
    private JButton mBtnHeaderDel;
    private JButton mBtnCookieAdd;
    private JButton mBtnCookieDel;
    private JButton mBtnShowHeader;
    private JButton mBtnShowCookie;
    private JButton mBtnShowFile;
    private JTextArea mTextAreaInfo;
    private JPanel mJpFiles;
    private JButton mBtnFileAdd;
    private JButton mBtnFileDel;
    private JTable mTableHeaders;
    private JTable mTableCookies;
    private JTable mTableFiles;
    private static final String[] EMPTY_ROW_DATA = {};

    private static final String[] DEFAULT_COLUMN_NAMES = {"Key", "Value"};
    private static final String[] DEFAULT_FILE_COLUMN_NAMES = {"Key", "Path"};
    private static final String[] DEFAULT_URL = {"http://"};
    private static final String[] DEFAULT_METHOD = {"GET", "POST"};
    private static final String[][] DEFAULT_DATA = {{"code", "1"}, {"changeLog", ""}};
    private static final String[][] DEFAULT_EMPTY_DATA = {{"", ""}};
    private DefaultTableModel mParamsModel;
    private MainPresenter mPresenter;

    private boolean uploading;
    private DefaultTableModel mHeadersModel;
    private DefaultTableModel mCookiesModel;
    private DefaultTableModel mFilesModel;

    public MainWindow() {
        initView();
        initEvent();
    }

    private void initView() {
        mTableParams.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
    }

    private void initEvent() {
        mCbMethod.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                StateProjectComponent.getInstance().setMethod(mCbMethod.getSelectedItem().toString());
            }
        });
        mCbUrl.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String url = mCbUrl.getSelectedItem().toString();
                AttachAttribute attachAttribute = StateProjectComponent.getInstance().getAttach().get(url);
                updateAttributeView(attachAttribute);
            }
        });

        mBtnParamAdd.addActionListener(this);
        mBtnParamDel.addActionListener(this);
        mBtnHeaderAdd.addActionListener(this);
        mBtnHeaderDel.addActionListener(this);
        mBtnCookieAdd.addActionListener(this);
        mBtnCookieDel.addActionListener(this);
        mBtnFileAdd.addActionListener(this);
        mBtnFileDel.addActionListener(this);

        mBtnShowHeader.addActionListener(this);
        mBtnShowCookie.addActionListener(this);
        mBtnShowFile.addActionListener(this);
        mBtnExecute.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "ADD_PARAM":
                mParamsModel.addRow(EMPTY_ROW_DATA);
                break;
            case "DEL_PARAM":
                mPresenter.delTableParams(mTableParams);
                ViewUtil.delSelectedRows(mTableParams);
                break;
            case "ADD_HEADER":
                mHeadersModel.addRow(EMPTY_ROW_DATA);
                break;
            case "DEL_HEADER":
                ViewUtil.delSelectedRows(mTableHeaders);
                mJpHeaders.setVisible(mHeadersModel.getRowCount() != 0);
                mBtnShowHeader.setVisible(mHeadersModel.getRowCount() == 0);
                break;
            case "ADD_COOKIE":
                mCookiesModel.addRow(EMPTY_ROW_DATA);
                break;
            case "DEL_COOKIE":
                ViewUtil.delSelectedRows(mTableCookies);
                mJpCookies.setVisible(mCookiesModel.getRowCount() != 0);
                mBtnShowCookie.setVisible(mCookiesModel.getRowCount() == 0);
                break;
            case "ADD_FILE":
                FileChooserDescriptor descriptor = new FileChooserDescriptor(true, false, true, true, false, false);
                VirtualFile toSelect = ProjectManager.getInstance().getOpenProjects()[0].getBaseDir();
                FileChooser.chooseFile(descriptor, null, toSelect, new Consumer<VirtualFile>() {
                    @Override
                    public void consume(VirtualFile virtualFile) {
                        if (virtualFile.exists()) {
                            mFilesModel.addRow(new String[]{"file" + (mFilesModel.getRowCount() + 1), virtualFile.getPath()});
                        }
                    }
                });
                break;
            case "DEL_FILE":
                ViewUtil.delSelectedRows(mTableFiles);
                mJpFiles.setVisible(mFilesModel.getRowCount() != 0);
                mBtnShowFile.setVisible(mFilesModel.getRowCount() == 0);
                break;
            case "SHOW_JP_HEADER":
                mJpHeaders.setVisible(true);
                mBtnShowHeader.setVisible(false);
                break;
            case "SHOW_JP_COOKIE":
                mJpCookies.setVisible(true);
                mBtnShowCookie.setVisible(false);
                break;
            case "SHOW_JP_FILE":
                mJpFiles.setVisible(true);
                mBtnShowFile.setVisible(false);
                break;
            case "EXECUTE":
                if (uploading) {
                    finishExecute();
                    mPresenter.cancelUpload();
                } else {
                    String method = mCbMethod.getSelectedItem().toString();
                    String url = mCbUrl.getEditor().getItem().toString();
                    mPresenter.executeRequest(method, url, mParamsModel, mHeadersModel, mCookiesModel, mFilesModel);
                }
                break;
        }
    }

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        mPresenter = new MainPresenter(this);
        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        Content content = contentFactory.createContent(mToolWindow, "", false);
        toolWindow.getContentManager().addContent(content);
        onToolWindowFirstOpen();
    }

    /**
     * 当工具窗口被第一次打开时调用
     */
    private void onToolWindowFirstOpen() {
        //initMethod
        mCbMethod.setModel(new DefaultComboBoxModel<>(DEFAULT_METHOD));
        String method = StateProjectComponent.getInstance().getMethod();
        if (!TextUtils.isEmpty(method)) {
            mCbMethod.setSelectedItem(method);
        }

        LinkedHashMap<String, AttachAttribute> attach = StateProjectComponent.getInstance().getAttach();


        AttachAttribute attachAttribute = null;
        //initUrl
        String[] urlData;
        if (attach.isEmpty()) {
            urlData = DEFAULT_URL;
        } else {
            urlData = attach.keySet().toArray(new String[attach.size()]);
            attachAttribute = attach.get(urlData[0]);
        }
        mCbUrl.setModel(new DefaultComboBoxModel<>(urlData));
        //initTable
        updateAttributeView(attachAttribute);

        setColumnWidth(mTableParams, 0, 100);
        setColumnWidth(mTableHeaders, 0, 100);
        setColumnWidth(mTableCookies, 0, 100);
        setColumnWidth(mTableFiles, 0, 100);
    }

    /**
     * 更新param header cookie file
     *
     * @param attachAttribute
     */
    private void updateAttributeView(AttachAttribute attachAttribute) {
        String[][] paramData;
        if (attachAttribute == null || attachAttribute.params.isEmpty()) {
            paramData = DEFAULT_DATA;
        } else {
            paramData = CommonUtil.mapToArray(attachAttribute.params);
        }
        mParamsModel = new DefaultTableModel(paramData, DEFAULT_COLUMN_NAMES);
        mTableParams.setModel(mParamsModel);

        String[][] headerData = null;
        boolean bH = attachAttribute != null && !attachAttribute.headers.isEmpty();
        if (bH) {
            headerData = CommonUtil.mapToArray(attachAttribute.headers);
        }
        mJpHeaders.setVisible(bH);
        mBtnShowHeader.setVisible(!bH);
        mHeadersModel = new DefaultTableModel(headerData, DEFAULT_COLUMN_NAMES);
        mTableHeaders.setModel(mHeadersModel);

        String[][] cookieData = null;
        boolean bC = attachAttribute != null && !attachAttribute.cookies.isEmpty();
        if (bC) {
            cookieData = CommonUtil.mapToArray(attachAttribute.cookies);
        }
        mJpCookies.setVisible(bC);
        mBtnShowCookie.setVisible(!bC);
        mCookiesModel = new DefaultTableModel(cookieData, DEFAULT_COLUMN_NAMES);
        mTableCookies.setModel(mCookiesModel);

        String[][] fileData = null;
        boolean bF = attachAttribute != null && !attachAttribute.files.isEmpty();
        if (bF) {
            fileData = CommonUtil.mapToArray(attachAttribute.files);
        }
        mJpFiles.setVisible(bF);
        mBtnShowFile.setVisible(!bF);
        mFilesModel = new DefaultTableModel(fileData, DEFAULT_FILE_COLUMN_NAMES);
        mTableFiles.setModel(mFilesModel);
    }

    /**
     * 设置指定Table的列宽
     *
     * @param table
     * @param columnIndex
     * @param width
     */
    private void setColumnWidth(JTable table, int columnIndex, int width) {
        TableColumn column = table.getColumnModel().getColumn(columnIndex);
        JTableHeader header = table.getTableHeader();
        column.setMinWidth(width);
        column.setPreferredWidth(width);
        header.setResizingColumn(column);
    }

    @Override
    public void showUploadedResult(String info) {
        mTextAreaInfo.setText(info);
    }

    @Override
    public void startExecute() {
        uploading = true;
        mBtnExecute.setText("Cancel");
        mProgressBar.setVisible(true);
    }

    @Override
    public void uploading(short percentage) {
        mProgressBar.setString(percentage + "%");
        mProgressBar.setValue(percentage);
    }

    @Override
    public void finishExecute() {
        uploading = false;
        mProgressBar.setString("");
        mProgressBar.setValue(0);
        mProgressBar.setVisible(false);
        mBtnExecute.setText("Execute");
    }

}
