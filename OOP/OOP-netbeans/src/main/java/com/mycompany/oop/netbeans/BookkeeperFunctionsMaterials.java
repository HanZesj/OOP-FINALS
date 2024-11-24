package com.mycompany.oop.netbeans;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BookkeeperFunctionsMaterials {
    private final Library library;
    private final Scanner scanner;

    public BookkeeperFunctionsMaterials(Library library) {
        this.library = library;
        this.scanner = new Scanner(System.in);
    }

    public void AddMaterial() {
        System.out.println("\nadd material");
        String title = getStringInput("Enter title: ");
        String author = getStringInput("Enter author: ");
        String publisher = getStringInput("Enter publisher: ");
        int yearPublished = getIntInput("Enter year published: ");
        int copies = getIntInput("Enter number of copies: ");
        Material material = new Book(title, author, publisher, yearPublished, copies);
        library.AddMaterial(material);
        System.out.println("Material added successfully with ID: " + material.GetMaterialID());
    }

    public void DeleteMaterial() {
        ViewMaterials();
        System.out.println("\ndelete material");
        int materialID = getIntInput("Enter material ID: ");
        library.DeleteMaterial(materialID);
        System.out.println("Material deleted successfully.");
    }

    public void ViewMaterials() {
        System.out.println("\nview materials");
        for (Material material : library.GetMaterials()) {
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