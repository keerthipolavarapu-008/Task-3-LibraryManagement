# 📚 Task 3 – Library Management System (OOP in Java)

## 🚀 Objective
Build a **Library Management System** in Java using **Object-Oriented Programming (OOP) principles**.  
The system supports **Add Book, Add User, Issue Book, Return Book** operations.

---

## 🛠 Features
1. **Add Book** → Add new books to the library.  
2. **Add User** → Register new users.  
3. **Issue Book** → Assign a book to a user (if available).  
4. **Return Book** → Return a previously issued book.  

---

## 📂 Files
- `LibraryApp.java` → Main Java program (single-file implementation).  

---

## ▶️ How to Run
```bash
# Compile
javac LibraryApp.java
--- Library Menu ---
1) Add Book
2) Add User
3) Issue Book
4) Return Book
5) List Books
6) List Users
0) Exit

Choice: 1
Title: Clean Code
Author: Robert Martin
✅ Added [a1b2c3] Clean Code by Robert Martin (Available)

Choice: 2
User name: Alice
✅ Added [u12345] Alice

Choice: 3
Book ID: a1b2c3
User ID: u12345
✅ Issued Clean Code to Alice


# Run
java LibraryApp
