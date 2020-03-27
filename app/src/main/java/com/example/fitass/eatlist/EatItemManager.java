package com.example.fitass.eatlist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.fitass.DataBaseHelper;

import java.util.ArrayList;
import java.util.List;

public class EatItemManager {
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static ContentValues getContentValues(EatItem eatItem){
        ContentValues values=new ContentValues();
        values.put(eatItem.EAT, eatItem.getEat());
        values.put(eatItem.DATE,eatItem.getDate());
        values.put(eatItem.CALORY,eatItem.getCalory());
        return values;
    }
    public void addEatItem(EatItem eatItem) {
        ContentValues values = getContentValues(eatItem);
        mDatabase.insert(eatItem.TABLE_NAME, null, values);
    }
    EatItemManager(Context context) {
        mContext=context;
        mDatabase=new
                DataBaseHelper(mContext).getWritableDatabase();
    }
    private EatCursorWrapper getEatItemsCursor(String whereClause,//Имеем объект(курсор) до первой строки
                                              String[] wereArgs){
        Cursor cursor=mDatabase.query(EatItem.TABLE_NAME,
                null,whereClause,wereArgs,null,null,null);
        return new EatCursorWrapper(cursor);//Получаем объект(курсор) до первой строки
    }
    public List<EatItem> getEatItemsList() {
        List<EatItem> eatList = new ArrayList<>();
        EatCursorWrapper cursor = getEatItemsCursor(null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                eatList.add(cursor.getEat());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return eatList;//Возвращает список дел
    }
}
