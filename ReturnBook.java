import java.sql.*;
import java.util.Scanner;

public class ReturnBook {
    public static void returnBook(Scanner sc) {
        try {
            System.out.print("Enter Transaction ID: ");
            int transactionId = sc.nextInt();
            sc.nextLine(); // consume newline

            Connection conn = DBConnection.connect();

            // Get book_id and issue_date
            String selectSql = "SELECT book_id, issue_date FROM transactions WHERE transaction_id = ? AND return_date IS NULL";
            PreparedStatement selectStmt = conn.prepareStatement(selectSql);
            selectStmt.setInt(1, transactionId);
            ResultSet rs = selectStmt.executeQuery();

            if (rs.next()) {
                int bookId = rs.getInt("book_id");
                Date issueDate = rs.getDate("issue_date");

                // Calculate fine (Rs.10/day after 7 days)
                java.util.Date today = new java.util.Date();
                long diff = today.getTime() - issueDate.getTime();
                int days = (int) (diff / (1000 * 60 * 60 * 24));
                double fine = 0;
                if (days > 7) {
                    fine = (days - 7) * 10;
                }

                // Update transactions table
                String updateSql = "UPDATE transactions SET return_date = CURDATE(), fine = ? WHERE transaction_id = ?";
                PreparedStatement updateStmt = conn.prepareStatement(updateSql);
                updateStmt.setDouble(1, fine);
                updateStmt.setInt(2, transactionId);
                updateStmt.executeUpdate();

                // Update books table
                String bookSql = "UPDATE books SET available = available + 1 WHERE book_id = ?";
                PreparedStatement bookStmt = conn.prepareStatement(bookSql);
                bookStmt.setInt(1, bookId);
                bookStmt.executeUpdate();

                System.out.println("Book returned successfully. Fine: Rs. " + fine);

                updateStmt.close();
                bookStmt.close();
            } else {
                System.out.println("Transaction ID not found or book already returned.");
            }

            rs.close();
            selectStmt.close();
            conn.close();
        } catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
