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
import zus.controller.RegistrationController;

public class RegisterView extends Stage {

    private final GridPane root = new GridPane();
    private Label firstNameLbl;
    private Label lastNameLbl;
    private Label usernameLbl;
    private Label passwordLbl;
    private Label passportLbl;
    private Label questionLbl;
    private Hyperlink loginHl;
    private TextField firstNameTf;
    private TextField lastNameTf;
    private TextField usernameTf;
    private PasswordField pf;
    private CheckBox passportCb;
    private Button submitRegistrationBttn;

    public RegisterView() {

        super.setTitle("Register form");
        init();
        pos();
        actions();

        super.setScene(new Scene(this.root));

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        super.setWidth(screenBounds.getWidth() / 5);
        super.setHeight(screenBounds.getHeight() / 3);
        super.setX((screenBounds.getWidth() - super.getWidth()) / 5);
        super.setY((screenBounds.getHeight() - super.getHeight()) / 3);

    }

    private void init() {

        // Labels
        firstNameLbl = new Label("First name");
        firstNameLbl.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 13));

        lastNameLbl = new Label("Last name");
        lastNameLbl.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 13));

        usernameLbl = new Label("Username");
        usernameLbl.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 13));

        passwordLbl = new Label("Password");
        passwordLbl.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 13));

        passportLbl = new Label("Do you have a passport?");
        passportLbl.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 13));

        questionLbl = new Label("Already have an account?");

        loginHl = new Hyperlink("Login");

        // TextFields & PasswordFields
        firstNameTf = new TextField();
        firstNameTf.setPromptText("Enter your first name");

        lastNameTf = new TextField();
        lastNameTf.setPromptText("Enter your last name");

        usernameTf = new TextField();
        usernameTf.setPromptText("Enter your username");

        pf = new PasswordField();
        pf.setPromptText("Enter your password");

        // CheckBoxes
        passportCb = new CheckBox();

        // Buttons
        submitRegistrationBttn = new Button("REGISTER");

    }

    private void pos() {

        // Children
        this.root.add(firstNameLbl, 0, 0);
        this.root.add(lastNameLbl, 0, 1);
        this.root.add(usernameLbl, 0, 2);
        this.root.add(passwordLbl, 0, 3);
        this.root.add(passportLbl, 0, 4);
        this.root.add(questionLbl, 0, 5);
        this.root.add(submitRegistrationBttn, 0, 6);

        this.root.add(firstNameTf, 1, 0);
        this.root.add(lastNameTf, 1, 1);
        this.root.add(usernameTf, 1, 2);
        this.root.add(pf, 1, 3);
        this.root.add(passportCb, 1, 4);
        this.root.add(loginHl, 1, 5);

        // Styling
        this.root.setPadding(new Insets(20));
        this.root.setVgap(20);
        this.root.setHgap(10);
        this.root.setAlignment(Pos.CENTER);

    }

    private void actions() {

        submitRegistrationBttn.setOnAction(new RegistrationController(firstNameTf, lastNameTf, usernameTf, pf, passportCb, this));

        loginHl.setOnAction(e->{
            LoginView loginView = new LoginView();
            loginView.show();
            this.close();
        });

    }

}
