package com.mycompany.oop.netbeans;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BorrowerFunctions {
    private final Library library;
    private final Scanner scanner;
    private Borrower loggedInBorrower;

    public BorrowerFunctions(Library library) {
        this.library = library;
        this.scanner = new Scanner(System.in);
    }

    public void setLoggedInBorrower(Borrower borrower) {
        this.loggedInBorrower = borrower;
    }

    public void BorrowerActionsMenu() {
        while (true) {
            System.out.println("\nWelcome " + loggedInBorrower.GetFirstName() + " " + loggedInBorrower.GetLastName());
            System.out.println("1. View available books.");
            System.out.println("2. Borrow a book.");
            System.out.println("3. Return a book.");
            System.out.println("4. View borrowed books.");
            System.out.println("5. View violations.");
            System.out.println("6. View your information.");
            System.out.println("7. Logout.");
            int choice = getIntInput(":: ");
            switch (choice) {
                case 1 -> ViewAvailableBooks();
                case 2 -> BorrowBook();
                case 3 -> ReturnBook();
                case 4 -> ViewBorrowedBooks();
                case 5 -> ViewViolations();
                case 6 -> ViewBorrowerInformation();
                case 7 -> {
                    loggedInBorrower = null;
                    return; // Return to the main menu
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public void ViewAvailableBooks() {
        System.out.println("\nAvailable Books");
        for (Material material : library.GetMaterials()) {
            if (material.GetCopies() > 0) {
                System.out.println("Material ID: " + material.GetMaterialID());
                System.out.println("Title: " + material.GetTitle());
                System.out.println("Author: " + material.GetAuthor());
                System.out.println("Publisher: " + material.GetPublisher());
                System.out.println("Year Published: " + material.GetYearPublished());
                System.out.println("Copies: " + material.GetCopies());
                System.out.println("Type: " + material.GetType());
                System.out.println();
            }
        }
    }

    public void BorrowBook() {
        if (loggedInBorrower == null) {
            System.out.println("You must be logged in to borrow a book.");
            return;
        }
        System.out.println("\nBorrow Book");
        int materialID = getIntInput("Enter Material ID: ");
        Material material = library.FindMaterial(materialID);
        if (material == null) {
            System.out.println("Material not found.");
            return;
        }
        if (material.GetCopies() <= 0) {
            System.out.println("No copies available.");
            return;
        }
        loggedInBorrower.BorrowMaterial(materialID);
        material.SetCopies(material.GetCopies() - 1);
        System.out.println("Book borrowed successfully.");
    }

    public void ReturnBook() {
        if (loggedInBorrower == null) {
            System.out.println("You must be logged in to return a book.");
            return;
        }
        System.out.println("\nReturn Book");
        int materialID = getIntInput("Enter Material ID: ");
        Material material = library.FindMaterial(materialID);
        if (material == null) {
            System.out.println("Material not found.");
            return;
        }
        loggedInBorrower.ReturnMaterial(materialID);
        material.SetCopies(material.GetCopies() + 1);
        System.out.println("Book returned successfully.");
    }

    public void ViewBorrowedBooks() {
        if (loggedInBorrower == null) {
            System.out.println("You must be logged in to view your borrowed books.");
            return;
        }
        System.out.println("\nBorrowed Books");
        for (String materialID : loggedInBorrower.GetBorrowedMaterials()) {
            Material material = library.FindMaterial(Integer.parseInt(materialID));
            if (material != null) {
                System.out.println("Material ID: " + material.GetMaterialID());
                System.out.println("Title: " + material.GetTitle());
                System.out.println("Author: " + material.GetAuthor());
                System.out.println("Publisher: " + material.GetPublisher());
                System.out.println("Year Published: " + material.GetYearPublished());
                System.out.println("Type: " + material.GetType());
                System.out.println();
            }
        }
    }

    public void ViewViolations() {
        if (loggedInBorrower == null) {
            System.out.println("You must be logged in to view your violations.");
            return;
        }
        System.out.println("\nViolations");
        System.out.println("Number of Violations: " + loggedInBorrower.GetNumberOfViolations());
    }

    public void ViewBorrowerInformation() {
        if (loggedInBorrower == null) {
            System.out.println("You must be logged in to view your information.");
            return;
        }
        System.out.println("\nMy Information");
        System.out.println("Borrower ID: " + loggedInBorrower.GetBorrowerID());
        System.out.println("Name: " + loggedInBorrower.GetFirstName() + " " + loggedInBorrower.GetLastName());
        System.out.println("Gender: " + loggedInBorrower.GetGender());
        System.out.println("Birthday: " + loggedInBorrower.GetBirthday());
        System.out.println("Contact Number: " + loggedInBorrower.GetContactNum());
        System.out.println("Email: " + loggedInBorrower.GetEmail());
        System.out.println("Address: " + loggedInBorrower.GetAddress());
        System.out.println("Number of Violations: " + loggedInBorrower.GetNumberOfViolations());
    }

    private int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Error: Input must be an integer.");
                scanner.next(); // Clear the invalid input
            }
        }
    }
}