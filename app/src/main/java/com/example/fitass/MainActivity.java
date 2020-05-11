package com.example.fitass;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.FragmentManager;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.fitass.Fragments.ActivityFragment;
import com.example.fitass.Fragments.EatFragment;
import com.example.fitass.activitypage.ActivityListManager;
import com.example.fitass.eatlist.Product;
import com.google.android.material.bottomnavigation.BottomNavigationView;


import java.util.ArrayList;
import java.util.Arrays;


import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {
    @BindView(R.id.activity_main_bottomNavigationBar)
    BottomNavigationView bottomBarView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        bottomBar(bottomBarView);

        ArrayList<String> product=new ArrayList<String>(Arrays.asList("Амарант","Амарантовые отруби Di&Di с гречей","Амарантовые отруби Di&Di с ламинарией","Амарантовые отруби Di&Di с топинамбуром","Булгур","Булгур Yelli с белыми грибами","Булгур Мистраль","Горох Makfa дробленый в пакетиках","Горох Makfa колотый","Горох Агро-Альянс колотый","Горох маш Ярмарка Платинум","Горох Мистраль Айдахо","Горох Мистраль Орегон","Горох Пассим колотый","Горох Увелка колотый","	Горох Makfa колотый"));
        ArrayList<String> cal=new ArrayList<String>(Arrays.asList("371","345","311","345","342","330","330","343","82","299","299","350","312","317","299","360"));
        SQLiteDatabase mDatabase=new
                DataBaseHelper(this).getWritableDatabase();
        for(int i = 0; i<product.size(); i++){
            ContentValues values = new ContentValues();
            values.put(Product.TITLE, product.get(i).toString());
            values.put(Product.CALROIE_PRODUCT, cal.get(i).toString());
            mDatabase.insert(Product.TABLE_NAME, null, values);
        }

    }



    public boolean bottomBar(BottomNavigationView navigation) {
       final EatFragment eatFragment = new EatFragment();
       final ActivityFragment activityFragment=new ActivityFragment();
        final FragmentManager fragmentManager = getSupportFragmentManager();
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {


                    case R.id.ic_main:
                        break;
                    case R.id.ic_activity:
                        fragmentManager
                                .beginTransaction()
                                .addToBackStack(null)
                                .replace(R.id.activity_main_fragmentMain, activityFragment)
                                .commit();
                        break;
                    case R.id.ic_eat:
                            fragmentManager
                                .beginTransaction().addToBackStack(null)
                                .replace(R.id.activity_main_fragmentMain, eatFragment)
                                .commit();
                        break;
                }

                return false;
            }
        });
        return false;
    }

}