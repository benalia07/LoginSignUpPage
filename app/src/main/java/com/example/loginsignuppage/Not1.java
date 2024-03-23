package com.example.loginsignuppage;

public class Not1 {
    private String client;
    private String email;

    private double prix;
    private String service;

    public Not1() {
        // Constructeur par défaut requis pour Firebase
    }

    // Getters et setters pour les attributs

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }
    public String getemail() {
        return email;
    }

    public void setemail(String email) {
        this.email = email;
    }
}
