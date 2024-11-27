package com.mycompany.oop.netbeans;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static Library library = new Library();
    public static Bookkeeper bookkeeper = new Bookkeeper(library);
    public final static BorrowerFunctions borrowerFunctions = new BorrowerFunctions(library);
    public final static BookkeeperFunctionsMaterials bookkeeperFunctionsMaterials = new BookkeeperFunctionsMaterials(library);
    public static Scanner scanner = new Scanner(System.in);
    public static Borrower LoggedInBorrower;

    public static void main(String args[]) {
        while (true) {
            int userType = GetUserType();
            if (userType == 1) {
                BookkeeperMenu();
            } else if (userType == 2) {
                BorrowerMenu();
            }
        }
    }

    // Methods for user type selection
    private static int GetUserType() {
        while (true) {
            ClearScreen();
            System.out.println("1. Book keeper");
            System.out.println("2. Borrower");
            System.out.println("3. Exit");
            System.out.print("Enter user type: ");
            int userType = scanner.nextInt();
            switch (userType) {
                case 1, 2 -> {
                    return userType;
                }
                case 3 -> {
                    System.out.println("Exiting the program.");
                    System.exit(0); // Terminate the program
                }
                default -> System.out.println("Invalid user type. Please try again.");
            }
        }
    }

    // Methods for bookkeeper menu
    private static void BookkeeperMenu() {
        while (true) {
            ClearScreen();
            System.out.println("---Book Keeper Interface---");
            System.out.println("1. Manage Borrowers.");
            System.out.println("2. Manage Materials.");
            System.out.println("3. Back to Main Menu.");
            int choice = getIntInput(":: ");
            switch (choice) {
                case 1 -> bookkeeper.ManageBorrowers();
                case 2 -> bookkeeperFunctionsMaterials.ManageMaterials();
                case 3 -> {
                    return; // Exit the BookkeeperMenu loop
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Methods for borrower menu
    private static void BorrowerMenu() {
        while (true) {
            ClearScreen();
            System.out.println("---Borrower Interface---");
            System.out.println("1. Login");
            System.out.println("2. Don't have an account? Register now.");
            System.out.println("3. Back to Main Menu.");
            int choice = getIntInput(":: ");
            switch (choice) {
                case 1 -> borrowerFunctions.BorrowerLogin();
                case 2 -> borrowerFunctions.AddBorrower();
                case 3 -> {
                    return; // Exit the BorrowerMenu loop
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
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

    private static void ClearScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
}