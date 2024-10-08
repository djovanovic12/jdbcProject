package zus.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import zus.model.base.Server;
import zus.model.utility.JDBCUtils;
import zus.view.AllSpendingsView;

public class SpendingsController implements EventHandler<ActionEvent> {
    public SpendingsController() {

    }

    @Override
    public void handle(ActionEvent actionEvent) {
        Server.SERVER.setBoughtSpaces(JDBCUtils.selectAllBoughtLivingSpaces(Server.SERVER.getUserName()));
        AllSpendingsView allSpendingsView = new AllSpendingsView();
        allSpendingsView.show();
    }
}
