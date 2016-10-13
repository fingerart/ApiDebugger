package me.fingerart.idea.ui.window;

import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import com.intellij.util.Consumer;
import me.fingerart.idea.engine.component.StateProjectComponent;
import me.fingerart.idea.engine.utils.CommonUtil;
import me.fingerart.idea.engine.utils.ViewUtil;
import me.fingerart.idea.presenter.MainPresenter;
import me.fingerart.idea.ui.iview.IMainWindowView;
import org.apache.http.util.TextUtils;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.LinkedHashMap;
import java.util.LinkedList;

/**
 * Created by FingerArt on 16/10/1.
 */
public class MainWindow extends IMainWindowView implements ToolWindowFactory {
    private JPanel mToolWindow;
    private JComboBox mComboBoxModule;
    private JTextField mTextFieldUrl;
    private JTextField mTextFieldPath;
    private JTextArea mTextAreaInfo;
    private JButton mButtonBrowse;
    private JButton mButtonAdd;
    private JButton mButtonDel;
    private JButton mButtonUpload;
    private JTable mTableParams;
    private JProgressBar mProgressBar;

    private static final String[] EMPTY_ROW_DATA = {};
    private static final String[] DEFAULT_COLUMN_NAMES = {"Key", "Value"};
    private static final String[][] DEFAULT_DATA = {{"code", "1"}, {"changeLog", ""}};
    private String mSelectedFilePath;
    private Project mProject;
    private DefaultTableModel mParamsModel;
    private MainPresenter mPresenter;

    private boolean uploading;

    public MainWindow() {
        initView();
        initEvent();
    }

    private void initView() {
        mTableParams.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
    }

    private void initEvent() {
        mTextFieldUrl.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }//Empty

            @Override
            public void focusLost(FocusEvent e) {
                String text = mTextFieldUrl.getText();
                if (!TextUtils.isEmpty(text)) {
                    StateProjectComponent.getInstance().setUrl(text);
                }
            }
        });
        mComboBoxModule.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                StateProjectComponent.getInstance().setModule(e.getItem().toString());
            }
        });
        mTextFieldPath.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }//Empty

            @Override
            public void focusLost(FocusEvent e) {
                mSelectedFilePath = mTextFieldPath.getText();
                StateProjectComponent.getInstance().setPath(mSelectedFilePath);
            }
        });
        mButtonBrowse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileChooserDescriptor descriptor = new FileChooserDescriptor(true, false, true, true, false, false);
                VirtualFile toSelect = ProjectManager.getInstance().getOpenProjects()[0].getBaseDir();
                FileChooser.chooseFile(descriptor, null, toSelect, new Consumer<VirtualFile>() {
                    @Override
                    public void consume(VirtualFile virtualFile) {
                        if (virtualFile.exists()) {
                            mSelectedFilePath = virtualFile.getPath();
                            mTextFieldPath.setText(mSelectedFilePath);
                            StateProjectComponent.getInstance().setPath(mSelectedFilePath);
                        }
                    }
                });
            }
        });
        //TODO 触发保存Table数据还需优化
        mTableParams.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }//Empty

            @Override
            public void focusLost(FocusEvent e) {
                int row = mTableParams.getSelectedRow();
                String key = (String) mTableParams.getModel().getValueAt(row, 0);
                String value = (String) mTableParams.getModel().getValueAt(row, 1);
                StateProjectComponent.getInstance().addOrModParam(key, value);
            }
        });
        mButtonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mParamsModel.addRow(EMPTY_ROW_DATA);
            }
        });
        mButtonDel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mPresenter.delTableParams(mTableParams);
                ViewUtil.delSelectedRows(mTableParams);
            }
        });
        mButtonUpload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (uploading) {
                    finishUpload();
                    mPresenter.cancelUpload();
                } else {
                    mPresenter.handleUploadFile(mTextFieldUrl.getText(), mSelectedFilePath, mParamsModel);
                }
            }
        });
    }

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        mPresenter = new MainPresenter(this);
        mProject = project;
        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        Content content = contentFactory.createContent(mToolWindow, "", false);
        toolWindow.getContentManager().addContent(content);
        onToolWindowFirstOpen();
    }

    /**
     * 当工具窗口被第一次打开时调用
     */
    private void onToolWindowFirstOpen() {
        ModuleManager moduleManager = ModuleManager.getInstance(mProject);

        //initUrl
        mTextFieldUrl.setText(StateProjectComponent.getInstance().getUrl());

        //initModules
        LinkedList<String> list = new LinkedList<>();
        for (Module module : moduleManager.getSortedModules()) {
            list.add(module.getName());
        }
        String[] strings = list.toArray(new String[]{});
        mComboBoxModule.setModel(new DefaultComboBoxModel(strings));
        String module = StateProjectComponent.getInstance().getModule();
        if (!TextUtils.isEmpty(module)) {
            mComboBoxModule.setSelectedItem(module);
        }

        //initTable
        LinkedHashMap<String, String> params = StateProjectComponent.getInstance().getParams();
        String[][] data;
        if (params.isEmpty()) {
            data = DEFAULT_DATA;
        } else {
            data = CommonUtil.mapToArray(params);
        }
        mParamsModel = new DefaultTableModel(data, DEFAULT_COLUMN_NAMES);
        mTableParams.setModel(mParamsModel);

        //initPath
//        String moduleFilePath = mModules[0].getModuleFilePath();
//        String path = moduleFilePath.substring(0, moduleFilePath.lastIndexOf('/')) + "/build/outputs/apk/";
    }

    @Override
    public void showUploadedResult(String info) {
        mTextAreaInfo.setText(info);
    }

    @Override
    public void startUpload() {
        uploading = true;
        mButtonUpload.setText("Cancel");
        mProgressBar.setVisible(true);
    }

    @Override
    public void uploading(short percentage) {
        mProgressBar.setString(percentage + "%");
        mProgressBar.setValue(percentage);
    }

    @Override
    public void finishUpload() {
        uploading = false;
        mProgressBar.setString("0%");
        mProgressBar.setValue(0);
        mProgressBar.setVisible(false);
        mButtonUpload.setText("Upload");
    }
}
