import java.sql.*;

public class DBConnection {
    public static Connection connect() {
        Connection conn = null;
        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to database
            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/library_db", "root", "spandana_SQL@999");

            System.out.println("Connected to DB successfully.");

        } catch(Exception e) {
            System.out.println(e);
        }
        return conn;
    }
}
