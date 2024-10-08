package zus.view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import zus.controller.FilterController;
import zus.controller.SpendingsController;
import zus.model.Planet;
import zus.model.base.Server;
import zus.model.utility.JDBCUtils;

public class HomepageView extends Stage {

    private final BorderPane bp = new BorderPane();
    private final TableView<Planet> tvPlanet = new PlanetsTable(Server.SERVER.getPlanets());

    private Label userLbl;
    private Label buyLivingSpaceLbl;
    private Button buyLivingSpaceBttn;
    private Button filterBttn;
    private Button showAllSpendingsBttn;

    public HomepageView() {

        super.setTitle("HOMEPAGE");

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

    public TableView<Planet> getTvPlanet() {
        return tvPlanet;
    }

    private void init() {
        tvPlanet.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Planet>() {
            @Override
            public void changed(ObservableValue<? extends Planet> observableValue, Planet oldPlanet, Planet newPlanet) {
                Server.SERVER.setPlanetID(newPlanet.getPlanetId());
                Server.SERVER.setLivingSpaces(JDBCUtils.selectAllFromLivingSpace());
            }
        });

        userLbl = new Label();
        userLbl.setText(Server.SERVER.getUserName());

        buyLivingSpaceLbl = new Label("Select the planet inside TableView and click the button ---> ");

        buyLivingSpaceBttn = new Button("BUY");
        buyLivingSpaceBttn.setTooltip(new Tooltip("buy a living space and book a flight"));
        buyLivingSpaceBttn.setPrefHeight(50);
        buyLivingSpaceBttn.setPrefWidth(150);

        filterBttn = new Button("FILTER");
        filterBttn.setTooltip(new Tooltip("show all habitable planets and satellites"));
        filterBttn.setPrefHeight(50);
        filterBttn.setPrefWidth(150);

        showAllSpendingsBttn = new Button("MY SPENDINGS");
        showAllSpendingsBttn.setTooltip(new Tooltip("living spaces and flights you bought"));
        showAllSpendingsBttn.setPrefHeight(50);
        showAllSpendingsBttn.setPrefWidth(150);
    }

    private void pos() {

        HBox hBox = new HBox();
        hBox.getChildren().addAll(userLbl, buyLivingSpaceLbl, buyLivingSpaceBttn);
        hBox.setSpacing(20);
        hBox.setAlignment(Pos.CENTER);
        hBox.setPadding(new Insets(25));

        VBox vBox = new VBox();
        vBox.getChildren().addAll(filterBttn, showAllSpendingsBttn);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(150);
        vBox.setPadding(new Insets(25));

        this.bp.setCenter(this.tvPlanet);
        this.bp.setTop(hBox);
        this.bp.setRight(vBox);
    }

    private void actions() {

        buyLivingSpaceBttn.setOnAction(e->{
            BuyLivingSpaceView buyLivingSpaceView = new BuyLivingSpaceView();
            buyLivingSpaceView.show();
        });

        filterBttn.setOnAction(new FilterController(this));

        showAllSpendingsBttn.setOnAction(new SpendingsController());
    }

}
