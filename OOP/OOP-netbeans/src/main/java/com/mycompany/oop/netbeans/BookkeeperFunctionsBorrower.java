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
        clearScreen();
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
        scanner.nextLine();
        String firstName = getStringInput("Enter new first name: ");
        String lastName = getStringInput("Enter new last name: ");
        String middleName = getStringInput("Enter new middle name: ");
        String gender = getStringInput("Enter new gender: ");
        String birthday = getBirthdayInput("Enter new birthday (YYYY-MM-DD or YYYY/MM/DD): ");
        scanner.nextLine();
        int contactNum = getIntInput("Enter new contact number: ");

        String email = getEmailInput("Enter new email: ");
        scanner.nextLine(); // Clear the newline character
        String address = getAddressInput("Enter new address: ");

        borrower.SetFirstName(firstName);
        borrower.SetLastName(lastName);
        borrower.SetMiddleName(middleName);
        borrower.SetGender(gender);
        borrower.SetBirthday(birthday);
        borrower.SetContactNum(contactNum);
        borrower.SetEmail(email);
        borrower.SetAddress(address);
        System.out.println("Borrower information updated successfully.");
        System.out.println("Press enter to continue...");
        scanner.nextLine(); // Wait for the user to press Enter
        scanner.nextLine(); // Clear the buffer
    }

    public void DeleteBorrower() {
        clearScreen();
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
        clearScreen();
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
        clearScreen();
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
            clearScreen();
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
                String input = scanner.nextLine().trim();
                if (input.isEmpty() || !input.matches("[a-zA-Z\\s]+")) {
                    throw new IllegalArgumentException("Input must be a non-empty string containing only letters and spaces.");
                }
                return input;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private String getEmailInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine().trim();
                if (input.isEmpty() || !input.contains("@")) {
                    throw new IllegalArgumentException("Email must contain '@' and cannot be empty.");
                }
                return input;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private String getBirthdayInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine().trim();
                if (!input.matches("\\d{4}[-/]\\d{2}[-/]\\d{2}")) {
                    throw new IllegalArgumentException("Birthday must be in the format YYYY-MM-DD or YYYY/MM/DD.");
                }
                return input;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private String getAddressInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine().trim();
                if (input.isEmpty() || !input.matches("[a-zA-Z0-9\\s]+")) {
                    throw new IllegalArgumentException("Address must be a non-empty alphanumeric string.");
                }
                return input;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void clearScreen() {
        System.out.print("\033c");
        System.out.flush();
        // Fallback to printing new lines if the escape sequence is not supported
        for (int i = 0; i < 50; ++i) System.out.println();
    }
}