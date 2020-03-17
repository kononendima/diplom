package com.example.fitass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;


import android.app.Dialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class EatList extends AppCompatActivity implements View.OnClickListener {

    List<EatItem> eat = new ArrayList<>();
    Dialog dialog;
    Spinner spinner;

    Button btnCreate;
    EditText editTextWeight;
    String[] data = {"1", "2", "3", "4", "5"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eat_list);



        Button btnAdd=(Button)findViewById(R.id.activity_calory_list_btnAdd);
        btnAdd.setOnClickListener(this);
        setInitialData();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.activity_eat_list_recyclerView);
        // создаем адаптер
        EatListAdapter adapterEat = new EatListAdapter(this, eat);
        // устанавливаем для списка адаптер
        recyclerView.setAdapter(adapterEat);
        dialog = new Dialog(EatList.this);


        // Установите заголовок
        dialog.setTitle("Заголовок диалога");
        // Передайте ссылку на разметку
        dialog.setContentView(R.layout.eat_list_item_add);
        editTextWeight=(EditText)dialog.findViewById(R.id.calory_list_item_add_editTextWeight);
        btnCreate=(Button)dialog.findViewById(R.id.calory_list_item_add_btnAdd);
        btnCreate.setOnClickListener(this);
        spinner=(Spinner)dialog.findViewById(R.id.calory_list_item_add_spinnerChoice);
        // адаптер
        ArrayAdapter<String> adapterSpinner = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data);
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapterSpinner);
        // заголовок
        spinner.setPrompt("Title");
        // выделяем элемент
        spinner.setSelection(2);
        // устанавливаем обработчик нажатия
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // показываем позиция нажатого элемента
                Toast.makeText(getBaseContext(), "Position = " + position, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
    }




    public void onClick(View v)
    {
        switch (v.getId()) {
            case R.id.activity_calory_list_btnAdd:
                dialog.show();
                break;
            case R.id.calory_list_item_add_btnAdd:

                String stringEditTextWeight= editTextWeight.getText().toString();
                int a=Integer.parseInt(stringEditTextWeight);
                String stringSpinner=spinner.getSelectedItem().toString();
                int b=Integer.parseInt(stringSpinner);
                Toast.makeText(getBaseContext(), " Получилось "+a*b, Toast.LENGTH_SHORT).show();

        }
    }
    private void setInitialData(){

        eat.add(new EatItem ("fdsfdsf", "123","fsdfdsf"));
        eat.add(new EatItem ("fdsfdsf", "123","fsdfdsf"));
        eat.add(new EatItem ("fdsfdsf", "123","fsdfdsf"));
        eat.add(new EatItem ("fdsfdsf", "123","fsdfdsf"));
    }

}
