package com.example.fitass.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.fitass.R;
import com.example.fitass.User;
import com.example.fitass.UserManager;

public class SignUp extends AppCompatActivity implements View.OnClickListener {
    EditText editTextLogin,editTextPassword,editTextHeight,editTextWeight;
    Spinner spinnerLifestyle;
    Button buttonRegistration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        editTextLogin=(EditText)findViewById(R.id.activity_sign_up_editTextLogin);
        editTextPassword=(EditText)findViewById(R.id.activity_sign_up_editTextPassword);
        editTextHeight=(EditText)findViewById(R.id.activity_sign_up_editTextHeight);
        editTextWeight=(EditText)findViewById(R.id.activity_sign_up_editTextWeight);
        spinnerLifestyle=(Spinner) findViewById(R.id.activity_sign_up_spinnerLifestyle);
        String[] data = {"активный", "сидячий", "средний"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLifestyle.setAdapter(adapter);
        buttonRegistration=(Button)findViewById(R.id.activity_sign_up_buttonRegistration);
        buttonRegistration.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.activity_sign_up_buttonRegistration:
            {
                User user=new User();
                user.setLogin(editTextLogin.getText().toString());
                user.setPassword(editTextPassword.getText().toString());
                user.setHeight(editTextHeight.getText().toString());
                user.setWeight(editTextWeight.getText().toString());
                user.setLifestyle(spinnerLifestyle.getSelectedItem().toString());
                UserManager userManager=new UserManager(this);
                userManager.addUser(user);

                break;
            }
        }
    }
}
