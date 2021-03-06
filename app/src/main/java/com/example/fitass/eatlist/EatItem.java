package com.example.fitass.eatlist;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class EatItem {
    public static final String TABLE_NAME = "eat_table";
    public static final String DATE ="date";
    public static final String CALORIE = "calorie";
    public static final String EAT = "eat";
    public static final String USER_ID="user_id";
    public static final String ID = "_id";
    public static final String WEIGHT = "weight";
    public static final String UUID = "uuid";
    private String date;
    private String calorie;
    private String eat;
    private String userId;
    private String uuid;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }






    public void setCalorie(String calorie) {
        this.calorie = calorie;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    private String weight;



    public EatItem(String eat, String date, String calorie,String userId,String weight,String uuid) {
        this.date = date;
        this.calorie = calorie;
        this.eat = eat;
        this.userId=userId;
        this.weight=weight;
        this.uuid=uuid;
    }
    public EatItem(String id, String eat, String date, String calorie,String userId,String weight,String uuid) {
        this.date = date;
        this.calorie = calorie;
        this.eat = eat;
        this.userId=userId;
        this.weight=weight;
        this.uuid=uuid;
    }
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCalorie() {
        return calorie;
    }

    public void setcalorie(String calorie) {
        this.calorie = calorie;
    }

    public String getEat() {
        return eat;
    }

    public void setEat(String type) {
        this.eat = type;
    }
}
