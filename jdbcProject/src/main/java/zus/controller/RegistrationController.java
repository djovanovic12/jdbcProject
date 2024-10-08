package zus.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import zus.model.base.Server;
import zus.model.utility.JDBCUtils;
import zus.view.HomepageView;
import zus.view.RegisterView;

import javax.swing.*;

public class RegistrationController implements EventHandler<ActionEvent> {
    private final TextField firstName;
    private final TextField lastName;
    private final TextField userName;
    private final PasswordField password;
    private final CheckBox passport;
    private final RegisterView registerView;

    public RegistrationController(TextField firstName, TextField lastName, TextField userName, PasswordField password, CheckBox passport, RegisterView registerView) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.passport = passport;
        this.registerView = registerView;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        String firstName = this.firstName.getText();
        String lastName = this.lastName.getText();
        String userName = this.userName.getText();
        String password = this.password.getText();
        boolean passport = this.passport.isSelected();
        JDBCUtils.insertUser(firstName, lastName, userName, password, passport);
        JOptionPane.showMessageDialog(null,
                "User successfully registered!",
                "Success",
                JOptionPane.INFORMATION_MESSAGE,
                null);
        registerView.close();
        Server.SERVER.setUserName(userName);
        HomepageView homepageView = new HomepageView();
        homepageView.show();
    }
}
