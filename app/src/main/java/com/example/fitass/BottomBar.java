package com.example.fitass;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BottomBar {
        Context mContext;
    public void Buttom(Context context) {
        mContext=context;
    }

    BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()

    {
        @Override
        public boolean onNavigationItemSelected (@NonNull MenuItem item){
        switch (item.getItemId()) {
            case R.id.ic_main:
                break;
            case R.id.ic_activity:

                break;
            case R.id.ic_eat:
                Intent b = new Intent(MainActivity.this, EatList.class);
                startActivity(b);
                break;
        }

        return false;
    }
    });
}
}
