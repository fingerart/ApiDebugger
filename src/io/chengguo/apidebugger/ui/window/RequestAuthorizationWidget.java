package io.chengguo.apidebugger.ui.window;

import com.intellij.ui.components.JBOptionButton;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;

import static com.intellij.openapi.ui.DialogWrapper.DEFAULT_ACTION;

/**
 * Created by fingerart on 17/5/27.
 */
public class RequestAuthorizationWidget {
    public JPanel container;
    private JBOptionButton authorizationType;

    private void createUIComponents() {
        AuthorizationAction action = new AuthorizationAction("No Auth");
        authorizationType = new JBOptionButton(action, action.getOptions());
    }

    private class AuthorizationAction extends javax.swing.AbstractAction implements com.intellij.openapi.ui.OptionAction {

        private Action[] myOptions = new Action[0];

        public AuthorizationAction(@NonNls String name) {
            super(name);
            putValue(DEFAULT_ACTION, Boolean.TRUE);
        }

        @NotNull
        @Override
        public Action[] getOptions() {
            return myOptions;
        }

        public void setOptions(List<? extends Action> actions) {
//            myOptions = ArrayUtil.toObjectArray(actions, Action.class);
        }

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}
