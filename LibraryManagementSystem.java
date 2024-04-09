/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Admin
 */

import java.io.*;
import java.util.Scanner;

class Book {
    String bcode;
    String title;
    int quantity;
    int lended;
    double price;

    public Book(String bcode, String title, int quantity, int lended, double price) {
        this.bcode = bcode;
        this.title = title;
        this.quantity = quantity;
        this.lended = lended;
        this.price = price;
    }
    
    
}

class Node {
    Book data;
    Node next;

    public Node(Book data) {
        this.data = data;
        this.next = null;
    }
}

class BookLinkedList {
    Node head;

    public BookLinkedList() {
        this.head = null;
    }
    public void append(Book book) {
        Node newNode = new Node(book);
        if (head == null) {
            head = newNode;
            return;
        }
        Node current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
    }
}


public class LibraryManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Create a linked list to manage books
        BookLinkedList bookList = new BookLinkedList();
        
         while (true) {
            System.out.println("Book list (8 marks):");
            System.out.println("1.1. Load data from file");
            System.out.println("1.2. Input & add to the end");
            System.out.println("1.3. Display data");
            System.out.println("1.4. Save book list to file");
            System.out.println("1.5. Search by bcode");
            System.out.println("1.6. Delete by bcode");
            System.out.println("1.7. Sort by bcode");
            System.out.println("1.8. Input & add to beginning");
            System.out.println("1.9. Add after position k");
            System.out.println("1.10. Delete position k");
            System.out.println("Enter your choice (e.g., 1.1, 1.2, etc.): ");
            
            String choice = scanner.nextLine();

            switch (choice) {
                case "1.1":
                    loadDataFromFile(bookList);
                    break;
                case "1.2":
                    addBookToEnd(bookList);
                    break;
                case "1.3":
                    displayBookList(bookList);
                    break;
                case "1.4":
                    saveBookListToFile(bookList);
                    break;
                case "1.5":
                    searchByBcode(bookList);
                    break;
                case "1.6":
                    deleteByBcode(bookList);
                    break;
                case "1.7":
                    sortByBcode(bookList);
                    break;
                case "1.8":
                    addBookToBeginning(bookList);
                    break;
                case "1.9":
                    addBookAfterPositionK(bookList);
                    break;
                case "1.10":
                    deletePositionK(bookList);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Method to load data from file
    public static void loadDataFromFile(BookLinkedList bookList) {
        // Implementation of loading data from file
    }

    // Method to add a book to the end of the list
    public static void addBookToEnd(BookLinkedList bookList) {
        // Implementation of adding a book to the end
        Scanner sc = new Scanner(System.in);
        
        //get input of new book from user
        System.out.println("Enter book code: ");
        String bcode = sc.nextLine();
        System.out.println("Enter book title: ");
        String title = sc.nextLine();
        System.out.println("Enter quantity: ");
        int quantity = sc.nextInt();
        System.out.println("Enter quantity lended: ");
        int lened = sc.nextInt();
        System.out.println("Enter price: ");
        double price = sc.nextDouble();
        
        //Check if book already have
        Node current = bookList.head;
        while (current != null) {
            if (current.data.bcode.equals(bcode)) {
                System.out.println("Error: Book code already exist");
                return;
            }
            current = current.next;
        }
        //Create a new book object
        Book newBook = new Book(bcode, title, quantity, lened, price);
        
        //Add new book to list
        bookList.append(newBook);
        System.out.println("Book added successfully");
    }

    // Method to display the book list
    public static void displayBookList(BookLinkedList bookList) {
        // Implementation of displaying the book list
    }

    // Method to save book list to file
    public static void saveBookListToFile(BookLinkedList bookList) {
        // Implementation of saving book list to file
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter filename to save: ");
        String fileName = sc.nextLine();
        
        try {
            FileWriter writer = new FileWriter(fileName);
            
            Node current = bookList.head;
            while (current != null) {
                writer.write("Code | Title\t| Quantity | Lended | Price" + "\n");
                writer.write("-------------------------------------------------");
                writer.write(current.data.bcode + " | " +
                         current.data.title + " | " +
                         current.data.quantity + " | " +
                         current.data.lended + " | " +
                         current.data.price + "\n");
                current = current.next;
            }
            writer.close();
            System.out.println("Saved successfully in: " + fileName);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Method to search by bcode
    public static void searchByBcode(BookLinkedList bookList) {
        // Implementation of searching by bcode
    }

    // Method to delete by bcode
    public static void deleteByBcode(BookLinkedList bookList) {
        // Implementation of deleting by bcode
    }

    // Method to sort by bcode
    public static void sortByBcode(BookLinkedList bookList) {
        // Implementation of sorting by bcode
    }

    // Method to add a book to the beginning of the list
    public static void addBookToBeginning(BookLinkedList bookList) {
        // Implementation of adding a book to the beginning
    }

    // Method to add a book after position k
    public static void addBookAfterPositionK(BookLinkedList bookList) {
        // Implementation of adding a book after position k
    }

    // Method to delete position k
    public static void deletePositionK(BookLinkedList bookList) {
        // Implementation of deleting position k
    }
}
