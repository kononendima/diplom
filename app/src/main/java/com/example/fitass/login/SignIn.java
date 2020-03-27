package com.example.fitass.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.fitass.MainActivity;
import com.example.fitass.R;
import com.example.fitass.User;
import com.example.fitass.UserManager;

public class SignIn extends AppCompatActivity implements View.OnClickListener {
    EditText editTextLogin,editTextPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        editTextLogin=(EditText)findViewById(R.id.activity_sign_id_EditTextLogin);
        editTextPassword=(EditText)findViewById(R.id.activity_sign_id_EditTextPassword);
        Button btn=(Button)findViewById(R.id.activity_sign_id_btnEntrance);
        btn.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.activity_sign_id_btnEntrance:
                String login=editTextLogin.getText().toString();
                String password=editTextPassword.getText().toString();
                UserManager userManager=new UserManager(this);

                User user= userManager.entrance(login,password);

                String b=user.getLogin();
                String c=user.getPassword();
                if(b.equals(login) && c.equals(password)){
                    Intent intent=new Intent(this, MainActivity.class);
                    startActivity(intent);
                }
                break;
        }
    }
}
