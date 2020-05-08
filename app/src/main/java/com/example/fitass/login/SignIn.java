package com.example.fitass.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.fitass.DataBaseHelper;
import com.example.fitass.MainActivity;
import com.example.fitass.eatlist.Product;
import com.example.fitass.R;
import com.example.fitass.User;
import com.example.fitass.UserManager;

import java.util.ArrayList;
import java.util.Arrays;

public class SignIn extends AppCompatActivity implements View.OnClickListener {
    EditText editTextLogin,editTextPassword;
    TextView textViewRegistration;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        editTextLogin=(EditText)findViewById(R.id.activity_sign_id_EditTextLogin);
        editTextPassword=(EditText)findViewById(R.id.activity_sign_id_EditTextPassword);
        Button btn=(Button)findViewById(R.id.activity_sign_id_btnEntrance);
        btn.setOnClickListener(this);
        textViewRegistration=(TextView)findViewById(R.id.activity_sign_in_textViewRegistration);
        textViewRegistration.setOnClickListener(this);
        SQLiteDatabase mDatabase=new
                DataBaseHelper(this).getWritableDatabase();
        ArrayList<String> product=new ArrayList<String>(Arrays.asList("Амарант","Амарантовые отруби Di&Di с гречей","Амарантовые отруби Di&Di с ламинарией","Амарантовые отруби Di&Di с топинамбуром","Булгур","Булгур Yelli с белыми грибами","Булгур Мистраль","Горох Makfa дробленый в пакетиках","Горох Makfa колотый","Горох Агро-Альянс колотый","Горох маш Ярмарка Платинум","Горох Мистраль Айдахо","Горох Мистраль Орегон","Горох Пассим колотый","Горох Увелка колотый","	Горох Makfa колотый"));
        ArrayList<String> cal=new ArrayList<String>(Arrays.asList("371","345","311","345","342","330","330","343","82","299","299","350","312","317","299","360"));

        for(int i = 0; i<product.size(); i++){
            ContentValues values = new ContentValues();
            values.put(Product.TITLE, product.get(i).toString());
            values.put(Product.CALROIE_PRODUCT, cal.get(i).toString());
            mDatabase.insert(Product.TABLE_NAME, null, values);
        }
//this.deleteDatabase("DataBase.db"); //Удаление бд
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.activity_sign_id_btnEntrance:
                String login=editTextLogin.getText().toString();
                String password=editTextPassword.getText().toString();
                UserManager userManager=new UserManager(this);

                user= userManager.entrance(login,password);

                String b=user.getLogin();
                String c=user.getPassword();
                if(b.equals(login) && c.equals(password)){
                    userManager.saveToMemoryUserData(login,password);
                    Intent intent=new Intent(this, MainActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.activity_sign_in_textViewRegistration:
                Intent intent=new Intent(this, SignUp.class);
                startActivity(intent);
        }
    }
}
