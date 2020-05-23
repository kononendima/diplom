package com.example.fitass;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.fitass.Fragments.EatFragment;
import com.example.fitass.Fragments.WaterFragment;
import com.example.fitass.eatlist.EatItem;
import com.example.fitass.eatlist.Product;
import com.example.fitass.waterpage.WaterItem;

public class DataBaseHelper extends SQLiteOpenHelper {
    static final int VERSION=1;
    private static final String DATABASE_NAME="DataBase.db";
    public DataBaseHelper(Context context){
        super(context,DATABASE_NAME,null,VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ Product.TABLE_NAME + " ("+
                "_id integer primary key autoincrement, " +
                Product.TITLE   + ", "+
                Product.CALROIE_PRODUCT+")");
        db.execSQL("create table "+ EatItem.TABLE_NAME + " ("+
                "_id integer primary key autoincrement, " +
                EatItem.EAT+ ", "+
                EatItem.USER_ID+ ", "+
                EatItem.DATE+","+
                EatItem.WEIGHT+","+
                EatItem.UUID+","+
                EatItem.CALORIE+")");
        db.execSQL("create table "+ WaterItem.TABLE_NAME + " ("+
                "_id integer primary key autoincrement, " +
                WaterItem.TYPE+ ", "+
                WaterItem.VOLUME+ ", "+
                WaterItem.DATE+","+
                WaterItem.USER_ID+")");
        db.execSQL("create table activity_table ("+
                "_id integer primary key autoincrement, " +
                "user_id"+ ", "+
                "steps"+","+
                "date"+")");

        db.execSQL("create table "+User.TABLE_NAME+" ("+
                "_id integer primary key autoincrement, " +
                User.LOGIN+ ", "+
                User.PASSWORD+","+
                User.HEIGHT+","+
                User.WEIGHT+","+
                User.LIFESTYLE+")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + EatItem.TABLE_NAME);
        onCreate(db);
    }
}
