package zus.view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import zus.model.LivingSpace;
import zus.model.base.Server;
import zus.model.utility.JDBCUtils;

import java.util.List;

public class LivingSpaceTable extends TableView<LivingSpace> {
    public LivingSpaceTable(List<LivingSpace> values) {
        super(FXCollections.observableArrayList(values));

        TableColumn<LivingSpace, Integer> tcLivingSpaceId = new TableColumn<>("ID");
        TableColumn<LivingSpace, String> tcLivingSpaceName = new TableColumn<>("LivingSpace name");
        TableColumn<LivingSpace, Integer> tcNumOfPpl = new TableColumn<>("Number of people");
        TableColumn<LivingSpace, Integer> tcOccupied = new TableColumn<>("Occupied");

        tcLivingSpaceId.setCellValueFactory(new PropertyValueFactory<>("LivingSpaceId"));
        tcLivingSpaceName.setCellValueFactory(new PropertyValueFactory<>("LivingSpaceName"));
        tcNumOfPpl.setCellValueFactory(new PropertyValueFactory<>("NumberOfPeople"));
        tcOccupied.setCellValueFactory(new PropertyValueFactory<>("Occupied"));

        super.getColumns().add(tcLivingSpaceId);
        super.getColumns().add(tcLivingSpaceName);
        super.getColumns().add(tcNumOfPpl);
        super.getColumns().add(tcOccupied);

        super.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<LivingSpace>() {
            @Override
            public void changed(ObservableValue<? extends LivingSpace> observableValue, LivingSpace oldLivingSpace, LivingSpace newLivingSpace) {
                Server.SERVER.setLivingSpaceID(newLivingSpace.getLivingSpaceId());
                Server.SERVER.setOccupied(newLivingSpace.isOccupied());
                Server.SERVER.setSpaceships(JDBCUtils.selectAllFromSpaceship());
            }
        });
    }
}
