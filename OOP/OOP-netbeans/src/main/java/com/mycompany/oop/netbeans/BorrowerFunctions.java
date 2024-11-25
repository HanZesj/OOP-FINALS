package com.mycompany.oop.netbeans;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BorrowerFunctions {
    private final Library library;
    private final Scanner scanner;
    private Borrower LoggedInBorrower;

    public BorrowerFunctions(Library library) {
        this.library = library;
        this.scanner = new Scanner(System.in);
    }

    public void setLoggedInBorrower(Borrower borrower) {
        this.LoggedInBorrower = borrower;
    }

    // Methods for viewing books and borrower information
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

    public void ViewBorrowedBooks() {
        if (LoggedInBorrower == null) {
            System.out.println("You must be logged in to view your borrowed books.");
            return;
        }
        System.out.println("\nBorrowed Books");
        for (String materialID : LoggedInBorrower.GetBorrowedMaterials()) {
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
        if (LoggedInBorrower == null) {
            System.out.println("You must be logged in to view your violations.");
            return;
        }
        System.out.println("\nViolations");
        System.out.println("Number of Violations: " + LoggedInBorrower.GetNumberOfViolations());
    }

    public void ViewBorrowerInformation() {
        if (LoggedInBorrower == null) {
            System.out.println("You must be logged in to view your information.");
            return;
        }
        System.out.println("\nMy Information");
        System.out.println("Borrower ID: " + LoggedInBorrower.GetBorrowerID());
        System.out.println("Name: " + LoggedInBorrower.GetFirstName() + " " + LoggedInBorrower.GetLastName());
        System.out.println("Gender: " + LoggedInBorrower.GetGender());
        System.out.println("Birthday: " + LoggedInBorrower.GetBirthday());
        System.out.println("Contact Number: " + LoggedInBorrower.GetContactNum());
        System.out.println("Email: " + LoggedInBorrower.GetEmail());
        System.out.println("Address: " + LoggedInBorrower.GetAddress());
        System.out.println("Number of Violations: " + LoggedInBorrower.GetNumberOfViolations());
    }

    // Methods for borrowing and returning books
    public void BorrowBook() {
        ViewAvailableBooks();
        if (LoggedInBorrower == null) {
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
        LoggedInBorrower.BorrowMaterial(materialID);
        material.SetCopies(material.GetCopies() - 1);
        System.out.println("Book borrowed successfully.");
    }

    public void ReturnBook() {
        ViewBorrowedBooks();
        if (LoggedInBorrower == null) {
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
        LoggedInBorrower.ReturnMaterial(materialID);
        material.SetCopies(material.GetCopies() + 1);
        System.out.println("Book returned successfully.");
    }

    // Utility methods for input
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

    private String getStringInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine();
                if (!input.matches("[a-zA-Z\\s]+")) {
                    throw new IllegalArgumentException("Input must be a string.");
                }
                return input;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}