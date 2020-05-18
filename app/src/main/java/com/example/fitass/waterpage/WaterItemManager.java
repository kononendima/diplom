package com.example.fitass.waterpage;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.fitass.DataBaseHelper;
import com.example.fitass.UserManager;

public class WaterItemManager {
    private UserManager userManager;
    private SQLiteDatabase mDatabase;
    private WaterItemManager waterItemManager;
    public WaterItemManager(Context context) {
        userManager=new UserManager(context);
        mDatabase=new
                DataBaseHelper(context).getWritableDatabase();
        waterItemManager=new WaterItemManager(context);
    }
    public void saveStepsToDb(WaterItem waterItem){

            ContentValues values = new ContentValues();
        values.put(waterItem.USER_ID, userManager.getCurrentUserIdFromMemory());
            values.put(waterItem.TYPE, waterItem.getType());
            values.put(waterItem.DATE, waterItem.getDate());
            mDatabase.insert(waterItem.TABLE_NAME,null,values);

    }
}
