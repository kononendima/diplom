package com.example.fitass;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class UserManager  {
    private Context mContext;
    User user;
    private SQLiteDatabase mDatabase;
    SharedPreferences sPref;

    public UserManager(Context context) {
        this.mContext = context;
        mDatabase=new
                DataBaseHelper(mContext).getWritableDatabase();
    }

    public static ContentValues getContentValues(User user) {
        ContentValues values = new ContentValues();
        values.put(User.LOGIN, user.getLogin());
        values.put(User.PASSWORD, user.getPassword());
        values.put(User.HEIGHT, user.getHeight());
        values.put(User.WEIGHT, user.getWeight());
        values.put(User.LIFESTYLE, user.getLifestyle());
        values.put(User.GOAL, user.getGoal());
        values.put(User.AGE, user.getAge());
        values.put(User.GENDER, user.getGender());
        return values;
    }

    public void addUser(User user) {
        ContentValues values = getContentValues(user);
        mDatabase.insert(user.TABLE_NAME, null, values);
    }

    public User checkRegistration(String login, String password) {
        user = new User();

        Cursor cursor = mDatabase.rawQuery(
                "SELECT _id, login, password, height, weight, age, gender FROM " + User.TABLE_NAME +
                        " WHERE login = ? AND password = ?", new String[]{login, password});

        if (cursor.moveToFirst()) {
            user.setId(cursor.getString(cursor.getColumnIndexOrThrow("_id")));
            user.setLogin(cursor.getString(cursor.getColumnIndexOrThrow("login")));
            user.setPassword(cursor.getString(cursor.getColumnIndexOrThrow("password")));
            user.setHeight(cursor.getString(cursor.getColumnIndexOrThrow("height")));
            user.setWeight(cursor.getString(cursor.getColumnIndexOrThrow("weight")));
            user.setAge(cursor.getString(cursor.getColumnIndexOrThrow("age")));
            user.setGender(cursor.getString(cursor.getColumnIndexOrThrow("gender")));
        } else {
            cursor.close();
            return null;
        }

        cursor.close();
        return user;
    }


    public void saveToMemoryUserData(String login, String password, String height, String weight){
        SharedPreferences sPref;
        sPref = mContext.getSharedPreferences("Data",MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString("id",user.getId());
        ed.putString("login", login);
        ed.putString("password", password);
        ed.putString("height",height);
        ed.putString("weight",weight);
        ed.commit();

    }

    public int getCurrentUserIdFromMemory(){
        sPref = mContext.getSharedPreferences("Data",Context.MODE_PRIVATE);
        int id = Integer.parseInt(sPref.getString("id","0"));
        return id;
    }

    public String getCurrentUserLogin() {
        sPref = mContext.getSharedPreferences("Data", Context.MODE_PRIVATE);
        return sPref.getString("login", "0");
    }
    public int getCurrentUserWeight(){
        sPref = mContext.getSharedPreferences("Data",Context.MODE_PRIVATE);
        int weight = Integer.parseInt(sPref.getString("weight","0"));
        return weight;
    }

    public String getCurrentUserGender() {
        sPref = mContext.getSharedPreferences("Data", Context.MODE_PRIVATE);
        return sPref.getString("gender", "unknown");
    }

    public int getCurrentUserAge() {
        sPref = mContext.getSharedPreferences("Data", Context.MODE_PRIVATE);
        return Integer.parseInt(sPref.getString("age", "0"));
    }

    public int getCurrentUserHeightFromMemory(){
        sPref = mContext.getSharedPreferences("Data",Context.MODE_PRIVATE);
        int height = Integer.parseInt(sPref.getString("height","0"));
        return height;
    }

    public void updateUserWeight(int userId, float newWeight) {
        ContentValues values = new ContentValues();
        values.put(User.WEIGHT, String.valueOf(newWeight));
        mDatabase.update(User.TABLE_NAME, values, User.ID + " = ?", new String[]{String.valueOf(userId)});
    }

    public float getUserWeightById(int userId) {
        Cursor cursor = mDatabase.rawQuery("SELECT " + User.WEIGHT + " FROM " + User.TABLE_NAME +
                " WHERE " + User.ID + " = ?", new String[]{String.valueOf(userId)});

        float weight = 0;
        if (cursor.moveToFirst()) {
            weight = Float.parseFloat(cursor.getString(0));
        }
        cursor.close();
        return weight;
    }

    @SuppressLint("Range")
    public User getUserById(int userId) {
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM " + User.TABLE_NAME + " WHERE " + User.ID + " = ?", new String[]{String.valueOf(userId)});
        User user = null;

        if (cursor.moveToFirst()) {
            user = new User();
            user.setId(cursor.getString(cursor.getColumnIndex(User.ID)));
            user.setLogin(cursor.getString(cursor.getColumnIndex(User.LOGIN)));
            user.setPassword(cursor.getString(cursor.getColumnIndex(User.PASSWORD)));
            user.setHeight(cursor.getString(cursor.getColumnIndex(User.HEIGHT)));
            user.setWeight(cursor.getString(cursor.getColumnIndex(User.WEIGHT)));
            user.setLifestyle(cursor.getString(cursor.getColumnIndex(User.LIFESTYLE)));
            user.setGoal(cursor.getString(cursor.getColumnIndex(User.GOAL)));
            user.setGender(cursor.getString(cursor.getColumnIndex(User.GENDER)));
            user.setAge(cursor.getString(cursor.getColumnIndex(User.AGE)));
        }

        cursor.close();
        return user;
    }
}
