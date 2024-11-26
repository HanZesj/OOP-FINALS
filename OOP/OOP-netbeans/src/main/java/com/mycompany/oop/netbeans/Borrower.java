package com.mycompany.oop.netbeans;

import java.util.ArrayList;
import java.util.List;

public class Borrower {
    private static int nextID = 1;
    private int borrowerID;
    private String firstName;
    private String lastName;
    private String middleName;
    private String gender;
    private String birthday; // Changed from int to String
    private int contactNum;
    private String email;
    private String address;
    private int numberOfViolations;
    private final List<String> borrowedMaterials;
    private final BorrowerFunctions borrowerFunctions;
    private static final List<Borrower> borrowers = new ArrayList<>();

    public static List<Borrower> getBorrowers() {
        return new ArrayList<>(borrowers);
    }

    public static void addBorrower(Borrower borrower) {
        borrowers.add(borrower);
    }

    public Borrower(int borrowerID, String firstName, String lastName, String middleName, String gender, String birthday, int contactNum, String email, String address, Library library) {
        this.borrowerID = nextID++;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.gender = gender;
        this.birthday = birthday;
        this.contactNum = contactNum;
        this.email = email;
        this.address = address;
        this.numberOfViolations = 0;
        this.borrowedMaterials = new ArrayList<>();
        this.borrowerFunctions = new BorrowerFunctions(library);
    }

    public int GetBorrowerID() {
        return borrowerID;
    }

    public void SetBorrowerID(int borrowerID) {
        this.borrowerID = borrowerID;
    }

    public String GetFirstName() {
        return firstName;
    }

    public void SetFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String GetLastName() {
        return lastName;
    }

    public void SetLastName(String lastName) {
        this.lastName = lastName;
    }

    public String GetMiddleName() {
        return middleName;
    }

    public void SetMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String GetGender() {
        return gender;
    }

    public void SetGender(String gender) {
        this.gender = gender;
    }

    public String GetBirthday() {
        return birthday;
    }

    public void SetBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int GetContactNum() {
        return contactNum;
    }

    public void SetContactNum(int contactNum) {
        this.contactNum = contactNum;
    }

    public String GetEmail() {
        return email;
    }

    public void SetEmail(String email) {
        this.email = email;
    }

    public String GetAddress() {
        return address;
    }

    public void SetAddress(String address) {
        this.address = address;
    }

    public int GetNumberOfViolations() {
        return numberOfViolations;
    }

    public void SetNumberOfViolations(int numberOfViolations) {
        this.numberOfViolations = numberOfViolations;
    }

    public List<String> GetBorrowedMaterials() {
        return borrowedMaterials;
    }

    public void BorrowMaterial(int materialID) {
        borrowedMaterials.add(String.valueOf(materialID));
    }

    public void ReturnMaterial(int materialID) {
        borrowedMaterials.remove(String.valueOf(materialID));
    }

    public void ViewAvailableBooks() {
        borrowerFunctions.ViewAvailableBooks();
    }

    public void BorrowBook(int materialID) {
        borrowerFunctions.BorrowBook();
    }

    public void ReturnBook(int materialID) {
        borrowerFunctions.ReturnBook();
    }

    public void ViewBorrowedBooks() {
        borrowerFunctions.ViewBorrowedBooks();
    }

    public void ViewViolations() {
        borrowerFunctions.ViewViolations();
    }

    public void ViewBorrowerInformation() {
        borrowerFunctions.ViewBorrowerInformation();
    }
}