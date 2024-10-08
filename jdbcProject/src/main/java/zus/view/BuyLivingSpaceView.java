package zus.view;

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
import javafx.stage.Screen;
import javafx.stage.Stage;
import zus.controller.BuySpaceController;
import zus.model.LivingSpace;
import zus.model.base.Server;

public class BuyLivingSpaceView extends Stage {

    private final BorderPane bp = new BorderPane();
    private final TableView<LivingSpace> tvLivingSpaces = new LivingSpaceTable(Server.SERVER.getLivingSpaces());

    private Label buySpaceLbl;
    private Button buySpaceBttn;

    public BuyLivingSpaceView() {
        Server.SERVER.setBuyLivingSpaceView(this);
        super.setTitle("Living spaces form");

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

        buySpaceLbl = new Label("Select the living space inside TableView and click the button ---> ");

        buySpaceBttn = new Button("BUY AND BOOK");
        buySpaceBttn.setTooltip(new Tooltip("buy selected living space and book a flight to it"));

    }

    private void pos() {

        HBox hBox = new HBox();
        hBox.getChildren().addAll(buySpaceLbl, buySpaceBttn);
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(20);
        hBox.setPadding(new Insets(25));

        this.bp.setTop(hBox);
        this.bp.setCenter(this.tvLivingSpaces);

    }

    private void actions() {
        buySpaceBttn.setOnAction(new BuySpaceController());
    }

}
