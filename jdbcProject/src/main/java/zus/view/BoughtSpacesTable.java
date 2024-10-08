package zus.view;

import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import zus.model.BoughtSpace;

import java.util.List;

public class BoughtSpacesTable extends TableView<BoughtSpace> {
    public BoughtSpacesTable(List<BoughtSpace> values) {
        super(FXCollections.observableArrayList(values));

        TableColumn<BoughtSpace, String> tcPlanetName = new TableColumn<>("Planet Name");
        TableColumn<BoughtSpace, String> tcLivingSpaceName = new TableColumn<>("LivingSpace name");
        TableColumn<BoughtSpace, String> tcSpaceshipName = new TableColumn<>("Spaceship Name");
        TableColumn<BoughtSpace, Integer> tcNumberOfPeople = new TableColumn<>("Number of People");

        tcPlanetName.setCellValueFactory(new PropertyValueFactory<>("planetName"));
        tcLivingSpaceName.setCellValueFactory(new PropertyValueFactory<>("livingSpaceName"));
        tcSpaceshipName.setCellValueFactory(new PropertyValueFactory<>("spaceshipName"));
        tcNumberOfPeople.setCellValueFactory(new PropertyValueFactory<>("numberOfPeople"));

        super.getColumns().add(tcPlanetName);
        super.getColumns().add(tcLivingSpaceName);
        super.getColumns().add(tcSpaceshipName);
        super.getColumns().add(tcNumberOfPeople);
    }
}
