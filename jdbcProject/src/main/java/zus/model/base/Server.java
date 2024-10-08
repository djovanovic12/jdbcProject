package zus.model.base;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import zus.model.BoughtSpace;
import zus.model.LivingSpace;
import zus.model.Planet;
import zus.model.Spaceship;
import zus.model.utility.JDBCUtils;
import zus.view.BuyLivingSpaceView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Server {

    public static final Server SERVER = new Server();

    private final ObservableList<Planet> planets = FXCollections.observableArrayList();
    private final ObservableList<Spaceship> spaceships = FXCollections.observableArrayList();
    private final List<LivingSpace> livingSpaces = new ArrayList<>();
    private final List<BoughtSpace> boughtSpaces = new ArrayList<>();
    private int userID;
    private String userName;
    private int planetID;
    private int livingSpaceID;
    private int spaceShipID;
    private boolean occupied;
    private BuyLivingSpaceView buyLivingSpaceView;

    private Server() {
        this.setPlanets(JDBCUtils.selectAllFromPlanet());
    }

    public ObservableList<Planet> getPlanets() {
        return planets;
    }
    public ObservableList<Spaceship> getSpaceships() {
        return spaceships;
    }
    public List<LivingSpace> getLivingSpaces() {
        return livingSpaces;
    }
    public List<BoughtSpace> getBoughtSpaces() {
        return boughtSpaces;
    }

    public int getUserID() {
        return userID;
    }

    public boolean getOccupied() {
        return occupied;
    }

    public BuyLivingSpaceView getBuyLivingSpaceView() {
        return buyLivingSpaceView;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public void setBuyLivingSpaceView(BuyLivingSpaceView buyLivingSpaceView) {
        this.buyLivingSpaceView = buyLivingSpaceView;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPlanetID(int planetID) {
        this.planetID = planetID;
    }

    public void setLivingSpaceID(int livingSpaceID) {
        this.livingSpaceID = livingSpaceID;
    }

    public void setSpaceShipID(int spaceShipID) {
        this.spaceShipID = spaceShipID;
    }

    public void setPlanets(Collection<Planet> planets) {
        this.planets.clear();
        this.planets.addAll(planets);
    }

    public void setSpaceships(Collection<Spaceship> spaceships) {
        this.spaceships.clear();
        this.spaceships.addAll(spaceships);
    }

    public void setLivingSpaces(Collection<LivingSpace> livingSpaces) {
        this.livingSpaces.clear();
        this.livingSpaces.addAll(livingSpaces);
    }

    public void setBoughtSpaces(Collection<BoughtSpace> boughtSpaces) {
        this.boughtSpaces.clear();
        this.boughtSpaces.addAll(boughtSpaces);
    }

    public String getUserName() {
        return userName;
    }

    public int getPlanetID() {
        return planetID;
    }

    public int getLivingSpaceID() {
        return livingSpaceID;
    }

    public int getSpaceShipID() {
        return spaceShipID;
    }
}
