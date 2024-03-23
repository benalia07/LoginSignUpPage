package com.example.loginsignuppage;

public class notification1 {
    private String nom;
    private String email;
    private Not1 not1;

    public notification1() {
        // Constructeur par défaut requis pour Firebase
    }

    // Getters et setters pour les attributs

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Not1 getNot1() {
        return not1;
    }

    public void setNot1(Not1 not1) {
        this.not1 = not1;
    }
}