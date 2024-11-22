package com.mycompany.oop.netbeans;

import java.io.Serializable;

public abstract class Material implements Serializable {
    private String title;
    private String author;
    private String publisher;
    private int yearPublished;
    private int materialID;
    private static int nextID = 1;
    protected int copies;
    

    public Material(String title, String author, String publisher, int yearPublished) {
        this.materialID = materialID++;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.yearPublished = yearPublished;
    }

    public int GetMaterialID() {
        return materialID;
    }

    public String GetTitle(){
        return title;
    }

    public int GetYeahPublished(){
        return yearPublished;
    }

    public String GetPublisher(){
        return publisher;
    }

    public String Getauthor(){
        return author;
    }

    public int GetCopies(){
        return copies;
    }

    public void SetCopies(int copies){
        this.copies = copies;
    }

    public abstract String GetType();
}