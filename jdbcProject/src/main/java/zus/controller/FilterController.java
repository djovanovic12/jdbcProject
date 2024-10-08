package zus.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import zus.model.base.Server;
import zus.model.utility.JDBCUtils;
import zus.view.HomepageView;

public class FilterController implements EventHandler<ActionEvent> {
    HomepageView homepageView;
    public FilterController(HomepageView homepageView) {
        this.homepageView = homepageView;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        Server.SERVER.setPlanets(JDBCUtils.filterPlanets());
        homepageView.getTvPlanet().setItems(Server.SERVER.getPlanets());
    }
}
