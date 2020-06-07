package com.example.fitass.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

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

import com.example.fitass.DataBaseHelper;
import com.example.fitass.MainActivity;
import com.example.fitass.eatlist.Product;
import com.example.fitass.R;
import com.example.fitass.User;
import com.example.fitass.UserManager;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class SignIn extends AppCompatActivity implements View.OnClickListener {
    EditText editTextLogin,editTextPassword;
    TextView textViewRegistration;
    String inputLogin,inputPassword;
    protected CompositeDisposable disposable = new CompositeDisposable();
    User user;
    UserManager userManager;
    @BindView(R.id.activity_sign_in_textViewError)
    TextView textViewError;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ButterKnife.bind(this);
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACTIVITY_RECOGNITION)== PackageManager.PERMISSION_DENIED){
            requestPerms();
        }
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_DENIED){
            requestPerms();
        }
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_DENIED){
            requestPerms();
        }
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
        userManager=new UserManager(this);


        //this.deleteDatabase("DataBase.db"); //Удаление бд
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.activity_sign_id_btnEntrance:
                 inputLogin=editTextLogin.getText().toString();
                 inputPassword=editTextPassword.getText().toString();
                 userManager=new UserManager(this);
                 userManager.checkRegistration();
//                 user=userManager.user;
                 entrance(user);
                break;
            case R.id.activity_sign_in_textViewRegistration:
                Intent intent=new Intent(this, SignUp.class);
                startActivity(intent);
        }
    }
    public void entrance(User user){
        if(user==null){
            textViewError.setText("Неверные данные");
        }else {
            String b = user.getLogin();
            String c = user.getPassword();
            if (b.equals(inputLogin) && c.equals(inputPassword)) {
                userManager.saveToMemoryUserData(inputLogin, inputPassword, user.getHeight(),user.getWeight());
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }
        }
    }
    private void requestPerms(){
        String[] perm = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.ACTIVITY_RECOGNITION,Manifest.permission.READ_EXTERNAL_STORAGE};
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            ActivityCompat.requestPermissions(this,perm,123);
        }
    }
}
