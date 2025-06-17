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

    EditText editTextLogin, editTextPassword, editTextHeight, editTextWeight, editTextAge, editTextGender;
    Spinner spinnerLifestyle, spinnerGoal;
    Button buttonRegistration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        editTextLogin = findViewById(R.id.activity_sign_up_editTextLogin);
        editTextPassword = findViewById(R.id.activity_sign_up_editTextPassword);
        editTextHeight = findViewById(R.id.activity_sign_up_editTextHeight);
        editTextWeight = findViewById(R.id.activity_sign_up_editTextWeight);
        editTextAge = findViewById(R.id.activity_sign_up_editTextAge);
        editTextGender = findViewById(R.id.activity_sign_up_editTextGender);

        spinnerLifestyle = findViewById(R.id.activity_sign_up_spinnerLifestyle);
        spinnerGoal = findViewById(R.id.activity_sign_up_spinnerGoal);

        // Lifestyle Spinner
        String[] lifestyleOptions = {"активный", "сидячий", "средний"};
        ArrayAdapter<String> adapterLifestyle = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, lifestyleOptions);
        adapterLifestyle.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLifestyle.setAdapter(adapterLifestyle);

        String[] goalData = {"Сохранить вес", "Похудеть", "Набрать массу"};
        ArrayAdapter<String> goalAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, goalData);
        goalAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGoal.setAdapter(goalAdapter);
        spinnerGoal.setPrompt("Сохранить вес");

        buttonRegistration = findViewById(R.id.activity_sign_up_buttonRegistration);
        buttonRegistration.setOnClickListener(view -> registrateUser());
    }

    public void registrateUser() {
        if (editTextLogin.getText().length() > 1 &&
                editTextPassword.getText().length() > 1 &&
                editTextHeight.getText().length() > 1 &&
                editTextWeight.getText().length() > 1 &&
                editTextAge.getText().length() > 0 &&
                editTextGender.getText().length() > 0) {

            User user = new User();
            user.setLogin(editTextLogin.getText().toString());
            user.setPassword(editTextPassword.getText().toString());
            user.setHeight(editTextHeight.getText().toString());
            user.setWeight(editTextWeight.getText().toString());
            user.setLifestyle(spinnerLifestyle.getSelectedItem().toString());
            user.setGoal(spinnerGoal.getSelectedItem().toString());
            user.setAge(editTextAge.getText().toString());
            user.setGender(editTextGender.getText().toString());

            UserManager userManager = new UserManager(this);
            userManager.addUser(user);

            Intent intent = new Intent(this, SignIn.class);
            startActivity(intent);
        } else {
            binding.activitySignUpTextViewError.setText("Заполните, пожалуйста, все поля");
        }
    }
}
