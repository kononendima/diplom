package com.example.fitass.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fitAss.R;
import com.example.fitAss.databinding.ActivitySignUpBinding;
import com.example.fitass.User;
import com.example.fitass.UserManager;

public class SignUp extends AppCompatActivity {

    private ActivitySignUpBinding binding;

    EditText editTextLogin, editTextPassword, editTextHeight, editTextWeight;
    Spinner spinnerLifestyle;
    Button buttonRegistration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        editTextLogin = (EditText) findViewById(R.id.activity_sign_up_editTextLogin);
        editTextPassword = (EditText) findViewById(R.id.activity_sign_up_editTextPassword);
        editTextHeight = (EditText) findViewById(R.id.activity_sign_up_editTextHeight);
        editTextWeight = (EditText) findViewById(R.id.activity_sign_up_editTextWeight);
        spinnerLifestyle = (Spinner) findViewById(R.id.activity_sign_up_spinnerLifestyle);
        String[] data = {"активный", "сидячий", "средний"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLifestyle.setAdapter(adapter);
        buttonRegistration = (Button) findViewById(R.id.activity_sign_up_buttonRegistration);
        buttonRegistration.setOnClickListener(view -> {
            registrateUser();
        });

    }

    public void registrateUser() {
        User user = new User();
        if (editTextLogin.getText().length() > 1 && editTextPassword.getText().length() > 1 && editTextHeight.getText().length() > 1 && editTextWeight.getText().length() > 1) {

            user.setLogin(editTextLogin.getText().toString());
            user.setPassword(editTextPassword.getText().toString());
            user.setHeight(editTextHeight.getText().toString());
            user.setWeight(editTextWeight.getText().toString());
            user.setLifestyle(spinnerLifestyle.getSelectedItem().toString());
            UserManager userManager = new UserManager(this);
            userManager.addUser(user);
            Intent intent = new Intent(this, SignIn.class);
            startActivity(intent);
        } else {
            binding.activitySignUpTextViewError.setText("Заполните, пожалуйста, все поля");
        }
    }
}
