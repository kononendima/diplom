package com.example.fitass;

import java.util.ArrayList;
import java.util.Date;

public class EatItem {
    private String date;
    private ArrayList<String> eat= new ArrayList<>();
    private int calory;
    private String type;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<String> getEat() {
        return eat;
    }

    public void setEat(ArrayList<String> eat) {
        this.eat = eat;
    }

    public int getCalory() {
        return calory;
    }

    public void setCalory(int calory) {
        this.calory = calory;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
