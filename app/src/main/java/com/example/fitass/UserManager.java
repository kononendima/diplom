package com.example.fitass;

import static android.content.Context.MODE_PRIVATE;

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
        return values;
    }

    public void addUser(User user) {
        ContentValues values = getContentValues(user);
        mDatabase.insert(user.TABLE_NAME, null, values);
    }

    public User checkRegistration(String login, String password) {
        user = new User();
        Cursor cursor = mDatabase.rawQuery("SELECT * from " + User.TABLE_NAME + " WHERE (login = '" + login + "' and password = '" + password + "');", null);
        if (cursor.getCount() == 0) {
            return null;
        } else {
            if (cursor.moveToFirst()) {
                do {
                    user.setId(cursor.getString(0));
                    user.setLogin(cursor.getString(1));
                    user.setPassword(cursor.getString(2));
                    user.setHeight(cursor.getString(3));
                    user.setWeight(cursor.getString(4));
                } while (cursor.moveToNext());
            }
            cursor.close();
            return user;
        }
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
}
