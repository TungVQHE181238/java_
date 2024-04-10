/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Tunggg
 */
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

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
    // Create a new node with the given book
    Node newNode = new Node(book);
    
    // Check if the list is empty
    if (head == null) {
        // If it is, set the head to point to the new node
        head = newNode;
        return;
    }
    
    // If the list is not empty, traverse to the end
    Node current = head;
    while (current.next != null) {
        current = current.next;
    }
    
    // Set the next reference of the last node to the new node
    current.next = newNode;
}
    
    
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BookLinkedList bookList = new BookLinkedList();
        
        while (true) {
            System.out.println("");
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
                    del(bookList);
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
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the file name: ");
        String fileName = sc.nextLine();
        
        try {
            File file = new File(fileName);
            Scanner scFile = new Scanner(file);
            while (scFile.hasNextLine()) {
                String line = scFile.nextLine();
                String[] parts = line.split("\\|");
                String bcode = parts[0].trim();
                String title = parts[1].trim();
                int quantity = Integer.parseInt(parts[2].trim());
                double price = Double.parseDouble(parts[3].trim());
                int lended = 0;
                
                Book book = new Book(bcode, title, quantity, lended, price);
                bookList.append(book);
            }
            scFile.close();
            System.out.println("Data loaded successfully");
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (NumberFormatException e) {
            System.out.println("Invalid data");
        }
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
        System.out.println("Code | Title\t| Quantity | Lended | Price | Value");
        System.out.println("--------------------------------------------------");
        
        Node current = bookList.head;
        while (current != null) {
            double value = current.data.price * current.data.quantity;
            
            System.out.printf("%-4s | %-12s | %-8d | %-6d | %-6.2f | %-6.2f%n", current.data.bcode, current.data.title, current.data.quantity, current.data.lended, current.data.price, value);
            current = current.next;
        }
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
                writer.write(current.data.bcode + " | " +
                         current.data.title + " | " +
                         current.data.quantity + " | " +
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
    public static Node searchByBcode(BookLinkedList bookList) {
        // Implementation of searching by bcode
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter bcode: ");
        String sBcode = sc.nextLine();
        
        Node current = bookList.head;
        while (current != null) {
            if (current.data.bcode.equals(sBcode)) {
                System.out.println("Book found:");
                System.out.println("Code | Title\t| Quantity | Lended | Price");
                System.out.println("--------------------------------------------------");
                System.out.printf("%-4s | %-12s | %-8d | %-6d | %-6.2f%n",
                              current.data.bcode, current.data.title,
                              current.data.quantity, current.data.lended,
                              current.data.price);
                return current;
            }
            current = current.next;
        }
        System.out.println("Book not found");
        return null;
    }

    // Method to delete by bcode
    
    
    public static void del(BookLinkedList bookList) {
        // Implementation of deleting by bcode
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter bcode: ");
        String delBcode = sc.nextLine();
        
        Node current = bookList.head;
        Node prev = null;
        
        while (current != null) {
            if (current.data.bcode.equals(delBcode)) {
                if (prev == null) {
                    bookList.head = current.next;
                } else {
                    prev.next = current.next;
                }
                System.out.println("Book with bcde " + delBcode + " deleted");
                return;
            }
            prev = current;
            current = current.next;
        }
        System.out.println("Book not found"); 
    }

    // Method to sort by bcode
    public static void sortByBcode(BookLinkedList bookList) {
        // Implementation of sorting by bcode
        if (bookList.head == null || bookList.head.next == null) {
            //if the list empty or have one element
            return;
        }
        
        boolean swapped;
        Node current;
        Node last = null;
        
        do {
            swapped = false;
            current = bookList.head;
            
            while (current.next != last) {
                if (current.data.bcode.compareTo(current.next.data.bcode) > 0) {
                    Book temp = current.data;
                    current.data = current.next.data;
                    current.next.data = temp;
                    swapped = true;
                }
                current = current.next;
            }
            last = current;
        } while(swapped);
    }

    // Method to add a book to the beginning of the list
    public static void addBookToBeginning(BookLinkedList bookList) {
        // Implementation of adding a book to the beginning
        Scanner sc = new Scanner(System.in);
        
        //get new book from user
        System.out.println("Enter bcode: ");
        String bcode = sc.nextLine();
        System.out.println("Enter book title: ");
        String title = sc.nextLine();
        System.out.println("Enter quantity: ");
        int quantity = sc.nextInt();
        System.out.println("Enter amount lended: ");
        int lended = sc.nextInt();
        System.out.println("Enter price: ");
        double price = sc.nextDouble();
        
        Book newBook = new Book(bcode, title, quantity, lended, price);
        
        Node newNode = new Node(newBook);
        
        newNode.next = bookList.head;
        bookList.head = newNode;
        
        System.out.println("Book added to first of booklist");
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
