package com.example.fitass;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    Button btnAdd;
    BottomNavigationView bottomNavigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<String> product=new ArrayList<String>(Arrays.asList("Амарант","Амарантовые отруби Di&Di с гречей","Амарантовые отруби Di&Di с ламинарией","Амарантовые отруби Di&Di с топинамбуром","Булгур","Булгур Yelli с белыми грибами","Булгур Мистраль","Горох Makfa дробленый в пакетиках","Горох Makfa колотый","Горох Агро-Альянс колотый","Горох маш Ярмарка Платинум","Горох Мистраль Айдахо","Горох Мистраль Орегон","Горох Пассим колотый","Горох Увелка колотый","	Горох Makfa колотый"));
        ArrayList<String> cal=new ArrayList<String>(Arrays.asList("371","345","311","345","342","330","330","343","82","299","299","350","312","317","299","360"));

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        BottomBar bottomBar=new BottomBar(navigation,this);
        bottomBar.Buttom();
        //this.deleteDatabase("DataBase.db"); //Удаление бд
        SQLiteDatabase mDatabase=new
                DataBaseHelper(this).getWritableDatabase();
        for(int i = 0; i<product.size(); i++){
            ContentValues values = new ContentValues();
            values.put(Product.TITLE, product.get(i).toString());
            values.put(Product.CALROIE_PRODUCT, cal.get(i).toString());
           mDatabase.insert(Product.TABLE_NAME, null, values);
        }


    }




}