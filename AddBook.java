import java.sql.*;
import java.util.Scanner;

public class AddBook {
    public static void addBook(Scanner sc) {
        try {
            System.out.print("Enter title: ");
            String title = sc.nextLine();

            System.out.print("Enter author: ");
            String author = sc.nextLine();

            System.out.print("Enter category: ");
            String category = sc.nextLine();

            System.out.print("Enter ISBN: ");
            String isbn = sc.nextLine();

            System.out.print("Enter quantity: ");
            int quantity = sc.nextInt();
            sc.nextLine(); // consume newline

            Connection conn = DBConnection.connect();

            String sql = "INSERT INTO books (title, author, category, isbn, quantity, available) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, title);
            pstmt.setString(2, author);
            pstmt.setString(3, category);
            pstmt.setString(4, isbn);
            pstmt.setInt(5, quantity);
            pstmt.setInt(6, quantity);

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Book added successfully.");
            }

            conn.close();
        } catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
