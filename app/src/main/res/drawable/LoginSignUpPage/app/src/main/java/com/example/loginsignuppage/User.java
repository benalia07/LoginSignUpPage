package com.example.loginsignuppage;

import java.util.ArrayList;

public class User {
    String userName, password;
    int profileImage;
    ArrayList<Service> services;

    public User(String userName, String password, int profileImage, ArrayList<Service> services) {
        this.userName = userName;
        this.password = password;
        this.profileImage = profileImage;
        this.services = services;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(int profileImage) {
        this.profileImage = profileImage;
    }

    public ArrayList<Service> getServices() {
        return services;
    }

    public void setServices(ArrayList<Service> services) {
        this.services = services;
    }
}
