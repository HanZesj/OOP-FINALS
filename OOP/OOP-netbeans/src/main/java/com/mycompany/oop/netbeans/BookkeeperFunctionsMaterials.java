package com.mycompany.oop.netbeans;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class BookkeeperFunctionsMaterials {
    private final Library library;
    private final Scanner scanner;

    public BookkeeperFunctionsMaterials(Library library) {
        this.library = library;
        this.scanner = new Scanner(System.in);
    }

    public void AddMaterial() {
        try {
            System.out.println("\nAdd Material");
            String title = getStringInput("Enter title: ");
            String genre = getStringInput("Enter genre: ");
            String author = getStringInput("Enter author: ");
            String publisher = getStringInput("Enter publisher: ");
            int yearPublished = getIntInput("Enter year published: ");
            int copies = getIntInput("Enter number of copies: ");
            Material material = new Book(title, genre, author, publisher, yearPublished, copies);
            library.AddMaterial(material);
            System.out.println("Material added successfully with ID: " + material.GetMaterialID());
        } catch (Exception e) {
            System.out.println("Error adding material: " + e.getMessage());
        }
    }

    public void DeleteMaterial() {
        try {
            List<Material> materials = library.GetMaterials();
            if (materials.isEmpty()) {
                System.out.println("No materials in the system yet.");
                return;
            }
            ViewMaterials();
            System.out.println("\nDelete Material");
            int materialID = getIntInput("Enter material ID: ");
            Material material = library.FindMaterial(materialID);
            if (material == null) {
                System.out.println("Material not found.");
                return;
            }
            library.DeleteMaterial(materialID);
            System.out.println("Material deleted successfully.");
        } catch (Exception e) {
            System.out.println("Error deleting material: " + e.getMessage());
        }
    }

public void ViewMaterials() {
    try {
        System.out.println("\nView Materials");
        List<Material> materials = library.GetMaterials();
        if (materials.isEmpty()) {
            System.out.println("No materials in the system yet.");
            return;
        }
        for (Material material : materials) {
            System.out.println("Material ID: " + material.GetMaterialID());
            System.out.println("Title: " + material.GetTitle());
            System.out.println("Genre: " + material.GetGenre());
            System.out.println("Author: " + material.GetAuthor());
            System.out.println("Publisher: " + material.GetPublisher());
            System.out.println("Year Published: " + material.GetYearPublished());
            System.out.println("Copies: " + material.GetCopies());
            System.out.println();
        }
    } catch (Exception e) {
        System.out.println("Error viewing materials: " + e.getMessage());
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