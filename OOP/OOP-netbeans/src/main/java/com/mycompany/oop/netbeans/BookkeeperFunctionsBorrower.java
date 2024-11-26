package com.mycompany.oop.netbeans;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class BookkeeperFunctionsBorrower {
    private final Library library;
    private final Scanner scanner;

    public BookkeeperFunctionsBorrower(Library library) {
        this.library = library;
        this.scanner = new Scanner(System.in);
    }

    public void EditBorrower() {
        ClearScreen();
        List<Borrower> borrowers = library.GetBorrowers();
        if (borrowers.isEmpty()) {
            System.out.println("No borrowers in the system yet.");
            return;
        }
        ViewBorrowers();
        System.out.println("\nEdit Borrower");
        int borrowerIDInt = getIntInput("Enter borrower ID: ");
        Borrower borrower = library.FindBorrower(borrowerIDInt);
        if (borrower == null) {
            System.out.println("Borrower not found.");
            return;
        }
        String firstName = getStringInput("Enter new first name: ");
        String lastName = getStringInput("Enter new last name: ");
        String gender = getStringInput("Enter new gender: ");
        String birthday = getStringInput("Enter new birthday: ");
        int contactNum = getIntInput("Enter new contact number: ");
        String email = getStringInput("Enter new email: ");

        borrower.SetFirstName(firstName);
        borrower.SetLastName(lastName);
        borrower.SetGender(gender);
        borrower.SetBirthday(birthday);
        borrower.SetContactNum(contactNum);
        borrower.SetEmail(email);
        System.out.println("Borrower information updated successfully.");
    }

    public void DeleteBorrower() {
        ClearScreen();
        List<Borrower> borrowers = library.GetBorrowers();
        if (borrowers.isEmpty()) {
            System.out.println("No borrowers in the system yet.");
            return;
        }
        ViewBorrowers();
        System.out.println("\nDelete Borrower");
        int borrowerIDInt = getIntInput("Enter borrower ID: ");
        Borrower borrower = library.FindBorrower(borrowerIDInt);
        if (borrower == null) {
            System.out.println("Borrower not found.");
            return;
        }
        library.RemoveBorrower(borrower.GetBorrowerID());
        System.out.println("Borrower deleted successfully.");
    }

    public void SetBorrowerViolations() {
        ClearScreen();
        List<Borrower> borrowers = library.GetBorrowers();
        if (borrowers.isEmpty()) {
            System.out.println("No borrowers in the system yet.");
            return;
        }
        ViewBorrowers();
        System.out.println("\nSet Borrower Violations");
        int borrowerIDInt = getIntInput("Enter borrower ID: ");
        Borrower borrower = library.FindBorrower(borrowerIDInt);
        if (borrower == null) {
            System.out.println("Borrower not found.");
            return;
        }
        int numberOfViolations = getIntInput("Enter number of violations: ");
        borrower.SetNumberOfViolations(numberOfViolations);
        System.out.println("Number of violations set successfully.");
    }

    public void ViewBorrowers() {
        ClearScreen();
        System.out.println("\nView Borrowers");
        List<Borrower> borrowers = library.GetBorrowers();
        if (borrowers.isEmpty()) {
            System.out.println("No borrowers in the system yet.");
            return;
        }
        for (Borrower borrower : borrowers) {
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

    public void ManageBorrowers() {
        while (true) {
            ClearScreen();
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

    private static void ClearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            // Fallback to printing new lines if the clear command fails
            for (int i = 0; i < 50; ++i) System.out.println();
        }
    }
}