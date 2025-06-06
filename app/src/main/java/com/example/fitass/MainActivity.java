package com.example.fitass;


import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.fitAss.R;
import com.example.fitAss.databinding.ActivityMainBinding;
import com.example.fitass.Fragments.ActivityFragment;
import com.example.fitass.Fragments.EatFragment;
import com.example.fitass.Fragments.WaterFragment;
import com.example.fitass.eatlist.Product;
import com.example.fitass.profile.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Arrays;

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
                .replace(R.id.activity_main_fragmentMain, new ProfileFragment())
                .commit();
        binding.activityMainBottomNavigationBar.setSelectedItemId(R.id.ic_profile);

    }


    public boolean bottomBar(BottomNavigationView navigation) {
        final EatFragment eatFragment = new EatFragment();
        final ActivityFragment activityFragment = new ActivityFragment();
        final WaterFragment waterFragment = new WaterFragment();
        final FragmentManager fragmentManager = getSupportFragmentManager();
        ProfileFragment profileFragment = new ProfileFragment();
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
            } else if (id == R.id.ic_profile) {
                item.setChecked(true);
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.activity_main_fragmentMain, profileFragment)
                        .commit();
            }
            return false;
        });
        return false;
    }

}