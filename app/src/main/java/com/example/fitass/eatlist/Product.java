package com.example.fitass.eatlist;

public class Product {
    public static final String TABLE_NAME="product_table";
    public static final String TITLE="title";
    public static final String CALROIE_PRODUCT="calorie_product";

    private String id;
    private String title;
    private String calorieProduct;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCalorieProduct() {
        return calorieProduct;
    }

    public void setCalorieProduct(String calorieProduct) {
        this.calorieProduct = calorieProduct;
    }
}
