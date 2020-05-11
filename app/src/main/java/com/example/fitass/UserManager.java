package com.example.fitass;

import android.content.ContentValues;
import android.content.Context;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.fitass.eatlist.EatItem;

import static android.content.Context.MODE_PRIVATE;

public class UserManager  {
    private Context mContext;
    User user;
    private SQLiteDatabase mDatabase;
    SharedPreferences sPref;
    final String SAVED_TEXT = "saved_text";
    public UserManager(Context context) {

        this.mContext = context;
        mDatabase=new
                DataBaseHelper(mContext).getWritableDatabase();

    }
    public UserManager(){

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
                } while (cursor.moveToNext());
            }
            cursor.close();
            return user;
        }
    }
    public void saveToMemoryUserData(String login, String password, String height){
        SharedPreferences sPref;
        sPref = mContext.getSharedPreferences("Data",MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString("id",user.getId());
        ed.putString("login", login);
        ed.putString("password", password);
        ed.putString("height",height);
        ed.commit();


    }
    public int getCurrentUserIdFromMemory(){
        sPref = mContext.getSharedPreferences("Data",Context.MODE_PRIVATE);
        int id = Integer.parseInt(sPref.getString("id","0"));
        return id;
    }
    public int getCurrentUserHeightFromMemory(){
        sPref = mContext.getSharedPreferences("Data",Context.MODE_PRIVATE);
        int height = Integer.parseInt(sPref.getString("height","0"));
        return height;
    }

}
