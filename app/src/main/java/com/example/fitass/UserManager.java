package com.example.fitass;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.database.sqlite.SQLiteDatabase;

import com.example.fitass.eatlist.EatItem;

public class UserManager  {
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public UserManager(Context mContext) {

        this.mContext = mContext;
        mDatabase=new
                DataBaseHelper(mContext).getWritableDatabase();
    }

    public static ContentValues getContentValues(User user) {
        ContentValues values = new ContentValues();
        values.put(User.LOGIN, user.getLogin());
        values.put(User.PASSWORD, user.getPassword());
        values.put(User.STEPS_ID, user.getStepsId());
        values.put(User.CALORIE_ID, user.getcalorieId());
        values.put(User.HEIGHT, user.getHeight());
        values.put(User.WEIGHT, user.getWeight());
        values.put(User.LIFESTYLE, user.getLifestyle());
        return values;
    }

    public void addUser(User user) {
        ContentValues values = getContentValues(user);
        mDatabase.insert(user.TABLE_NAME, null, values);
    }
    public User entrance(String login,String password){
        User user=new User();
        Cursor cursor =mDatabase.rawQuery("SELECT * from "+User.TABLE_NAME+" WHERE (login = '"+login+"' and password = '"+password+"');",null);
        if (cursor.moveToFirst()) {
            do {
                // Passing values
                user.setId(cursor.getString(0));
                user.setLogin(cursor.getString(1));
                user.setPassword(cursor.getString(2));
                // Do something Here with values
            } while (cursor.moveToNext());
        }
        cursor.close();
        return user;
    }
}
