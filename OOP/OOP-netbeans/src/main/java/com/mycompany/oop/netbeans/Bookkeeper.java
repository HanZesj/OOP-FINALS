package com.mycompany.oop.netbeans;

public class Bookkeeper {
    private final BookkeeperFunctionsMaterials bookkeeperFunctionsMaterials;
    private final BookkeeperFunctionsBorrower bookkeeperFunctionsBorrower;

    public Bookkeeper(Library library) {
        this.bookkeeperFunctionsMaterials = new BookkeeperFunctionsMaterials(library);
        this.bookkeeperFunctionsBorrower = new BookkeeperFunctionsBorrower(library);
    }

    public void AddMaterial() {
        bookkeeperFunctionsMaterials.AddMaterial();
    }

    public void DeleteMaterial() {
        bookkeeperFunctionsMaterials.DeleteMaterial();
    }

    public void ViewMaterials() {
        bookkeeperFunctionsMaterials.ViewMaterials();
    }

    public void EditBorrower() {
        bookkeeperFunctionsBorrower.EditBorrower();
    }

    public void DeleteBorrower() {
        bookkeeperFunctionsBorrower.DeleteBorrower();
    }

    public void ViewBorrowers() {
        bookkeeperFunctionsBorrower.ViewBorrowers();
    }

    public void SetBorrowerViolations() {
        bookkeeperFunctionsBorrower.SetBorrowerViolations();
    }

    public void ManageBorrowers() {
        bookkeeperFunctionsBorrower.ManageBorrowers();
    }
}
