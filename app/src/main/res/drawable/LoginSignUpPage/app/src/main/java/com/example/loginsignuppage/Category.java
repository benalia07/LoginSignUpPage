package com.example.loginsignuppage;

import android.widget.ImageView;

public class Category {
    String name;
    int image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public Category(String name , int image) {
        this.name = name;
        this.image = image;
    }
}
