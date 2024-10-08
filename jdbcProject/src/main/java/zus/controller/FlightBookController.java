package zus.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import zus.model.base.Server;
import zus.model.utility.JDBCUtils;
import zus.view.BookAFlightView;

import javax.swing.*;

public class FlightBookController implements EventHandler<ActionEvent> {
    private int valid = 1;
    private TextField firstNamesTf;
    private TextField lastNamesTf;
    private final BookAFlightView bookAFlightView;

    public FlightBookController(TextField firstNamesTf, TextField lastNamesTf, BookAFlightView bookAFlightView) {
        this.firstNamesTf = firstNamesTf;
        this.lastNamesTf = lastNamesTf;
        this.bookAFlightView = bookAFlightView;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        String firstNames = firstNamesTf.getText();
        String lastNames = lastNamesTf.getText();
        String[] firstNameArray = {};
        String[] lastNameArray = {};
        int count = 0;
        if (!firstNames.isEmpty() && !lastNames.isEmpty()) {
            firstNameArray = firstNames.split(", ");
            lastNameArray = lastNames.split(", ");
            count = firstNameArray.length;
        }
        else {
            count = 1;
            valid = 0;
        }
        JDBCUtils.insertSpaceShipAndLivingSpace(Server.SERVER.getUserName(), Server.SERVER.getSpaceShipID(), Server.SERVER.getLivingSpaceID());
        if (valid == 1) {
            for (int i = 0; i < count; i++) {
                JDBCUtils.insertCompanion(firstNameArray[i], lastNameArray[i], Server.SERVER.getLivingSpaceID(), Server.SERVER.getUserID());
            }
        }
        JDBCUtils.setLivingSpaceOccupied(Server.SERVER.getLivingSpaceID(), true, count);
        Server.SERVER.setLivingSpaces(JDBCUtils.selectAllFromLivingSpace());
        JOptionPane.showMessageDialog(null,
                "Living space bought and booked a flight successfully!",
                "Success",
                JOptionPane.INFORMATION_MESSAGE,
                null);
        bookAFlightView.close();
        Server.SERVER.getBuyLivingSpaceView().close();
    }
}
