package com.example.fitass;

public class User {
    public static final String TABLE_NAME = "user_table";
    public static final String ID = "_id";
    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";
    public static final String STEPS_ID = "steps_id";
    public static final String CALORIE_ID = "calorie_id";
    public static final String HEIGHT = "height";
    public static final String WEIGHT = "weight";
    public static final String LIFESTYLE = "lifestyle";
    public static final String GOAL = "goal";
    public static final String GENDER = "gender";
    public static final String AGE = "age";

    String gender;
    String age;

    String id;
    String login;
    String password;
    String stepsId;
    String calorieId;
    String height;
    String weight;
    String lifestyle;
    String goal;

    public User() {
    }

    public String getStepsId() {
        return stepsId;
    }

    public void setStepsId(String stepsId) {
        this.stepsId = stepsId;
    }

    public String getcalorieId() {
        return calorieId;
    }

    public String getGoal() {
        return goal;
    }

    public void setcalorieId(String calorieId) {
        this.calorieId = calorieId;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public User(String id, String login, String password, String stepsId, String calorieId, String height, String weight, String lifestyle, String goal, String gender, String age) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.stepsId = stepsId;
        this.calorieId = calorieId;
        this.height = height;
        this.weight = weight;
        this.lifestyle = lifestyle;
        this.goal = goal;
        this.gender = gender;
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User(String login, String password, String stepsId, String calorieId, String height, String weight, String lifestyle, String goal) {
        this.login = login;
        this.password = password;
        this.stepsId = stepsId;
        this.calorieId = calorieId;
        this.height = height;
        this.weight = weight;
        this.lifestyle = lifestyle;
        this.goal = goal;
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
