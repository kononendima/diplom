package com.example.fitass;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ButtomNavigationBar {
    Context mContex;
    Intent intent;

    public ButtomNavigationBar(Context context) {
        mContex = context;
    }

    public void Choser() {
        BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.ic_main:

                                return true;
                            case R.id.ic_activity:

                                return true;
                            case R.id.ic_eat:
                                intent = new Intent(mContex, Calory_list.class);
                                return true;
                        }
                        return false;
                    }

                };
    }
}