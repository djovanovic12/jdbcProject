package zus.view;

import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import zus.model.Planet;

import java.util.List;

public class PlanetsTable extends TableView<Planet> {
    public PlanetsTable(List<Planet> values) {
        super(FXCollections.observableArrayList(values));

        TableColumn<Planet, Integer> tcPlanetId = new TableColumn<>("ID");
        TableColumn<Planet, String> tcPlanetName = new TableColumn<>("Planet Name");
        TableColumn<Planet, Integer> tcMaxTemp = new TableColumn<>("Max temp [K]");
        TableColumn<Planet, Integer> tcMinTemp = new TableColumn<>("Min temp [K]");
        TableColumn<Planet, Boolean> tcFounded = new TableColumn<>("Founded");
        TableColumn<Planet, Boolean> tcSatellite = new TableColumn<>("Satellite");
        TableColumn<Planet, Integer> tcStartDate = new TableColumn<>("Start Date");
        TableColumn<Planet, Integer> tcEndDate = new TableColumn<>("End Date");

        tcPlanetId.setCellValueFactory(new PropertyValueFactory<>("planetId"));
        tcPlanetName.setCellValueFactory(new PropertyValueFactory<>("planetName"));
        tcMaxTemp.setCellValueFactory(new PropertyValueFactory<>("maxTemp"));
        tcMinTemp.setCellValueFactory(new PropertyValueFactory<>("minTemp"));
        tcFounded.setCellValueFactory(new PropertyValueFactory<>("founded"));
        tcSatellite.setCellValueFactory(new PropertyValueFactory<>("satellite"));
        tcStartDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        tcEndDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));

        super.getColumns().add(tcPlanetId);
        super.getColumns().add(tcPlanetName);
        super.getColumns().add(tcMaxTemp);
        super.getColumns().add(tcMinTemp);
        super.getColumns().add(tcFounded);
        super.getColumns().add(tcSatellite);
        super.getColumns().add(tcStartDate);
        super.getColumns().add(tcEndDate);
    }
}