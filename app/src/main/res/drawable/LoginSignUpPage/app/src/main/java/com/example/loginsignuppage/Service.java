package com.example.loginsignuppage;

import java.util.ArrayList;

public class Service {
    User user;
    String name , description , category;
    int mainImage,rating,numOrders,price;
    ArrayList<Integer> images;

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

    public int getMainImage() {
        return mainImage;
    }

    public void setMainImage(int mainImage) {
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

    public ArrayList<Integer> getImages() {
        return images;
    }

    public void setImages(ArrayList<Integer> images) {
        this.images = images;
    }

    public Service(String name, String description, String category, int mainImage, int rating, int numOrders, int price ) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.mainImage = mainImage;
        this.rating = rating;
        this.numOrders = numOrders;
        this.price = price;

    }
}
