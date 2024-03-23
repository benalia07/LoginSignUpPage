package com.example.loginsignuppage;

import android.widget.ImageView;

public class Category {
    String name;
    String imageUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return imageUrl;
    }

    public void setImage(String image) {
        this.imageUrl = image;
    }

    public Category(String name , String image) {
        this.name = name;
        this.imageUrl = image;
    }
}
