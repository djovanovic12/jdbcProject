package zus.view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import zus.controller.FlightBookController;
import zus.model.Spaceship;
import zus.model.base.Server;

public class BookAFlightView extends Stage {

    private final BorderPane bp = new BorderPane();
    private final TableView<Spaceship> tvSpaceships = new SpaceshipTable(Server.SERVER.getSpaceships());

    private Label firstNamesLbl;
    private Label lastNamesLbl;
    private TextField firstNameTF;
    private TextField lastNameTF;
    private Button bookAFlightBttn;

    public BookAFlightView() {

        super.setTitle("Flights form");

        init();
        pos();
        actions();

        super.setScene(new Scene(this.bp));

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        super.setWidth(screenBounds.getWidth() / 2);
        super.setHeight(screenBounds.getHeight() / 2);
        super.setX((screenBounds.getWidth() - super.getWidth()) / 2);
        super.setY((screenBounds.getHeight() - super.getHeight()) / 2);

    }

    private void init() {
        tvSpaceships.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Spaceship>() {
            @Override
            public void changed(ObservableValue<? extends Spaceship> observableValue, Spaceship oldSpaceship, Spaceship newSpaceship) {
                Server.SERVER.setSpaceShipID(newSpaceship.getSpaceshipId());
            }
        });

        firstNamesLbl = new Label("First names");

        firstNameTF = new TextField();

        lastNamesLbl = new Label("Last names");

        lastNameTF = new TextField();

        bookAFlightBttn = new Button("BOOK A FLIGHT");

    }

    private void pos() {
        HBox hBox = new HBox();
        hBox.getChildren().addAll(firstNamesLbl, firstNameTF, lastNamesLbl, lastNameTF, bookAFlightBttn);
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(20);
        hBox.setPadding(new Insets(25));

        this.bp.setTop(hBox);
        this.bp.setCenter(this.tvSpaceships);
    }

    private void actions() {
        bookAFlightBttn.setOnAction(new FlightBookController(firstNameTF, lastNameTF,this));
    }

}
