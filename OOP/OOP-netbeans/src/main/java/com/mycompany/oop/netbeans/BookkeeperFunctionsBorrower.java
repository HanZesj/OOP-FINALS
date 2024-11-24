package com.mycompany.oop.netbeans;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BookkeeperFunctionsBorrower {
    private final Library library;
    private final Scanner scanner;

    public BookkeeperFunctionsBorrower(Library library) {
        this.library = library;
        this.scanner = new Scanner(System.in);
    }

    public void EditBorrower() {
        ViewBorrowers();
        System.out.println("\nEdit Borrower");
        String borrowerID = getStringInput("Enter borrower ID: ");
        int borrowerIDInt = Integer.parseInt(borrowerID);
        Borrower borrower = library.FindBorrower(borrowerIDInt);
        if (borrower == null) {
            System.out.println("Borrower not found.");
            return;
        }
        String firstName = getStringInput("Enter new first name: ");
        String lastName = getStringInput("Enter new last name: ");
        String gender = getStringInput("Enter new gender: ");
        String birthday = getStringInput("Enter new birthday: ");
        String contactNum = getStringInput("Enter new contact number: ");
        String email = getStringInput("Enter new email: ");

        borrower.SetFirstName(firstName);
        borrower.SetLastName(lastName);
        borrower.SetGender(gender);
        borrower.SetBirthday(birthday);
        borrower.SetContactNum(contactNum);
        borrower.SetEmail(email);
    }

    public void DeleteBorrower() {
        ViewBorrowers();
        System.out.println("\nDelete Borrower");
        String borrowerID = getStringInput("Enter borrower ID: ");
        int borrowerIDInt = Integer.parseInt(borrowerID);
        Borrower borrower = library.FindBorrower(borrowerIDInt);
        if (borrower == null) {
            System.out.println("Borrower not found.");
            return;
        }
        library.RemoveBorrower(borrower.GetBorrowerID());
        System.out.println("Borrower deleted successfully.");
    }

    public void ViewBorrowers() {
        System.out.println("\nView Borrowers");
        for (Borrower borrower : library.GetBorrowers()) {
            System.out.println("Borrower ID: " + borrower.GetBorrowerID());
            System.out.println("Name: " + borrower.GetFirstName() + " " + borrower.GetLastName());
            System.out.println("Gender: " + borrower.GetGender());
            System.out.println("Birthday: " + borrower.GetBirthday());
            System.out.println("Contact Number: " + borrower.GetContactNum());
            System.out.println("Email: " + borrower.GetEmail());
            System.out.println("Address: " + borrower.GetAddress());
            System.out.println("Number of Violations: " + borrower.GetNumberOfViolations());
            for (String materialID : borrower.GetBorrowedMaterials()) {
                Material material = library.FindMaterial(Integer.parseInt(materialID));
                if (material != null) {
                    System.out.println("  Borrowed Material ID: " + material.GetMaterialID() + ", Title: " + material.GetTitle());
                }
            }
            System.out.println();
        }
    }

    public void SetBorrowerViolations() {
        ViewBorrowers();
        System.out.println("\nset borrower violations");
        String borrowerID = getStringInput("Enter borrower ID: ");
        int borrowerIDInt = Integer.parseInt(borrowerID);
        Borrower borrower = library.FindBorrower(borrowerIDInt);
        if (borrower == null) {
            System.out.println("Borrower not found.");
            return;
        }
        int numberOfViolations = getIntInput("Enter number of violations: ");
        borrower.SetNumberOfViolations(numberOfViolations);
        System.out.println("Number of violations set successfully.");
    }

    public void ManageBorrowers() {
        while (true) {
            System.out.println("\nManage Borrowers");
            System.out.println("1. Edit Borrower");
            System.out.println("2. Delete Borrower");
            System.out.println("3. View Borrowers");
            System.out.println("4. Set Borrower Violations");
            System.out.println("5. Back to Main Menu");
            int choice = getIntInput(":: ");
            switch (choice) {
                case 1 -> EditBorrower();
                case 2 -> DeleteBorrower();
                case 3 -> ViewBorrowers();
                case 4 -> SetBorrowerViolations();
                case 5 -> {
                    return; // Return to the main menu in Main.java
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
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