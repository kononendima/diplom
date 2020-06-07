package com.example.fitass.login;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fitass.R;
import com.example.fitass.User;
import com.example.fitass.UserManager;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SignUp extends AppCompatActivity implements View.OnClickListener {

    EditText editTextLogin,editTextPassword,editTextHeight,editTextWeight;
    @BindView(R.id.activity_sign_up_textViewError)
    TextView editTextError;
    Spinner spinnerLifestyle;
    Button buttonRegistration;
    DatabaseReference firebaseDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        editTextLogin=(EditText)findViewById(R.id.activity_sign_up_editTextLogin);
        editTextPassword=(EditText)findViewById(R.id.activity_sign_up_editTextPassword);
        editTextHeight=(EditText)findViewById(R.id.activity_sign_up_editTextHeight);
        editTextWeight=(EditText)findViewById(R.id.activity_sign_up_editTextWeight);
        spinnerLifestyle=(Spinner) findViewById(R.id.activity_sign_up_spinnerLifestyle);
        ArrayList<String> data = new ArrayList<String>(Arrays.asList("активный", "сидячий", "средний"));
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLifestyle.setAdapter(adapter);

        buttonRegistration=(Button)findViewById(R.id.activity_sign_up_buttonRegistration);
        buttonRegistration.setOnClickListener(this);
        spinnerLifestyle.setSelection(adapter.getPosition("сидячий"));

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.activity_sign_up_buttonRegistration:
            {
               registrateUser();
               break;
            }
        }
    }
    public void registrateUser(){
        User user=new User();
        if(editTextLogin.getText().length()>1 && editTextPassword.getText().length()>1 && editTextHeight.getText().length()>1 && editTextWeight.getText().length()>1) {
            firebaseDatabase=FirebaseDatabase.getInstance().getReference("user_table");

            UUID uuid=UUID.randomUUID();
            user.setId(uuid.toString());
            user.setLogin(editTextLogin.getText().toString());
            user.setPassword(editTextPassword.getText().toString());
            user.setHeight(editTextHeight.getText().toString());
            user.setWeight(editTextWeight.getText().toString());
            user.setLifestyle(spinnerLifestyle.getSelectedItem().toString());
            firebaseDatabase.push().setValue(user);

            Intent intent=new Intent(this,SignIn.class);
            startActivity(intent);
        } else {
            editTextError.setText("Заполните, пожалуйста, все поля");
        }
    }


}
