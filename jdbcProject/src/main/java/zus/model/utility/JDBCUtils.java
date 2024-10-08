package zus.model.utility;

import zus.model.BoughtSpace;
import zus.model.LivingSpace;
import zus.model.Planet;
import zus.model.Spaceship;
import zus.model.base.Server;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class JDBCUtils {

    public static Connection connection = null;

    public static void connect() {
        Properties properties = new Properties();
        properties.put("user", "root");
        properties.put("password", "");
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/zus", properties);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Planet> selectAllFromPlanet() {
        List<Planet> planets = new ArrayList<>();
        String query = "SELECT * FROM planet JOIN mission USING(mission_id)";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int planetId = resultSet.getInt(2);
                String planetName = resultSet.getString(3);
                int nearestStarDistance = resultSet.getInt(4);
                int maxTemp = resultSet.getInt(5);
                int minTemp = resultSet.getInt(6);
                int gravFieldRange = resultSet.getInt(7);
                int rotSpeed = resultSet.getInt(8);
                boolean isFounded = resultSet.getBoolean(9);
                boolean isSatellite = resultSet.getBoolean(10);
                int deadPplUnder40Num = resultSet.getInt(11);
                LocalDate startDate = resultSet.getDate(13).toLocalDate();
                LocalDate endDate = resultSet.getDate(14).toLocalDate();
                Planet planet = new Planet(planetId, planetName, nearestStarDistance, maxTemp, minTemp, gravFieldRange, rotSpeed, isFounded, isSatellite, deadPplUnder40Num, startDate, endDate);
                planets.add(planet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return planets;
    }

    public static List<Spaceship> selectAllFromSpaceship() {
        List<Spaceship> spaceships = new ArrayList<>();
        String query = "select * from spaceship where living_space_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, Server.SERVER.getLivingSpaceID());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int spaceshipId = resultSet.getInt(1);
                String spaceshipName = resultSet.getString(2);
                LocalDate departureDate = resultSet.getDate(3).toLocalDate();
                int departureHours = resultSet.getInt(4);
                int departureMinutes = resultSet.getInt(5);
                int numberOfSeats = resultSet.getInt(6);
                Spaceship spaceship = new Spaceship(spaceshipId, spaceshipName, departureDate, departureHours, departureMinutes, numberOfSeats);
                spaceships.add(spaceship);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return spaceships;
    }

    public static List<LivingSpace> selectAllFromLivingSpace() {
        List<LivingSpace> livingSpaces = new ArrayList<>();
        String query = "select * from living_space where planet_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, Server.SERVER.getPlanetID());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int livingSpaceId = resultSet.getInt(1);
                String livingSpaceName = resultSet.getString(2);
                int numberOfPeople = resultSet.getInt(3);
                boolean occupied = resultSet.getBoolean(4);
                LivingSpace livingSpace = new LivingSpace(livingSpaceId, livingSpaceName, numberOfPeople, occupied);
                livingSpaces.add(livingSpace);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return livingSpaces;
    }

    public static void insertUser(String firstName, String lastName, String userName, String password,boolean passport) {
        String query = "insert into human (first_name, last_name, username, password, has_passport)" +
                "values (?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            connection.setAutoCommit(false);
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, userName);
            statement.setString(4, password);
            statement.setBoolean(5, passport);
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void getUserID(String userName) {
        String query = "select human_id from human where username = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, userName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int userID = resultSet.getInt(1);
                Server.SERVER.setUserID(userID);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void insertCompanion(String firstName, String lastName, int livingSpaceID, int humanID) {
        String query = "insert into companion (first_name, last_name, living_space_id, human_id)" +
                "values (?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            connection.setAutoCommit(false);
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setInt(3, livingSpaceID);
            statement.setInt(4, humanID);
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void setLivingSpaceOccupied(int livingSpaceID, boolean occupied, int number) {
        String query = "UPDATE living_space SET is_occupied = ?, number_of_people = ? where living_space_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            connection.setAutoCommit(false);
            statement.setBoolean(1, occupied);
            statement.setInt(2, number);
            statement.setInt(3, livingSpaceID);
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void insertSpaceShipAndLivingSpace(String userName, int spaceShipID, int livingSpaceID) {
        String sqlQuery = "UPDATE human SET spaceship_id = ?, living_space_id = ? WHERE username = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            connection.setAutoCommit(false);
            statement.setInt(1, spaceShipID);
            statement.setInt(2, livingSpaceID);
            statement.setString(3, userName);
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean validateUser(String userName, String password) {
        String sqlQuery = "select * from human where username = ? and password = ?";
        try {
            PreparedStatement preparedStatement = JDBCUtils.connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String username = resultSet.getString(4);
                Server.SERVER.setUserName(username);
                return true;
            }
            else {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Planet> filterPlanets() {
        List<Planet> planets = new ArrayList<>();
        String query = "select * from mission m" +
                "    join planet p on (m.mission_id = p.mission_id)" +
                "    join atmosphere a on(p.atmosphere_id = a.atmosphere_id)" +
                "    join gas g on(g.gas_symbol = a.gas_symbol)" +
                "    where nearest_star_distance between 100 and 200" +
                "    and min_temp between 150 and 250" +
                "    and max_temp between 250 and 350" +
                "    and max_temp - min_temp <= 120" +
                "    and oxygen_percentage between 0.15 and 0.25" +
                "    and is_deadly = 0" +
                "    and is_soluble = 1" +
                "    and oxygen_percentage + gas_percentage between 0.90 and 0.99" +
                "    and grav_field_range >= 1000" +
                "    and rot_speed between 25 and 35" +
                "    and dead_ppl_under_40_num <= 20";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int planetId = resultSet.getInt(4);
                String planetName = resultSet.getString(5);
                int nearestStarDistance = resultSet.getInt(6);
                int maxTemp = resultSet.getInt(7);
                int minTemp = resultSet.getInt(8);
                int gravFieldRange = resultSet.getInt(9);
                int rotSpeed = resultSet.getInt(10);
                boolean isFounded = resultSet.getBoolean(11);
                boolean isSatellite = resultSet.getBoolean(12);
                int deadPplUnder40Num = resultSet.getInt(13);
                LocalDate startDate = resultSet.getDate(2).toLocalDate();
                LocalDate endDate = resultSet.getDate(3).toLocalDate();
                Planet planet = new Planet(planetId, planetName, nearestStarDistance, maxTemp, minTemp, gravFieldRange, rotSpeed, isFounded, isSatellite, deadPplUnder40Num, startDate, endDate);
                planets.add(planet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return planets;
    }

    public static List<BoughtSpace> selectAllBoughtLivingSpaces(String userName) {
        List<BoughtSpace> boughtSpaces = new ArrayList<>();
        String query = "SELECT planet_name, living_space_name, spaceship_name, number_of_people FROM spaceship S" +
                "      JOIN human h ON (s.spaceship_id = h.spaceship_id)" +
                "      JOIN living_space L ON(S.living_space_id = L.living_space_id)" +
                "      JOIN planet P ON (L.planet_id = P.planet_id)" +
                "      WHERE username = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String planetName = resultSet.getString(1);
                String livingSpaceName = resultSet.getString(2);
                String spaceshipName = resultSet.getString(3);
                int numberOfPeople = resultSet.getInt(4);
                BoughtSpace boughtSpace = new BoughtSpace(planetName, livingSpaceName, spaceshipName, numberOfPeople);
                boughtSpaces.add(boughtSpace);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return boughtSpaces;
    }

    private JDBCUtils() {

    }

}
