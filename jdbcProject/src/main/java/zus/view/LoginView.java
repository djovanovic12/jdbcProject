package zus.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Screen;
import javafx.stage.Stage;
import zus.controller.LogInController;

public class LoginView extends Stage {

    private final GridPane gp = new GridPane();
    private Label usernameLbl;
    private Label passwordLbl;
    private Label questionLbl;
    private Hyperlink registerHl;
    private TextField usernameTf;
    private PasswordField pf;
    private Button submitLoginBttn;

    public LoginView() {

        super.setTitle("Login form");

        init();
        pos();
        actions();

        super.setScene(new Scene(this.gp));

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        super.setWidth(screenBounds.getWidth() / 5);
        super.setHeight(screenBounds.getHeight() / 3);
        super.setX((screenBounds.getWidth() - super.getWidth()) / 5);
        super.setY((screenBounds.getHeight() - super.getHeight()) / 3);

    }

    private void init() {

        // Labels
        usernameLbl = new Label("username");
        usernameLbl.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 13));

        passwordLbl = new Label("password");
        passwordLbl.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 13));

        questionLbl = new Label("Don't have an account?");
        questionLbl.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 13));

        registerHl = new Hyperlink("Register");

        // TextFields and PasswordFields
        usernameTf = new TextField();
        usernameTf.setPromptText("enter your username");

        pf = new PasswordField();
        pf.setPromptText("enter your password");

        // Buttons
        submitLoginBttn = new Button("Login");

    }

    private void pos() {

        // Children
        this.gp.add(usernameLbl, 0, 0);
        this.gp.add(passwordLbl, 0, 1);
        this.gp.add(questionLbl, 0, 2);

        this.gp.add(usernameTf, 1, 0);
        this.gp.add(pf, 1, 1);
        this.gp.add(registerHl, 1, 2);
        this.gp.add(submitLoginBttn, 1, 3);

        // Styling
        this.gp.setPadding(new Insets(20));
        this.gp.setVgap(20);
        this.gp.setHgap(10);
        this.gp.setAlignment(Pos.CENTER);

    }

    private void actions() {

        submitLoginBttn.setOnAction(new LogInController(usernameTf, pf, this));

        registerHl.setOnAction(e->{
            RegisterView registerView = new RegisterView();
            registerView.show();
            this.close();
        });
    }

}
