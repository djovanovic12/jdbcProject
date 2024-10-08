package zus.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Screen;
import javafx.stage.Stage;
import zus.model.BoughtSpace;
import zus.model.base.Server;

public class AllSpendingsView extends Stage {

    private final BorderPane bp = new BorderPane();

    private Label label;

    private final TableView<BoughtSpace> tableView = new BoughtSpacesTable(Server.SERVER.getBoughtSpaces());

    public AllSpendingsView() {

        super.setTitle("All spendings form");

        init();
        pos();

        super.setScene(new Scene(this.bp));

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        super.setWidth(screenBounds.getWidth() / 4);
        super.setHeight(screenBounds.getHeight() / 2);
        super.setX((screenBounds.getWidth() - super.getWidth()) / 4);
        super.setY((screenBounds.getHeight() - super.getHeight()) / 2);
    }

    private void init() {

        label = new Label("ALL YOUR SPENDINGS ON THIS APP:");
        label.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 17));
        label.setTextFill(Color.SEAGREEN);

    }

    private void pos() {

        HBox hBox = new HBox();
        hBox.getChildren().add(label);
        hBox.setAlignment(Pos.CENTER);
        hBox.setPadding(new Insets(20));

        this.bp.setTop(hBox);
        this.bp.setCenter(this.tableView);

    }

}
