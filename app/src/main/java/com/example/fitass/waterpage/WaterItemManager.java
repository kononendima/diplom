package com.example.fitass.waterpage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.fitass.DataBaseHelper;
import com.example.fitass.UserManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
    public void addWater(WaterItem waterItem){

            ContentValues values = new ContentValues();
            values.put(waterItem.USER_ID, userManager.getCurrentUserIdFromMemory());
            values.put(waterItem.TYPE, waterItem.getType());
            values.put(waterItem.VOLUME, waterItem.getVolume());
            values.put(waterItem.DATE, waterItem.getDate());
            values.put(waterItem.UUID,waterItem.uuid);
            mDatabase.insert(waterItem.TABLE_NAME,null,values);

    }
    public void deleteWater(String uuid){


        mDatabase.delete(WaterItem.TABLE_NAME,"uuid ='"+uuid+"'",null);

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
                waterItem.setUuid(cursor.getString(4));
                waterList.add(waterItem);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return waterList;
    };

    public int getTodayWater(String userId) {
        int totalVolume = 0;

        String today = new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault()).format(new Date());

        Cursor cursor = mDatabase.rawQuery(
                "SELECT SUM(volume) FROM water_table WHERE user_id = " + userId + " AND date =\"" + today + "\"", null
        );

        if (cursor.moveToFirst()) {
            totalVolume = cursor.isNull(0) ? 0 : cursor.getInt(0);
        }

        cursor.close();
        return totalVolume;
    }
}
