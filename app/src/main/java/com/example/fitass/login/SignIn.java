package com.example.fitass.login;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.fitAss.R;
import com.example.fitAss.databinding.ActivitySignInBinding;
import com.example.fitass.DataBaseHelper;
import com.example.fitass.MainActivity;
import com.example.fitass.User;
import com.example.fitass.UserManager;
import com.example.fitass.eatlist.Product;

import java.util.ArrayList;
import java.util.Arrays;

public class SignIn extends AppCompatActivity {

    private ActivitySignInBinding binding;
    EditText editTextLogin, editTextPassword;
    TextView textViewRegistration;
    String inputLogin, inputPassword;
    User user;
    UserManager userManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            if (checkSelfPermission(Manifest.permission.ACTIVITY_RECOGNITION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.ACTIVITY_RECOGNITION}, 1);
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (checkSelfPermission(Manifest.permission.FOREGROUND_SERVICE_HEALTH) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.FOREGROUND_SERVICE_HEALTH}, 2);
            }
        }
        editTextLogin = (EditText) findViewById(R.id.activity_sign_id_EditTextLogin);
        editTextPassword = (EditText) findViewById(R.id.activity_sign_id_EditTextPassword);
        Button btn = (Button) findViewById(R.id.activity_sign_id_btnEntrance);
        btn.setOnClickListener(view -> {
            inputLogin = editTextLogin.getText().toString();
            inputPassword = editTextPassword.getText().toString();
            userManager = new UserManager(this);

            user = userManager.checkRegistration(inputLogin, inputPassword);
            entrance(user);
        });
        textViewRegistration = (TextView) findViewById(R.id.activity_sign_in_textViewRegistration);
        textViewRegistration.setOnClickListener(view -> {
            Intent intent = new Intent(this, SignUp.class);
            startActivity(intent);
        });
        SQLiteDatabase mDatabase = new
                DataBaseHelper(this).getWritableDatabase();
        ArrayList<String> product = new ArrayList<String>(Arrays.asList("Амарант", "Амарантовые отруби Di&Di с гречей", "Амарантовые отруби Di&Di с ламинарией", "Амарантовые отруби Di&Di с топинамбуром", "Булгур", "Булгур Yelli с белыми грибами", "Булгур Мистраль", "Горох Makfa дробленый в пакетиках", "Горох Makfa колотый", "Горох Агро-Альянс колотый", "Горох маш Ярмарка Платинум", "Горох Мистраль Айдахо", "Горох Мистраль Орегон", "Горох Пассим колотый", "Горох Увелка колотый", "	Горох Makfa колотый"));
        ArrayList<String> cal = new ArrayList<String>(Arrays.asList("371", "345", "311", "345", "342", "330", "330", "343", "82", "299", "299", "350", "312", "317", "299", "360"));

        for (int i = 0; i < product.size(); i++) {
            ContentValues values = new ContentValues();
            values.put(Product.TITLE, product.get(i).toString());
            values.put(Product.CALROIE_PRODUCT, cal.get(i).toString());
            mDatabase.insert(Product.TABLE_NAME, null, values);
        }
        //this.deleteDatabase("DataBase.db"); //Удаление бд
    }

    public void entrance(User user) {
        if (user == null) {
            binding.activitySignInTextViewError.setText("Неверные данные");
        } else {
            String b = user.getLogin();
            String c = user.getPassword();
            if (b.equals(inputLogin) && c.equals(inputPassword)) {
                userManager.saveToMemoryUserData(inputLogin, inputPassword, user.getHeight(), user.getWeight());
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }
        }
    }
}
