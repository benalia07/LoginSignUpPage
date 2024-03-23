package com.example.loginsignuppage;

import java.util.ArrayList;

public class Service {
    User user;
    String name , mainImage,description , category;
    int rating,numOrders,price;
    ArrayList<String> images;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getNumOrders() {
        return numOrders;
    }

    public void setNumOrders(int numOrders) {
        this.numOrders = numOrders;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public ArrayList<String> getImages() {
        return images;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }

    public Service(String name, String description, String category, String mainImage, int rating, int numOrders, int price ) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.mainImage = mainImage;
        this.rating = rating;
        this.numOrders = numOrders;
        this.price = price;
    }
}
