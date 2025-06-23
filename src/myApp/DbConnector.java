package myApp;

import com.ibm.db2.jcc.am.SqlIntegrityConstraintViolationException;
import myApp.utils.dataTypes.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;

//copied class from the lecture
public class DbConnector {
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;


    public void openConnection(){

        // Step 1: Load IBM DB2 JDBC driver

        try {

            DriverManager.registerDriver(new com.ibm.db2.jcc.DB2Driver());

        }

        catch(Exception cnfex) {

            System.out.println("Problem in loading or registering IBM DB2 JDBC driver");

            cnfex.printStackTrace();
        }

        // Step 2: Opening database connection


        try {

            //missing the the line for connection:
            //connection = Drivermanager.getConnection(url,user,password);
            statement = connection.createStatement();

        }

        catch(SQLException s){

            s.printStackTrace();

        }

    }

    public void closeConnection(){

        try {

            if(null != connection) {

                // cleanup resources, once after processing

                resultSet.close();

                statement.close();


                // and then finally close connection

                connection.close();

            }

        }

        catch (SQLException s) {

            s.printStackTrace();

        }

    }

    /// //--some changes---///////////
    public List<DataBase> select(String query) {
        List<DataBase> dataList = new ArrayList<>();

        try (ResultSet rs = statement.executeQuery(query)) {
            while (rs.next()) {
                String type = rs.getString("type");
                DataBase data;
                switch (type) {
                    case "ROCK FORMATION":
                        data = new DataRockFormation(
                                "",
                                rs.getString("name"),
                                rs.getInt("rock_formation_area"),
                                rs.getDate("date_of_creation") != null ?
                                        rs.getDate("date_of_creation").toLocalDate() : null

                        );
                        break;
                    case "NATURAL RESERVE":
                        data = new DataReserve(        "",
                                rs.getString("name"),
                                rs.getInt("reserve_area"),
                                rs.getDate("date_Of_Creation") != null ?
                                        rs.getDate("date_Of_Creation").toLocalDate() : null

                        );
                        break;
                    case "SWAMP":
                        data = new DataSwamp(        "",
                                rs.getString("name"),
                                rs.getInt("swamp_longitude"),
                                rs.getInt("latitude"),
                                rs.getInt("altitude")
                        );
                        break;
                    case "PLANT":
                        data = new DataPlant(        "",
                                rs.getString("name"),
                                rs.getString("plant_order"),
                                rs.getString("genus"),
                                rs.getString("plant_family")
                        );
                        break;
                    case "CAVE":
                        Date sqlDate = rs.getDate("date_of_discovery");
                        LocalDate localDate = (sqlDate != null) ? sqlDate.toLocalDate() : null;

                        data = new DataCave(        "",
                                rs.getString("name"),
                                rs.getInt("cave_longitude"),
                                rs.getInt("elevation"),
                                rs.getInt("humidity"),
                                rs.getInt("temperature"),
                                localDate
                        );
                        break;
                    default:
                        throw new IllegalArgumentException("Unknown user type: " + type);
                }
                dataList.add(data);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dataList;
    }

    public void insert(String stmnt) {

        try{

            statement.executeUpdate(stmnt);

        }
        catch (SqlIntegrityConstraintViolationException e) {
            //e.printStackTrace();
            /// ////////////////////////////////////
            // Rethrow with custom message or log it
            throw new RuntimeException("Cannot add duplicate to the database ");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Database error: " + e.getMessage());
        }

        System.out.println("Successfully inserted!");

    }

    public void delete(String stmnt) {

        try{

            statement.executeUpdate(stmnt);

        }

        catch (SQLException s){

            s.printStackTrace();

        }

        System.out.println("Successfully deleted!");

    }

    /// ///////////////---Added---//////////////////////
    public Connection getConnection() {
        return connection;
    }
}