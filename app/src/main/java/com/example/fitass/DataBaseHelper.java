package com.example.fitass;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
                EatItem.UUID+","+
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

        db.execSQL("CREATE TABLE recipes (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "title TEXT, " +
                "description TEXT, " +
                "calories INTEGER, " +
                "imageUrl TEXT);");

        // Пример вставки рецепта
        db.execSQL("INSERT INTO recipes (title, description, calories, imageUrl) VALUES " +
                "('Овсянка с ягодами', 'Вкусная и полезная овсяная каша с свежими ягодами и мёдом.', 350, 'https://images.unsplash.com/photo-1504754524776-8f4f37790ca0?auto=format&fit=crop&w=800&q=80')");

        db.execSQL("INSERT INTO recipes (title, description, calories, imageUrl) VALUES " +
                "('Куриное филе с овощами', 'Запечённое куриное филе с ассорти из овощей и пряностями.', 450, 'https://images.unsplash.com/photo-1551183053-bf91a1d81141?auto=format&fit=crop&w=800&q=80')");

        db.execSQL("INSERT INTO recipes (title, description, calories, imageUrl) VALUES " +
                "('Смузи из шпината и банана', 'Зеленый смузи с полезным шпинатом и сладким бананом.', 200, 'https://images.unsplash.com/photo-1512621776951-a57141f2eefd?auto=format&fit=crop&w=800&q=80')");

        db.execSQL("INSERT INTO recipes (title, description, calories, imageUrl) VALUES " +
                "('Салат Цезарь', 'Классический салат Цезарь с курицей, сухариками и пармезаном.', 300, 'https://images.unsplash.com/photo-1551218808-94e220e084d2?auto=format&fit=crop&w=800&q=80')");

        db.execSQL("INSERT INTO recipes (title, description, calories, imageUrl) VALUES " +
                "('Паста с томатным соусом', 'Итальянская паста с насыщенным томатным соусом и базиликом.', 500, 'https://images.unsplash.com/photo-1525755662778-989d0524087e?auto=format&fit=crop&w=800&q=80')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + EatItem.TABLE_NAME);
        onCreate(db);
    }
}
