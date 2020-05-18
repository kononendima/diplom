package com.example.fitass.waterpage;

public class WaterItem {
    public static final String ID="_id";
    public static final String TABLE_NAME="water_table";
    public static final String USER_ID="user_id";
    public static final String VOLUME="volume";
    public static final String DATE="date";
    public static final String TYPE="type";
    String userId;
    String volume;
    String date;
    String type;

    public WaterItem() {
    }

    public WaterItem(String userId, String volume, String date, String type) {
        this.userId = userId;
        this.volume = volume;
        this.date = date;
        this.type = type;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
