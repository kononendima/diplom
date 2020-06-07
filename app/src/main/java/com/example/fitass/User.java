package com.example.fitass;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
@IgnoreExtraProperties
public class User implements Serializable {
    public static final String TABLE_NAME = "user_table";
    public static final String ID = "_id";
    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";

    public static final String HEIGHT = "height";
    public static final String WEIGHT = "weight";
    public static final String LIFESTYLE = "lifestyle";

    String id;
    String login;
    String password;
    String height;
    String weight;
    String lifestyle;
    public User() {
    }

    public User(String id, String login, String password, String height, String weight, String lifestyle) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.height = height;
        this.weight = weight;
        this.lifestyle = lifestyle;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User(String login, String password,String height, String weight, String lifestyle) {

        this.login = login;
        this.password = password;
        this.height = height;
        this.weight = weight;
        this.lifestyle = lifestyle;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getLifestyle() {
        return lifestyle;
    }

    public void setLifestyle(String lifestyle) {
        this.lifestyle = lifestyle;
    }
}
