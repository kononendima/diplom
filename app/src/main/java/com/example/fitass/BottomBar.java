package com.example.fitass;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;

import com.example.fitass.eatlist.EatList;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BottomBar {
    BottomNavigationView navigation;
    Context context;
    View view;
    Intent b;
        Button button;
    public BottomBar(BottomNavigationView navigation, Context context) {
        this.navigation = navigation;
        this.context = context;
    }

    public void Buttom() {
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.ic_main:
                        break;
                    case R.id.ic_activity:
                        b=new Intent(context,ActivityList.class);
                        context.startActivity(b);
                        break;
                    case R.id.ic_eat:

                        b = new Intent(context, EatList.class);
                        context.startActivity(b);
                        break;
                }

                return false;
            }
        });
    }
}
