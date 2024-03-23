package com.example.loginsignuppage;

import java.io.Serializable;
import java.util.ArrayList;

public class Order  implements Serializable {
    User user;
    String name , description , category,client;
    int mainImage,rating,numOrders,price;
    ArrayList<Integer> images;

    public String getName() {
        return name;
    }

    public void setName(String Client) {
        this.name = name;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
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

    public Order(String name, String description, String client, int mainImage, int rating, int numOrders, int price ) {
        this.name = name;
        this.description = description;
        this.client = String.valueOf(client);
        this.mainImage = mainImage;
        this.rating = rating;
        this.numOrders = numOrders;
        this.price = price;

    }
}
