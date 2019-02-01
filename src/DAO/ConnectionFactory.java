package DAO;

import java.sql.*;

public class ConnectionFactory {
    public static final String URL = "jdbc:mysql://localhost:8889/inventory_management";
    public static final String USER = "root";
    public static final  String PASS = "root";

    public static Connection getConnection()
    {
        try {
            //DriverManager.registerDriver(new Driver());
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException ex) {
            throw new RuntimeException("Error connecting to the database", ex);
        }
    }

    /**
     *Test the Connection
     */
    public static void main(String[] args) {
        Connection connection =  ConnectionFactory.getConnection();
        System.out.println("Database Connected to " +URL);
    }
}
