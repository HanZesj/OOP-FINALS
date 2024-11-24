package com.mycompany.oop.netbeans;

public class Book extends Material {
    public Book(String title, String author, String genre, String publisher, int yearPublished, int copies) {
        super(title, genre, author, publisher, yearPublished);
        this.SetCopies(copies);
    }

    @Override
    public String GetType() {
        return "Book";
    }
}