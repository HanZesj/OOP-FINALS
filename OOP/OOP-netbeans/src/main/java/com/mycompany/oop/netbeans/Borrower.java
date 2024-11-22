package com.mycompany.oop.netbeans;

import java.util.List;

public class Borrower {
    private int borrowerID;
    private String firstName;
    private String lastName;
    private String middleName;
    private String gender;
    private String birthday;
    private String contactNum;
    private String email;
    private String address;
    private int numberOfViolations;
    private List<String> borrowedMaterials;

    public Borrower(int borrowerID, String firstName, String lastName, String middleName, String gender, String birthday, String contactNum, String email, String address, int numberOfViolations) {
        this.borrowerID = borrowerID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.gender = gender;
        this.birthday = birthday;
        this.contactNum = contactNum;
        this.email = email;
        this.address = address;
        this.numberOfViolations = numberOfViolations;
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

    public String GetContactNum() {
        return contactNum;
    }

    public void SetContactNum(String contactNum) {
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

        public List<String> getBorrowedMaterials() {
        return borrowedMaterials;
    }

    public void borrowMaterial(int materialID) {
        borrowedMaterials.add(String.valueOf(materialID));
    }

    public void ReturnMaterial(int materialID){
        borrowedMaterials.remove(materialID);
    }
}