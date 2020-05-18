package com.example.fitass.waterpage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.fitass.DataBaseHelper;
import com.example.fitass.UserManager;
import com.example.fitass.eatlist.Product;

import java.util.ArrayList;
import java.util.List;

public class WaterItemManager {
    private UserManager userManager;
    private SQLiteDatabase mDatabase;
    private WaterItemManager waterItemManager;
    private WaterItem waterItem;
    public WaterItemManager(Context context) {
        userManager=new UserManager(context);
        mDatabase=new
                DataBaseHelper(context).getWritableDatabase();

    }
    public void saveStepsToDb(WaterItem waterItem){

            ContentValues values = new ContentValues();
            values.put(waterItem.USER_ID, userManager.getCurrentUserIdFromMemory());
            values.put(waterItem.TYPE, waterItem.getType());
            values.put(waterItem.VOLUME, waterItem.getVolume());
            values.put(waterItem.DATE, waterItem.getDate());
            mDatabase.insert(waterItem.TABLE_NAME,null,values);

    }
    public List<WaterItem> getWaterList(){
        int userId=userManager.getCurrentUserIdFromMemory();
        ArrayList<WaterItem> waterList=new ArrayList<>();
        Cursor cursor =mDatabase.rawQuery("SELECT * FROM "+waterItem.TABLE_NAME+" where \""+waterItem.USER_ID+"\" = "+ userId+"",null);
        if (cursor.moveToFirst()) {
            do {
                WaterItem waterItem=new WaterItem();
                waterItem.setType(cursor.getString(1));
                waterItem.setVolume(cursor.getString(2));
                waterItem.setDate(cursor.getString(3));
                waterList.add(waterItem);
            } while (cursor.moveToNext());

        }
        cursor.close();

        return waterList;
    };
}
