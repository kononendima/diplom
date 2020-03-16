package com.example.fitass;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Calory_list extends AppCompatActivity implements View.OnClickListener {
    Dialog dialog;
    Spinner spinner;
    Button btnAdd,btnCreate;
    EditText editTextWeight;
    String[] data = {"1", "2", "3", "4", "5"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        btnAdd=(Button)findViewById(R.id.activity_calory_list_btnAdd);
        btnAdd.setOnClickListener(this);

        dialog = new Dialog(Calory_list.this);


        // Установите заголовок
        dialog.setTitle("Заголовок диалога");
        // Передайте ссылку на разметку
        dialog.setContentView(R.layout.calory_list_item_add);
        editTextWeight=(EditText)dialog.findViewById(R.id.category_list_item_add_editTextWeight);
        btnCreate=(Button)dialog.findViewById(R.id.category_list_item_add_btnAdd);
        btnCreate.setOnClickListener(this);
        spinner=(Spinner)dialog.findViewById(R.id.category_list_item_add_spinnerChoice);
        // адаптер
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
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
            case R.id.category_list_item_add_btnAdd:

                String stringEditTextWeight= editTextWeight.getText().toString();
                int a=Integer.parseInt(stringEditTextWeight);
                String stringSpinner=spinner.getSelectedItem().toString();
                int b=Integer.parseInt(stringSpinner);
                Toast.makeText(getBaseContext(), " Получилось "+a*b, Toast.LENGTH_SHORT).show();

        }
    }

}
