import java.sql.*;

public class ViewBooks {
    public static void viewBooks() {
        try {
            Connection conn = DBConnection.connect();

            String sql = "SELECT * FROM books";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            System.out.println("\nBook ID | Title | Author | Category | ISBN | Quantity | Available");
            System.out.println("-------------------------------------------------------------------------------");

            while(rs.next()) {
                int id = rs.getInt("book_id");
                String title = rs.getString("title");
                String author = rs.getString("author");
                String category = rs.getString("category");
                String isbn = rs.getString("isbn");
                int quantity = rs.getInt("quantity");
                int available = rs.getInt("available");

                System.out.println(id + " | " + title + " | " + author + " | " + category + " | " + isbn + " | " + quantity + " | " + available);
            }

            conn.close();
        } catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
