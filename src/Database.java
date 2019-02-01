import java.sql.*;

public class Database {
    public static final String URL = "jdbc:mysql://localhost:8889/test";
    public static final String USER = "root";
    public static final  String PASS = "root";
    public static void main(String[] args) {
        try {
            //Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:8889/test", "root", "root");
            Connection myConn = DriverManager.getConnection(URL, USER, PASS);
            //create statement
            Statement myStmt = myConn.createStatement();
            //execute sql query
            ResultSet myRs = myStmt.executeQuery("select * from user");

            //results set

            while (myRs.next()) {
                System.out.println(myRs.getString("userId")+ " , "+myRs.getString("userName")+"," +myRs.getString("password")+ " , "+myRs.getString("email"));
            }
        }
        catch (Exception exc) {
            //System.out.println("Error");
            exc.printStackTrace();
            System.out.println(exc.getMessage());
        }
    }
}
