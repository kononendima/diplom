package com.example.fitass.receipe;

public class Recipe {
    private int id;
    private String title;
    private String description;
    private int calories;
    private String imageUrl;

    // Сеттеры

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    // Геттеры (для полноты)

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
