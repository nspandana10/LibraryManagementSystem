import java.sql.*;
import java.util.Scanner;

public class IssueBook {
    public static void issueBook(Scanner sc) {
        try {
            System.out.print("Enter User ID: ");
            int userId = sc.nextInt();

            System.out.print("Enter Book ID: ");
            int bookId = sc.nextInt();
            sc.nextLine(); // consume newline

            Connection conn = DBConnection.connect();

            // Check availability
            String checkSql = "SELECT available FROM books WHERE book_id = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkSql);
            checkStmt.setInt(1, bookId);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                int available = rs.getInt("available");
                if (available > 0) {
                    // Insert into transactions
                    String insertSql = "INSERT INTO transactions (book_id, user_id, issue_date, return_date, fine) VALUES (?, ?, CURDATE(), NULL, 0)";
                    PreparedStatement insertStmt = conn.prepareStatement(insertSql);
                    insertStmt.setInt(1, bookId);
                    insertStmt.setInt(2, userId);
                    insertStmt.executeUpdate();

                    // Update books table
                    String updateSql = "UPDATE books SET available = available - 1 WHERE book_id = ?";
                    PreparedStatement updateStmt = conn.prepareStatement(updateSql);
                    updateStmt.setInt(1, bookId);
                    updateStmt.executeUpdate();

                    System.out.println("Book issued successfully.");

                    insertStmt.close();
                    updateStmt.close();
                } else {
                    System.out.println("Book not available.");
                }
            } else {
                System.out.println("Book ID not found.");
            }

            rs.close();
            checkStmt.close();
            conn.close();
        } catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
