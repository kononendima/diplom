package com.example.fitass;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.fitAss.databinding.ActivityMainBinding;
import com.example.fitAss.databinding.ActivitySignUpBinding;
import com.example.fitass.Fragments.ActivityFragment;
import com.example.fitass.Fragments.EatFragment;
import com.example.fitass.Fragments.WaterFragment;
import com.example.fitass.eatlist.Product;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.ArrayList;
import java.util.Arrays;
import com.example.fitAss.R;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());


        setContentView(binding.getRoot());
        bottomBar(binding.activityMainBottomNavigationBar);
        ArrayList<String> product = new ArrayList<String>(Arrays.asList("Амарант", "Амарантовые отруби Di&Di с гречей", "Амарантовые отруби Di&Di с ламинарией", "Амарантовые отруби Di&Di с топинамбуром", "Булгур", "Булгур Yelli с белыми грибами", "Булгур Мистраль", "Горох Makfa дробленый в пакетиках", "Горох Makfa колотый", "Горох Агро-Альянс колотый", "Горох маш Ярмарка Платинум", "Горох Мистраль Айдахо", "Горох Мистраль Орегон", "Горох Пассим колотый", "Горох Увелка колотый", "	Горох Makfa колотый"));
        ArrayList<String> cal = new ArrayList<String>(Arrays.asList("371", "345", "311", "345", "342", "330", "330", "343", "82", "299", "299", "350", "312", "317", "299", "360"));
        SQLiteDatabase mDatabase = new
                DataBaseHelper(this).getWritableDatabase();
        for (int i = 0; i < product.size(); i++) {
            ContentValues values = new ContentValues();
            values.put(Product.TITLE, product.get(i).toString());
            values.put(Product.CALROIE_PRODUCT, cal.get(i).toString());
            mDatabase.insert(Product.TABLE_NAME, null, values);
        }
        getSupportFragmentManager().beginTransaction()
                .addToBackStack(null)
                .replace(R.id.activity_main_fragmentMain, new WaterFragment())
                .commit();
        binding.activityMainBottomNavigationBar.setSelectedItemId(R.id.ic_main);

    }


    public boolean bottomBar(BottomNavigationView navigation) {
        final EatFragment eatFragment = new EatFragment();
        final ActivityFragment activityFragment = new ActivityFragment();
        final WaterFragment waterFragment = new WaterFragment();
        final FragmentManager fragmentManager = getSupportFragmentManager();
        navigation.setOnNavigationItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.ic_main) {
                item.setChecked(true);
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.activity_main_fragmentMain, waterFragment)
                        .commit();
            } else if (id == R.id.ic_activity) {
                item.setChecked(true);
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.activity_main_fragmentMain, activityFragment)
                        .commit();
            } else if (id == R.id.ic_eat) {
                item.setChecked(true);
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.activity_main_fragmentMain, eatFragment)
                        .commit();
            }
            return false;
        });
        return false;
    }

}