package com.mycompany.oop.netbeans;

import java.util.Scanner;

public class Main {
    private static Library library = new Library();
    private static Bookkeeper bookkeeper = new Bookkeeper(library);
    public static Scanner scanner = new Scanner(System.in);

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

    private static void BookkeeperMenu(){
        while(true){
            System.out.println("---Book Keeper Interface---");
            System.out.println("1. Manage Borrowers.");
            System.out.println("2. Manage Materials.");
            System.out.println("3. Exit.");
            int choice = getIntInput(":: ");
            switch (choice) {
                case 1 -> ManageBorrowers();
                case 2 -> ManageMaterials();
                case 3 -> System.exit(0);
            }
        }
    }

    private static void ManageBorrowers(){

    }

    private static void ManageMaterials(){
        
    }


    private static void BorrowerMenu(){}
}
