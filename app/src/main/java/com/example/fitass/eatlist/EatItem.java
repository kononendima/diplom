package com.example.fitass.eatlist;

import java.util.ArrayList;
import java.util.Date;

public class EatItem {
    public static final String TABLE_NAME = "eat_table";
    public static final String DATE ="date";
    public static final String CALORY = "calory";
    public static final String EAT = "eat";
    public static final String ID = "_id";
    private String date;
    private String calory;
    private String eat;
    private String id;
    public EatItem(String eat, String date, String calory) {
        this.date = date;
        this.calory = calory;
        this.eat = eat;
    }
    public EatItem(String id, String eat, String date, String calory) {
        this.date = date;
        this.calory = calory;
        this.eat = eat;
        this.id=id;
    }



    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }



    public String getCalory() {
        return calory;
    }

    public void setCalory(String calory) {
        this.calory = calory;
    }

    public String getEat() {
        return eat;
    }

    public void setEat(String type) {
        this.eat = type;
    }
}
