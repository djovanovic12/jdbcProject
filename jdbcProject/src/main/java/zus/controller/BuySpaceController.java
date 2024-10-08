package zus.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import zus.model.base.Server;
import zus.view.BookAFlightView;

import javax.swing.*;

public class BuySpaceController implements EventHandler<ActionEvent> {
    public BuySpaceController() {

    }

    @Override
    public void handle(ActionEvent actionEvent) {
        if (Server.SERVER.getOccupied()) {
            JOptionPane.showMessageDialog(null,
                    "Living space already occupied!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE,
                    null);
        }
        else {
            BookAFlightView bookAFlightView = new BookAFlightView();
            bookAFlightView.show();
        }
    }
}
