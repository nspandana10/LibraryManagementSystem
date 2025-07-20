import java.sql.*;
import java.util.Scanner;

public class DeleteBook {
    public static void deleteBook(Scanner sc) {
        System.out.print("Enter Book ID to delete: ");
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
                System.out.println("Book found: " + rs.getString("title") + " by " + rs.getString("author"));

                System.out.print("Are you sure you want to delete this book? (yes/no): ");
                String confirm = sc.nextLine();

                if(confirm.equalsIgnoreCase("yes")) {
                    String deleteSql = "DELETE FROM books WHERE book_id = ?";
                    PreparedStatement deleteStmt = conn.prepareStatement(deleteSql);
                    deleteStmt.setInt(1, bookId);
                    int rows = deleteStmt.executeUpdate();

                    if(rows > 0) {
                        System.out.println("Book deleted successfully.");
                    }
                } else {
                    System.out.println("Deletion cancelled.");
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
