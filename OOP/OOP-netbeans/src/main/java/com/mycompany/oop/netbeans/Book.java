package com.mycompany.oop.netbeans;

public class Book extends Material {
    public Book(String title, String author, String publisher, int yearPublished, int copies) {
        super(title, author, publisher, yearPublished);
        this.SetCopies(copies);
    }

    @Override
    public String GetType() {
        return "Book";
    }
}