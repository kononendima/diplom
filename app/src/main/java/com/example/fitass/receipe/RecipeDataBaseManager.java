package com.example.fitass.receipe;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.fitass.DataBaseHelper;
import com.example.fitass.UserManager;

import java.util.ArrayList;
import java.util.List;

public class RecipeDataBaseManager {

    private SQLiteDatabase mDatabase;
    private UserManager userManager;

    public RecipeDataBaseManager(Context context) {
        mDatabase = new
                DataBaseHelper(context).getWritableDatabase();
        userManager = new UserManager(context);
    }

    @SuppressLint("Range")
    public List<Recipe> getAllRecipes() {
        List<Recipe> recipes = new ArrayList<>();
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM recipes", null);
        if (cursor.moveToFirst()) {
            do {
                Recipe recipe = new Recipe();
                recipe.setId(cursor.getInt(cursor.getColumnIndex("id")));
                recipe.setTitle(cursor.getString(cursor.getColumnIndex("title")));
                recipe.setDescription(cursor.getString(cursor.getColumnIndex("description")));
                recipe.setCalories(cursor.getInt(cursor.getColumnIndex("calories")));
                recipe.setImageUrl(cursor.getString(cursor.getColumnIndex("imageUrl")));
                recipes.add(recipe);
            } while (cursor.moveToNext());
        }
        cursor.close();
        mDatabase.close();
        return recipes;
    }
}
