package com.example.loginsignuppage;
public class Service2 {
    private String Nom;
    private String Category;
    private String Description;
    private String Prix;
    private String Email;

    public Service2() {
        // Constructeur par défaut requis pour Firebase
    }

    public Service2(String Nom, String Category, String Description, String Prix,String Email) {
        this.Nom = Nom;
        this.Category = Category;
        this.Description = Description;
        this.Prix = Prix;
        this.Email=Email;
    }

    // Getters et setters pour les attributs

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String Category) {
        this.Category = Category;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getPrix() {
        return Prix;
    }

    public void setPrix(String Prix) {
        this.Prix = Prix;
    }
    public String getemail() {
        return Email;
    }

    public void setemail(String Email) {
        this.Email = Email;
    }

}
