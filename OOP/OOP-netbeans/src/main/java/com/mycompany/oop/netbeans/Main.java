package com.mycompany.oop.netbeans;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static Library library = new Library();
    public static Bookkeeper bookkeeper = new Bookkeeper(library);
    public static BorrowerFunctions borrowerFunctions = new BorrowerFunctions(library);
    public static Scanner scanner = new Scanner(System.in);
    public static Borrower LoggedInBorrower;

    public static void main(String args[]){
        // LoadLibraryData();
        int userType = GetUserType();
        if(userType == 1){
            BookkeeperMenu();
        }else if(userType == 2){
            BorrowerMenu();
        }
    }

    private static int GetUserType(){
        while(true){
            System.out.println("1. Bookkeeper");
            System.out.println("2. Borrower");
            System.out.print("Enter user type: ");
            int userType = scanner.nextInt();
            if(userType == 1 || userType == 2){
                return userType;
            }else{
                System.out.println("Invalid user type. Please try again.");
            }
        }
    }

    private static int getIntInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter an integer.");
            scanner.next(); // discard invalid input
            System.out.print(prompt);
        }
        return scanner.nextInt();
    }

    public static String GetStringInput(String prompt){
        while(true){
            try{
                System.out.print(prompt);
                String input = scanner.nextLine();
                if(!input.matches("[a-zA-Z\\s]+") && !input.isEmpty()){
                    System.out.println("input must be string. Please try again.");
                    }
                    return input;
                } catch (Exception e) {
                    System.out.println("An error occurred. Please try again.");
                }
            }
        }

    private static void BookkeeperMenu(){
        while(true){
            System.out.println("---Book Keeper Interface---");
            System.out.println("1. Manage Borrowers.");
            System.out.println("2. Manage Materials.");
            System.out.println("3. Exit.");
            int choice = getIntInput(":: ");
            switch (choice) {
                case 1 -> bookkeeper.ManageBorrowers();
                case 2 -> ManageMaterials();
                case 3 -> System.exit(0);
            }
        }
    }

    private static void AddBorrower(){}

    private static void ManageMaterials(){
        
    }

    private static void BorrowerMenu(){
        while(true){
            System.out.println("---Borrower Interface---");
            System.out.println("1. Login");
            System.out.println("2. Don't have an account? Register now.");
            System.out.println("3. exit.");
            int choice = getIntInput(":: ");
            switch(choice){
                case 1 -> BorrowerLogin();
                case 2 -> AddBorrower(); //register();
                case 3 -> System.exit(0);
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void BorrowerLogin(){
        System.out.println("\nBorrower Login");
        int borrowerID = getIntInput("Enter borrower ID: ");
        Borrower borrower = library.FindBorrower(borrowerID);
        if(borrower == null){
            System.out.println("Borrower not found.");
            return;
        }
        LoggedInBorrower = borrower;
        System.out.println("Login successful.");
        borrowerFunctions.BorrowerActionsMenu();
    }
}
