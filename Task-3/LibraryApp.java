import java.util.*;

public class LibraryApp {

    /* --------- Book class --------- */
    static class Book {
        private String id;
        private String title;
        private String author;
        private boolean issued;
        private String issuedTo;

        public Book(String title, String author) {
            this.id = UUID.randomUUID().toString().substring(0, 6);
            this.title = title;
            this.author = author;
            this.issued = false;
            this.issuedTo = null;
        }

        public String getId() { return id; }
        public String getTitle() { return title; }
        public String getAuthor() { return author; }
        public boolean isIssued() { return issued; }
        public String getIssuedTo() { return issuedTo; }

        public void issue(String userId) {
            this.issued = true;
            this.issuedTo = userId;
        }

        public void returned() {
            this.issued = false;
            this.issuedTo = null;
        }

        @Override
        public String toString() {
            return String.format("[%s] %s by %s (%s)", id, title, author,
                    issued ? "Issued to " + issuedTo : "Available");
        }
    }

    /* --------- User class --------- */
    static class User {
        private String id;
        private String name;

        public User(String name) {
            this.id = UUID.randomUUID().toString().substring(0, 6);
            this.name = name;
        }

        public String getId() { return id; }
        public String getName() { return name; }

        @Override
        public String toString() {
            return String.format("[%s] %s", id, name);
        }
    }

    /* --------- Library --------- */
    static class Library {
        private Map<String, Book> books = new HashMap<>();
        private Map<String, User> users = new HashMap<>();

        // Add book
        public Book addBook(String title, String author) {
            Book b = new Book(title, author);
            books.put(b.getId(), b);
            return b;
        }

        // Add user
        public User addUser(String name) {
            User u = new User(name);
            users.put(u.getId(), u);
            return u;
        }

        // Issue book
        public String issueBook(String bookId, String userId) {
            Book b = books.get(bookId);
            User u = users.get(userId);
            if (b == null) return "❌ Book not found";
            if (u == null) return "❌ User not found";
            if (b.isIssued()) return "❌ Already issued";
            b.issue(userId);
            return "✅ Issued " + b.getTitle() + " to " + u.getName();
        }

        // Return book
        public String returnBook(String bookId) {
            Book b = books.get(bookId);
            if (b == null) return "❌ Book not found";
            if (!b.isIssued()) return "❌ Not currently issued";
            b.returned();
            return "✅ Returned " + b.getTitle();
        }

        public void listBooks() {
            if (books.isEmpty()) System.out.println("No books.");
            books.values().forEach(System.out::println);
        }

        public void listUsers() {
            if (users.isEmpty()) System.out.println("No users.");
            users.values().forEach(System.out::println);
        }
    }

    /* --------- CLI --------- */
    public static void main(String[] args) {
        Library lib = new Library();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Library Menu ---");
            System.out.println("1) Add Book");
            System.out.println("2) Add User");
            System.out.println("3) Issue Book");
            System.out.println("4) Return Book");
            System.out.println("5) List Books");
            System.out.println("6) List Users");
            System.out.println("0) Exit");
            System.out.print("Choice: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1" -> {
                    System.out.print("Title: ");
                    String t = sc.nextLine();
                    System.out.print("Author: ");
                    String a = sc.nextLine();
                    Book b = lib.addBook(t, a);
                    System.out.println("Added " + b);
                }
                case "2" -> {
                    System.out.print("User name: ");
                    String n = sc.nextLine();
                    User u = lib.addUser(n);
                    System.out.println("Added " + u);
                }
                case "3" -> {
                    lib.listBooks();
                    lib.listUsers();
                    System.out.print("Book ID: ");
                    String bid = sc.nextLine();
                    System.out.print("User ID: ");
                    String uid = sc.nextLine();
                    System.out.println(lib.issueBook(bid, uid));
                }
                case "4" -> {
                    lib.listBooks();
                    System.out.print("Book ID: ");
                    String bid = sc.nextLine();
                    System.out.println(lib.returnBook(bid));
                }
                case "5" -> lib.listBooks();
                case "6" -> lib.listUsers();
                case "0" -> { System.out.println("Goodbye!"); return; }
                default -> System.out.println("Invalid choice.");
            }
        }
    }
}
