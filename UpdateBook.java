import java.sql.*;
import java.util.Scanner;

public class UpdateBook {
    public static void updateBook(Scanner sc) {
        System.out.print("Enter Book ID to update: ");
        int bookId = sc.nextInt();
        sc.nextLine(); // consume newline

        Connection conn = DBConnection.connect();
        try {
            // Check if book exists
            String checkSql = "SELECT * FROM books WHERE book_id = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkSql);
            checkStmt.setInt(1, bookId);
            ResultSet rs = checkStmt.executeQuery();

            if(rs.next()) {
                System.out.println("Current Details:");
                System.out.println("Title: " + rs.getString("title"));
                System.out.println("Author: " + rs.getString("author"));
                System.out.println("Category: " + rs.getString("category"));
                System.out.println("ISBN: " + rs.getString("isbn"));
                System.out.println("Quantity: " + rs.getInt("quantity"));

                // Take new details
                System.out.print("Enter new title: ");
                String title = sc.nextLine();

                System.out.print("Enter new author: ");
                String author = sc.nextLine();

                System.out.print("Enter new category: ");
                String category = sc.nextLine();

                System.out.print("Enter new ISBN: ");
                String isbn = sc.nextLine();

                System.out.print("Enter new quantity: ");
                int quantity = sc.nextInt();
                sc.nextLine(); // consume newline

                // Update details
                String updateSql = "UPDATE books SET title = ?, author = ?, category = ?, isbn = ?, quantity = ?, available = ? WHERE book_id = ?";
                PreparedStatement updateStmt = conn.prepareStatement(updateSql);
                updateStmt.setString(1, title);
                updateStmt.setString(2, author);
                updateStmt.setString(3, category);
                updateStmt.setString(4, isbn);
                updateStmt.setInt(5, quantity);
                updateStmt.setInt(6, quantity); // reset available to total
                updateStmt.setInt(7, bookId);

                int rows = updateStmt.executeUpdate();
                if(rows > 0) {
                    System.out.println("Book updated successfully.");
                }
            } else {
                System.out.println("Book ID not found.");
            }

            conn.close();
        } catch(Exception e) {
            System.out.println(e);
        }
    }
}
