package zus.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import zus.model.utility.JDBCUtils;
import zus.view.HomepageView;
import zus.view.LoginView;

import javax.swing.*;

public class LogInController implements EventHandler<ActionEvent> {
    private final TextField userName;
    private final PasswordField password;
    private final LoginView loginView;

    public LogInController(TextField userName, PasswordField password, LoginView loginView) {
        this.userName = userName;
        this.password = password;
        this.loginView = loginView;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        boolean isValidated = JDBCUtils.validateUser(userName.getText(), password.getText());
        if (isValidated) {
            JOptionPane.showMessageDialog(null,
                    "Successfully logged in!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE,
                    null);
            loginView.close();
            HomepageView homepageView = new HomepageView();
            homepageView.show();
        }
        else {
            JOptionPane.showMessageDialog(null,
                    "Login failed!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE,
                    null);
        }
        JDBCUtils.getUserID(userName.getText());
    }
}
