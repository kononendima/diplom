package com.example.fitass;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.fitass.eatlist.EatItem;

public class DataBaseHelper extends SQLiteOpenHelper {
    static final int VERSION=1;
    private static final String DATABASE_NAME="DataBase.db";
    public DataBaseHelper(Context context){
        super(context,DATABASE_NAME,null,VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ EatItem.TABLE_NAME + " ("+
                "_id integer primary key autoincrement, " +
                EatItem.EAT+ ", "+
                EatItem.DATE+","+
                EatItem.CALORIE+")");

        db.execSQL("create table "+User.TABLE_NAME+" ("+
                "_id integer primary key autoincrement, " +
                User.LOGIN+ ", "+
                User.PASSWORD+","+
                User.STEPS_ID+","+
                User.CALORIE_ID+","+
                User.HEIGHT+","+
                User.WEIGHT+","+
                User.LIFESTYLE+")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

       // db.execSQL("DROP TABLE IF EXISTS " + EatItem.TABLE_NAME);
        onCreate(db);
    }
}
