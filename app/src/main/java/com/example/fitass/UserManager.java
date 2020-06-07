package com.example.fitass;

import android.content.ContentValues;
import android.content.Context;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.CursorWrapper;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.fitass.eatlist.EatItem;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.internal.cache.DiskLruCache;

import static android.content.Context.MODE_PRIVATE;
import static com.firebase.ui.auth.AuthUI.TAG;

public class UserManager {
    private Context mContext;
    public User user;
    protected CompositeDisposable disposable = new CompositeDisposable();
    DatabaseReference databaseReference;
    private SQLiteDatabase mDatabase;
    SharedPreferences sPref;


    public UserManager(Context context) {
        this.mContext = context;
        mDatabase = new
                DataBaseHelper(mContext).getWritableDatabase();
    }

    public static ContentValues getContentValues(User user) {
        ContentValues values = new ContentValues();
        values.put(User.LOGIN, user.getLogin());
        values.put(User.PASSWORD, user.getPassword());
        values.put(User.HEIGHT, user.getHeight());
        values.put(User.WEIGHT, user.getWeight());
        values.put(User.LIFESTYLE, user.getLifestyle());
        return values;
    }


    public void checkRegistration(
            //   String login, String password
    ) {

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("user_table").child("-M97Tj-ANg8dFBMFYoVB");


        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    User s = dataSnapshot.getValue(User.class);
                  saveToUser(s);
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



//       if(user.getPassword()==password){
//            return user;
//        }else{
//            return null;
//        }
//        Observable.create(Observable.just(query))
//        Observable.defer(() -> Observable.just()
//                .subscribeOn(Schedulers.io())
//                .subscribe());
//        Cursor cursor = mDatabase.rawQuery("SELECT * from " + User.TABLE_NAME + " WHERE (login = '" + login + "' and password = '" + password + "');", null);
//        if (cursor.getCount() == 0) {
//            return null;
//        } else {
//            if (cursor.moveToFirst()) {
//                do {
//                    user.setId(cursor.getString(0));
//                    user.setLogin(cursor.getString(1));
//                    user.setPassword(cursor.getString(2));
//                    user.setHeight(cursor.getString(3));
//                    user.setWeight(cursor.getString(4));
//                } while (cursor.moveToNext());
//            }
//            cursor.close();
//            return user;
//        }

    }
    public void saveToUser(User _user){
        user=_user;

    }
    public User getUser(){
        return user;
    }
    public void saveToMemoryUserData(String login, String password, String height, String weight){
        SharedPreferences sPref;
        sPref = mContext.getSharedPreferences("Data",MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString("id",user.getId());
        ed.putString("login", login);
        ed.putString("password", password);
        ed.putString("height",height);
        ed.putString("weight",weight);
        ed.commit();

    }

    public int getCurrentUserIdFromMemory(){
        sPref = mContext.getSharedPreferences("Data",Context.MODE_PRIVATE);
        int id = Integer.parseInt(sPref.getString("id","0"));
        return id;
    }
    public int getCurrentUserWeight(){
        sPref = mContext.getSharedPreferences("Data",Context.MODE_PRIVATE);
        int weight = Integer.parseInt(sPref.getString("weight","0"));
        return weight;
    }

    public int getCurrentUserHeightFromMemory(){
        sPref = mContext.getSharedPreferences("Data",Context.MODE_PRIVATE);
        int height = Integer.parseInt(sPref.getString("height","0"));
        return height;
    }

}
