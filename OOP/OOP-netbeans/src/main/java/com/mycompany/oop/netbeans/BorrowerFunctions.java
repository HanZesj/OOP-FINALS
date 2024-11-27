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
        clearScreen();
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
        clearScreen();
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
        clearScreen();
        if (LoggedInBorrower == null) {
            System.out.println("You must be logged in to view your violations.");
            return;
        }
        System.out.println("\nViolations");
        System.out.println("Number of Violations: " + LoggedInBorrower.GetNumberOfViolations());
    }

    public void ViewBorrowerInformation() {
        clearScreen();
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
        clearScreen();
        if (LoggedInBorrower == null) {
            System.out.println("You must be logged in to borrow a book.");
            return;
        }
        ViewAvailableBooks();
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
        clearScreen();
        if (LoggedInBorrower == null) {
            System.out.println("You must be logged in to return a book.");
            return;
        }
        ViewBorrowedBooks();
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

    // Methods for managing borrowers
    public void AddBorrower() {
        clearScreen();
        System.out.println("\nRegister Borrower");
        String firstName = getStringInput("Enter first name: ");
        String lastName = getStringInput("Enter last name: ");
        String middleName = getStringInput("Enter middle name: ");
        String gender = getStringInput("Enter gender: ");
        String birthday = getBirthdayInput("Enter birthday (YYYY-MM-DD or YYYY/MM/DD): ");
        int contactNum = getIntInput("Enter contact number: ");
        String email = getEmailInput("Enter email: ");
        String address = getAddressInput("Enter address: ");
        Borrower borrower = new Borrower(library.GetNextBorrowerID(), firstName, lastName, middleName, gender, birthday, contactNum, email, address, library);
        library.AddBorrower(borrower);
        System.out.println("Borrower added successfully.");
        System.out.println("Your new Borrower ID is: " + borrower.GetBorrowerID());
    }

    public void BorrowerActionsMenu() {
        while (true) {
            clearScreen();
            System.out.println("\nWelcome " + LoggedInBorrower.GetFirstName() + " " + LoggedInBorrower.GetLastName());
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
                    LoggedInBorrower = null;
                    return; // Return to the main menu
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public void BorrowerLogin() {
        clearScreen();
        System.out.println("\nBorrower Login");
        int borrowerID = getIntInput("Enter borrower ID: ");
        Borrower borrower = library.FindBorrower(borrowerID);
        if (borrower == null) {
            System.out.println("Borrower not found.");
            return;
        }
        LoggedInBorrower = borrower;
        System.out.println("Login successful.");
        setLoggedInBorrower(borrower); // Ensure loggedInBorrower is set
        BorrowerActionsMenu();
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

    private String getEmailInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine();
                if (!input.contains("@")) {
                    throw new IllegalArgumentException("Email must contain '@'.");
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
                String input = scanner.nextLine();
                if (!input.matches("[a-zA-Z0-9\\s]+")) {
                    throw new IllegalArgumentException("Address must be alphanumeric.");
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
                String input = scanner.nextLine();
                if (!input.matches("\\d{4}[-/]\\d{2}[-/]\\d{2}")) {
                    throw new IllegalArgumentException("Birthday must be in the format YYYY-MM-DD or YYYY/MM/DD.");
                }
                return input;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void clearScreen() {
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