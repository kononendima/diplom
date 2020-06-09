package com.example.fitass.eatlist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.fitass.DataBaseHelper;
import com.example.fitass.User;
import com.example.fitass.UserManager;

import java.util.ArrayList;
import java.util.List;

public class EatItemManager {
    private SQLiteDatabase mDatabase;
    private UserManager userManager;

    public ContentValues getContentValues(EatItem eatItem){
        int currentUser=userManager.getCurrentUserIdFromMemory();
        ContentValues values=new ContentValues();
        values.put(eatItem.EAT, eatItem.getEat());
        values.put(eatItem.DATE,eatItem.getDate());
        values.put(eatItem.CALORIE,eatItem.getCalorie());
        values.put(eatItem.USER_ID,currentUser);
        values.put(eatItem.WEIGHT,eatItem.getWeight());
        values.put(eatItem.UUID,eatItem.getUuid().toString());
        return values;
    }
    public void addEatItem(EatItem eatItem) {
        ContentValues values = getContentValues(eatItem);
        mDatabase.insert(eatItem.TABLE_NAME, null, values);
    }
    public void deleteEatItem(String uuid) {

        mDatabase.delete(EatItem.TABLE_NAME,"uuid ='"+uuid+"'",null);
    }
    public EatItemManager(Context context) {
        mDatabase=new
                DataBaseHelper(context).getWritableDatabase();
        userManager=new UserManager(context);
    }
    private EatCursorWrapper getEatItemsCursor(String whereClause,
                                              String[] wereArgs){
        Cursor cursor=mDatabase.query(EatItem.TABLE_NAME,
                null, EatItem.USER_ID+" = "+userManager.getCurrentUserIdFromMemory(),wereArgs,null,null,null);
        return new EatCursorWrapper(cursor);
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
        return eatList;
    }
    public List<Product> getProductList(){

        ArrayList<Product> productList=new ArrayList<>();
        Cursor cursor =mDatabase.rawQuery("SELECT * from "+Product.TABLE_NAME+"",null);
        if (cursor.moveToFirst()) {
            do {
                Product product=new Product();
                product.setId(cursor.getString(0));
                product.setTitle(cursor.getString( 1));
                product.setCalorieProduct(cursor.getString(2));
                productList.add(product);
            } while (cursor.moveToNext());

        }
        cursor.close();

        return productList;
    };
    public  String getCalorieProduct(String productTitle){
        Cursor cursor =mDatabase.rawQuery("SELECT *  FROM product_table WHERE (title =  '"+productTitle+"');",null);
        String calorie = null;
        if (cursor.moveToFirst()) {
            do {

                 calorie=cursor.getString(2);

            } while (cursor.moveToNext());
        }
        cursor.close();
        return calorie;


    }
}
