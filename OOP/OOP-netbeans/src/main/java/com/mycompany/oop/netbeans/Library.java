package com.mycompany.oop.netbeans;

import java.util.ArrayList;
import java.util.List;
public class Library {
    private final List<Material> materials;
    private final List<Borrower> borrowers;

    public Library() {
        this.materials = new ArrayList<>();
        this.borrowers = new ArrayList<>();
    }

    public void AddMaterial(Material material){
        materials.add(material);
    }

    public void DeleteMaterial(int materialID){
        materials.removeIf(material -> material.GetMaterialID() == materialID);
    }

    public List<Material> GetMaterials(){
        return materials;
    }

    public Material FindMaterial(int materialID){
        for(Material material : materials){
            if(material.GetMaterialID() == materialID){
                return material;
            }
        }
        return null;
    }

    // should be for the GetBorrowedMaterials method
    public List<Material> GetBorrowedMaterials(int borrowerID){
        List<Material> borrowedMaterials = new ArrayList<>();
        for(Borrower borrower : borrowers){
            if(borrower.GetBorrowerID() == borrowerID){
                for(String materialID : borrower.GetBorrowedMaterials()){
                    Material material = FindMaterial(Integer.parseInt(materialID));
                    if(material != null){
                        borrowedMaterials.add(material);
                    }
                }
            }
        }
        return borrowedMaterials;
    }

    public Material GetMaterial(int materialID){
        for(Material material : materials){
            if(material.GetMaterialID() == materialID){
                return material;
            }
        }
        return null;
    }

    public void AddBorrower(Borrower borrower){
        borrowers.add(borrower);
    }

    public void RemoveBorrower(int borrowerID){
        borrowers.removeIf(borrower -> borrower.GetBorrowerID() == borrowerID);
    }

    public List<Borrower> GetBorrowers(){
        return borrowers;
    }

    public Borrower FindBorrower(int borrowerID){
        for(Borrower borrower : borrowers){
            if(borrower.GetBorrowerID() == borrowerID){
                return borrower;
            }
        }
        return null;
    }
}