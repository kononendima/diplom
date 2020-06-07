package com.example.fitass;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.content.FileProvider;

import com.example.fitass.Retrofit.RetrofitService;
import com.google.firebase.auth.FirebaseAuth;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SyncDb {
    Context mContext;
    private FirebaseAuth mAuth;

    public SyncDb(Context mContext) {
        this.mContext = mContext;
    }

    public void downloadDB(String path) {
        mAuth = FirebaseAuth.getInstance();
        RetrofitService.getInstance()
                .getJSONApi()
                .downloadDB("download")
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
    public void saveDb(ResponseBody responseBody){
        File file = new File(mContext.getFilesDir(),"123.txt");

        // создаём новое намерение
        Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);

// устанавливаем флаг для того, чтобы дать внешнему приложению пользоваться нашим FileProvider
        intent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);

// генерируем URI, я определил полномочие как ID приложения в манифесте, последний параметр это файл, который я хочу открыть
        Uri uri = FileProvider.getUriForFile(mContext, BuildConfig.APPLICATION_ID, file);

// я открываю PDF-файл, поэтому я даю ему действительный тип MIME
        intent.setDataAndType(uri, "application/pdf");

// подтвердите, что устройство может открыть этот файл!
//        PackageManager pm = mContext.getPackageManager();
//        if (intent.resolveActivity(pm) != null) {
//            mContext.startActivity(intent);
//        }
    }
    public void saveFile(
            ResponseBody responseBody
    ) {
        File folder =new File(mContext.getFilesDir().toString(), "123.txt");
        folder.mkdirs();

        File unzipMe = new File(folder, "123.txt");
        if (!unzipMe.exists()){
            try {
                unzipMe.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        BufferedOutputStream bos = null;
        try {
            bos = new BufferedOutputStream(new FileOutputStream(unzipMe));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            bos.write(responseBody.bytes());
            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}