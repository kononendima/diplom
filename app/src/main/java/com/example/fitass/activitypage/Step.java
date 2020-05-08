package com.example.fitass.activitypage;

public class Step {
    public static final String ID="_id";
    public static final String TABLE_NAME="activity_table";
    public static final String USER_ID="user_id";
    public static final String STEPS="steps";
    public static final String DATE="date";
    String id;
    String userId;
    String steps;
    String date;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
