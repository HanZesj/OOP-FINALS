package com.mycompany.oop.netbeans;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static Library library = new Library();
    public static Bookkeeper bookkeeper = new Bookkeeper(library);
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
        while(true){
            System.out.println("\nManage Borrowers");
            System.out.println("1. Add Borrower");
            System.out.println("2. Edit Borrower");
            System.out.println("3. Delete Borrower");
            System.out.println("4. View Borrowers");
            System.out.println("5. Set Borrower Violations");
            System.out.println("6. Back to Main Menu");
            int choice = getIntInput(":: ");
            switch(choice){
                case 1 -> AddBorrower();
                case 2 -> EditBorrower();
                case 3 -> DeleteBorrower();
                case 4 -> ViewBorrowers();
                case 5 -> SetBorrowerViolations();
                case 6 -> GetUserType();
            }
        }
    }

    private static void AddBorrower(){}
    private static void EditBorrower(){
        ViewBorrowers();
        System.out.println("\nEdit Borrower");
        int borrowerID = getIntInput("Enter borrower ID: ");
        Borrower borrower = library.FindBorrower(borrowerID);
        if(borrower == null){
            System.out.println("Borrower not found.");
            return;
        }
        System.out.println("Enter new first name: ");
        String firstName = scanner.next();
        System.out.println("Enter new last name: ");
        String lastName = scanner.next();
        System.out.println("Enter new gender: ");
        String gender = scanner.next();
        System.out.println("Enter new birthday: ");
        String birthday = scanner.next();
        System.out.println("Enter new contact number: ");
        String contactNum = scanner.next();
        System.out.println("Enter new email: ");
        String email = scanner.next();

        borrower.SetFirstName(firstName);
        borrower.SetLastName(lastName);
        borrower.SetGender(gender);
        borrower.SetBirthday(birthday);
        borrower.SetContactNum(contactNum);
        borrower.SetEmail(email);
    }
    private static void DeleteBorrower(){
        ViewBorrowers();
        System.out.println("\nDelete Borrower");
        int borrowerID = getIntInput("Enter borrower ID: ");
        Borrower borrower = library.FindBorrower(borrowerID);
        if(borrower == null){
            System.out.println("Borrower not found.");
            return;
        }
        library.RemoveBorrower(borrower.GetBorrowerID());
        System.out.println("Borrower deleted successfully.");
    }

    private static void ViewBorrowers(){
        System.out.println("Borrowers:");
        for(Borrower borrower : library.GetBorrowers()){
            System.out.println(borrower.GetBorrowerID() + " " + borrower.GetFirstName() + " " + borrower.GetLastName());
        }
    }
    private static void SetBorrowerViolations(){}

    private static void ManageMaterials(){
        
    }

    private static void BorrowerMenu(){}
}
