package com.example.fitass.eatlist;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;


import android.app.Dialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.fitass.BottomBar;
import com.example.fitass.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Date;

public class EatList extends AppCompatActivity implements View.OnClickListener {

    List<EatItem> eat = new ArrayList<>();
    Dialog dialog;
    AutoCompleteTextView editTextProduct;

    Button btnCreate;
    EditText editTextWeight;
    String[] data = {"1", "2", "3", "4", "5"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eat_list);
        BottomNavigationView navigation1 = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        BottomBar bottomBarEat=new BottomBar(navigation1,this);
        bottomBarEat.Buttom();


        Button btnAdd=(Button)findViewById(R.id.activity_eat_list_btnAdd);
        btnAdd.setOnClickListener(this);
        EatItemManager eatItemManager = new EatItemManager(this);
        List<EatItem> eatItems = eatItemManager.getEatItemsList();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.activity_eat_list_recyclerView);
        // создаем адаптер
        EatListAdapter adapterEat = new EatListAdapter(this, eatItems);
        // устанавливаем для списка адаптер
        recyclerView.setAdapter(adapterEat);
        dialog = new Dialog(EatList.this);



        dialog.setTitle("Заголовок диалога");

        dialog.setContentView(R.layout.eat_list_item_add);// ссылка на разметку
        editTextWeight=(EditText)dialog.findViewById(R.id.calorie_list_item_add_editTextWeight);
        btnCreate=(Button)dialog.findViewById(R.id.eat_list_item_add_btnAdd);
        btnCreate.setOnClickListener(this);
        editTextProduct=(AutoCompleteTextView )dialog.findViewById(R.id.calorie_list_item_add_AutoComplereRextViewProduct);
        // адаптер
        ArrayAdapter<String> adapterSpinner = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data);
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        editTextProduct.setAdapter(adapterSpinner);
        // заголовок

        // устанавливаем обработчик нажатия
        editTextProduct.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // показываем позиция нажатого элемента
              //  Toast.makeText(getBaseContext(), "Position = " + position, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
    }




    public void onClick(View v)
    {
        Date date;
        switch (v.getId()) {
            case R.id.activity_eat_list_btnAdd:
                dialog.show();
                break;
            case R.id.eat_list_item_add_btnAdd:
                date=new Date();
                String stringDate=date.toString();

                String stringSpinner=spinner.getSelectedItem().toString();

                Toast.makeText(getBaseContext(), " Получилось "+stringSpinner, Toast.LENGTH_SHORT).show();
                String result=stringSpinner;

                EatItemManager eatItemManager=new EatItemManager(this);
                eatItemManager.addEatItem(new EatItem(stringSpinner,stringDate,"Калорий "+result));
        }
    }


}
