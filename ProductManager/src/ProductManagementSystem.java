
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Admin
 */
class Product {
    String pcode;
    String pro_name;
    int quantity;
    int saled;
    double price;

    public Product(String pcode, String pro_name, int quantity, int saled, double price) {
        this.pcode = pcode;
        this.pro_name = pro_name;
        this.quantity = quantity;
        this.saled = saled;
        this.price = price;
    }
}

class Node {
    Product data;
    Node next;
    
    public Node(Product data) {
        this.data = data;
        this.next = null;
    }
}

class ProductLinkedList {
    Node head;

    public ProductLinkedList() {
        this.head = null;
    }
    
    public void append(Product product) {
        //Create a new node with given product
        Node newNode = new Node(product);
        
        //if the list empty
        if(head == null) {
            head = newNode;
            return;
        }
        
        //if not, traverse to end
        Node current = head;
        while (current.next != null) {
            current = current.next;
        }
        
        //after traverse to end => create a reference to new node
        current.next = newNode;
    }
    
    public void display() {
        Node current = head;
        while (current != null) {
            System.out.printf("%-4s | %-12s | %-6d | %-6d | %-6.2f%n", current.data.pcode, current.data.pro_name, current.data.quantity, current.data.saled, current.data.price);
            current = current.next;
        }
    }
}

public class ProductManagementSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ProductLinkedList prodList = new ProductLinkedList();
        
        while(true) {
            System.out.println("");
            System.out.println("11. Load data from file and display");
            System.out.println("12. Delete pcode=1 and display");
            System.out.println("13. Edit quantity=77 in pcode=4");
            System.out.println("14. Add (5,F,3,2,1) and display");
            System.out.println("15. Sort ascending by saled");
            System.out.println("Your choice: ");
            
            String choice = sc.nextLine();
            
            switch(choice) {
                case "11":
                    loadAndDisplay(prodList);
                    break;
                case "12":
                    delAndDis(prodList);
                    break;
                case "13":
                    editQuant(prodList);
                    break;
                case "14":
                    addPro(prodList);
                    break;
                default:
                    System.out.println("Please try again.");
            }
        } 
    }
    
    public static void loadAndDisplay(ProductLinkedList prodList) {
        String fileName = "C:\\Users\\Admin\\Documents\\NetBeansProjects\\ProductManager\\src\\product1.txt";
        
        try {
            File file = new File(fileName);
            Scanner scFile = new Scanner(file);
            while (scFile.hasNextLine()) {
                String line = scFile.nextLine();
                String[] parts = line.split("\\|");
                String pcode = parts[0].trim();
                String pro_name = parts[1].trim();
                int quantity = Integer.parseInt(parts[2].trim());
                int saled = Integer.parseInt(parts[3].trim());
                double price = Double.parseDouble(parts[4].trim());
                
                Product newProduct = new Product(pcode, pro_name, quantity, saled, price);
                prodList.append(newProduct);
            }
            scFile.close();
            prodList.display();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid data.");
        }       
    }
    
    public static void delAndDis(ProductLinkedList prodList) {
        String delCode = "1";
        
        Node current = prodList.head;
        Node prev = null;
        
        while (current != null) {
            if (current.data.pcode.equals(delCode)) {
                if(prev == null) {
                    prodList.head = current.next;
                } else {
                    prev.next = current.next;
                }
                prodList.display();
                return;        
            }
            prev = current;
            current = current.next;
        }      
    }
    
    public static void editQuant(ProductLinkedList prodList) {
        String pCode = "4";
        
        Node current = prodList.head;
        
        while(current != null) {
            if (current.data.pcode.equals(pCode)) {
                current.data.quantity = 77;
            }
           current = current.next;
        }
        prodList.display();
        return;
    }
    
    public static void addPro(ProductLinkedList prodList) {
        Product newPro = new Product("5", "F", 3, 2, 1);
        prodList.append(newPro);
        prodList.display();
    }
}


