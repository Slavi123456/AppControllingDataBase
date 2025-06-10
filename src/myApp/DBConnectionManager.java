package myApp;


import myApp.utils.dataTypes.DataBase;

import java.sql.Connection; //class representing the link bettween Java and the DB
import java.sql.DriverManager; // JDBC class that loads the database driver and creates connections
import java.sql.SQLException; //base class for all JDBC-related errors
import java.util.ArrayList;
import java.util.List;

//Becasue for now the app is one-user only i can make singleton instead of pool connection
public class DBConnectionManager {
    private static DBConnectionManager instance;
    private static DbConnector dbConnector;

    private DBConnectionManager(){
        //these can be rewritten to throw exceptions which can be visualized by the app
        dbConnector = new DbConnector();
        dbConnector.openConnection();
    }

    public static DBConnectionManager getInstance (){
        if (instance == null ) { //could be added verification if the connection is closed
            instance = new DBConnectionManager();
        }
        return instance;
    }

    public Connection getConnection() {
        return dbConnector.getConnection();
    }

    public static void insert(String stmnt)  {
        dbConnector.insert(stmnt);
    }

    public List<DataBase> initilizeDatabase() {
        List<DataBase> attractions = new ArrayList<DataBase>();
        String stmnt = "SELECT n.*, " +
                "c.cave_longitude, c.elevation, c.humidity, c.temperature, c.date_of_discovery, " +
                "rf.rock_formation_area, rf.date_of_creation, " +
                "p.plant_order, p.genus, p.plant_family, " +
                "s.swamp_longitude, s.latitude, s.altitude, " +
                "nr.reserve_area, nr.date_Of_Creation " +
                "FROM NATURAL_ATTRACTIONS n " +
                "LEFT JOIN CAVE c ON n.name = c.name AND n.type = 'CAVE' " +
                "LEFT JOIN ROCK_FORMATION rf ON n.name = rf.name AND n.type = 'ROCK FORMATION' " +
                "LEFT JOIN PLANT p ON n.name = p.name AND n.type = 'PLANT' " +
                "LEFT JOIN SWAMP s ON n.name = s.name AND n.type = 'SWAMP' " +
                "LEFT JOIN NATURAL_RESERVE nr ON n.name = nr.name AND n.type = 'NATURAL RESERVE'";

        attractions.addAll(dbConnector.select(stmnt)); //its column sensitive)
        return attractions;
    }
    //maybe it could be better if :
    // 2-th when i merge this with the DbConnector
}
