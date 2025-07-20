import java.sql.*;
import java.util.Scanner;

public class AddUser {
    public static void addUser(Scanner sc) {
        try {
            System.out.print("Enter name: ");
            String name = sc.nextLine();

            System.out.print("Enter email: ");
            String email = sc.nextLine();

            System.out.print("Enter contact: ");
            String contact = sc.nextLine();

            Connection conn = DBConnection.connect();

            String sql = "INSERT INTO users (name, email, contact) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setString(3, contact);

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("User added successfully.");
            }

            conn.close();
        } catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
