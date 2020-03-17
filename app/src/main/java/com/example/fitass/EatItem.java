package com.example.fitass;

import java.util.ArrayList;
import java.util.Date;

public class EatItem {
    private String date;
    private String calory;
    private String eat;
    public EatItem(String eat, String date, String calory) {
        this.date = date;
        this.calory = calory;
        this.eat = eat;
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

    public String getType() {
        return eat;
    }

    public void setType(String type) {
        this.eat = type;
    }
}
