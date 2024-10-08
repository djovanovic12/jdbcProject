package zus.view;

import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import zus.model.Spaceship;

import java.time.LocalDate;
import java.util.List;

public class SpaceshipTable extends TableView<Spaceship> {
    public SpaceshipTable(List<Spaceship> values) {
        super(FXCollections.observableArrayList(values));

        TableColumn<Spaceship, Integer> tcSpaceshipId = new TableColumn<>("ID");
        TableColumn<Spaceship, String> tcSpaceshipName = new TableColumn<>("Spaceship name");
        TableColumn<Spaceship, LocalDate> tcSpaceshipDate = new TableColumn<>("Departure date");
        TableColumn<Spaceship, Integer> tcSpaceshipTimeH = new TableColumn<>("Departure time [hours]");
        TableColumn<Spaceship, Integer> tcSpaceshipTimeM = new TableColumn<>("Departure time [minutes]");
        TableColumn<Spaceship, Integer> tcNumOfSeats = new TableColumn<>("Number of seats");

        tcSpaceshipId.setCellValueFactory(new PropertyValueFactory<>("spaceshipId"));
        tcSpaceshipName.setCellValueFactory(new PropertyValueFactory<>("spaceshipName"));
        tcSpaceshipDate.setCellValueFactory(new PropertyValueFactory<>("departureDate"));
        tcSpaceshipTimeH.setCellValueFactory(new PropertyValueFactory<>("departureHours"));
        tcSpaceshipTimeM.setCellValueFactory(new PropertyValueFactory<>("departureMinutes"));
        tcNumOfSeats.setCellValueFactory(new PropertyValueFactory<>("numberOfSeats"));

        super.getColumns().add(tcSpaceshipId);
        super.getColumns().add(tcSpaceshipName);
        super.getColumns().add(tcSpaceshipDate);
        super.getColumns().add(tcSpaceshipTimeH);
        super.getColumns().add(tcSpaceshipTimeM);
        super.getColumns().add(tcNumOfSeats);
    }
}