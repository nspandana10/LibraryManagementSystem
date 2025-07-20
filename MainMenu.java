import java.util.Scanner;

public class MainMenu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=== Library Management System ===");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Add User");
            System.out.println("4. Issue Book");
            System.out.println("5. Return Book");
            System.out.println("6. Update Book");
            System.out.println("7. Delete Book");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            try {
                switch(choice) {
                    case 1:
                        AddBook.addBook(sc);
                        break;
                    case 2:
                        ViewBooks.viewBooks();
                        break;
                    case 3:
                        AddUser.addUser(sc);
                        break;
                    case 4:
                        IssueBook.issueBook(sc);
                        break;
                    case 5:
                        ReturnBook.returnBook(sc);
                        break;
                    case 6:
                        UpdateBook.updateBook(sc);
                        break;
                    case 7:
                        DeleteBook.deleteBook(sc);
                        break;
                    case 8:
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            } catch(Exception e) {
                System.out.println("Error: " + e);
            }
        } while(choice != 8);

        sc.close();
    }
}
