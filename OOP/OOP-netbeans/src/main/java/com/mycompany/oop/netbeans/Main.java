package com.mycompany.oop.netbeans;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static Library library = new Library();
    public static Bookkeeper bookkeeper = new Bookkeeper(library);
    public final static BorrowerFunctions borrowerFunctions = new BorrowerFunctions(library);
    public static Scanner scanner = new Scanner(System.in);
    public static Borrower LoggedInBorrower;

    public static void main(String args[]) {
        // LoadLibraryData();
        int userType = GetUserType();
        if (userType == 1) {
            BookkeeperMenu();
        } else if (userType == 2) {
            BorrowerMenu();
        }
    }

    // Methods for user type selection
    private static int GetUserType() {
        while (true) {
            System.out.println("1. Bookkeeper");
            System.out.println("2. Borrower");
            System.out.println("3. Exit");
            System.out.print("Enter user type: ");
            int userType = scanner.nextInt();
            if (userType == 1 || userType == 2) {
                return userType;
            } else if (userType == 3) {
                System.out.println("Exiting the program.");
                System.exit(0); // Terminate the program
            } else {
                System.out.println("Invalid user type. Please try again.");
            }
        }
    }

    // Methods for bookkeeper menu
    private static void BookkeeperMenu() {
        while (true) {
            System.out.println("---Book Keeper Interface---");
            System.out.println("1. Manage Borrowers.");
            System.out.println("2. Manage Materials.");
            System.out.println("3. Back to Main Menu.");
            int choice = getIntInput(":: ");
            switch (choice) {
                case 1 -> bookkeeper.ManageBorrowers();
                case 2 -> ManageMaterials();
                case 3 -> {
                    return;
                } // Exit the BookkeeperMenu loop
            }
        }
    }

    // Methods for managing borrowers
    private static void AddBorrower() {
        // add a borrower in the system function
        System.out.println("\nRegister Borrower");
        String firstName = GetStringInput("Enter first name: ");
        scanner.nextLine();
        String lastName = GetStringInput("Enter last name: ");
        String middleName = GetStringInput("Enter middle name: ");
        String gender = GetStringInput("Enter gender: ");
        int birthday = getIntInput("Enter birthday (YYYY/MM/DD): ");
        scanner.nextLine();
        int contactNum = getIntInput("Enter contact number: ");
        scanner.nextLine();
        String email = GetStringInput("Enter email: ");
        String address = GetStringInput("Enter address: ");
        Borrower borrower = new Borrower(library.GetNextBorrowerID(), firstName, lastName, middleName, gender, birthday, contactNum, email, address, library);
        library.AddBorrower(borrower);
        System.out.println("Borrower added successfully.");
        System.out.println("Your new Borrower ID is: " + borrower.GetBorrowerID());
    }

//* */
private static void ManageMaterials() {
    while (true) {
        System.out.println("\nManage Materials");
        System.out.println("1. Add Material");
        //System.out.println("2. Edit Material");
        System.out.println("3. Delete Material");
        System.out.println("4. View Materials");
        System.out.println("5. Back to Main Menu");
        int choice = getIntInput(":: ");
        switch (choice) {
            case 1 -> bookkeeper.AddMaterial();
            //case 2 -> bookkeeper.EditMaterial();
            case 3 -> bookkeeper.DeleteMaterial();
            case 4 -> bookkeeper.ViewMaterials();
            case 5 -> {
                return;
            }
        }
    }
}

    // Methods for borrower menu
    private static void BorrowerMenu() {
        while (true) {
            System.out.println("---Borrower Interface---");
            System.out.println("1. Login");
            System.out.println("2. Don't have an account? Register now.");
            System.out.println("3. Back to Main Menu.");
            int choice = getIntInput(":: ");
            switch (choice) {
                case 1 -> BorrowerLogin();
                case 2 -> AddBorrower();
                case 3 -> {
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void BorrowerActionsMenu() {
        while (true) {
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
                case 1 -> borrowerFunctions.ViewAvailableBooks();
                case 2 -> borrowerFunctions.BorrowBook();
                case 3 -> borrowerFunctions.ReturnBook();
                case 4 -> borrowerFunctions.ViewBorrowedBooks();
                case 5 -> borrowerFunctions.ViewViolations();
                case 6 -> borrowerFunctions.ViewBorrowerInformation();
                case 7 -> {
                    LoggedInBorrower = null;
                    return; // Return to the main menu
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void BorrowerLogin() {
        System.out.println("\nBorrower Login");
        int borrowerID = getIntInput("Enter borrower ID: ");
        Borrower borrower = library.FindBorrower(borrowerID);
        if (borrower == null) {
            System.out.println("Borrower not found.");
            return;
        }
        LoggedInBorrower = borrower;
        System.out.println("Login successful.");
        borrowerFunctions.setLoggedInBorrower(borrower); // Ensure loggedInBorrower is set
        BorrowerActionsMenu();
    }

    // Utility methods for input
    private static int getIntInput(String prompt) {
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

    public static String GetStringInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine();
                if (!input.matches("[a-zA-Z\\s]+") && !input.isEmpty()) {
                    System.out.println("Input must be a string. Please try again.");
                }
                return input;
            } catch (Exception e) {
                System.out.println("An error occurred. Please try again.");
            }
        }
    }
}