package com.example.fitass.activitypage;




import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import com.example.fitass.R;
//import com.example.fitass.ServicePedometer;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityList extends AppCompatActivity {

     TextView textViewResult;

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        BottomNavigationView navigation1 = (BottomNavigationView) findViewById(R.id.activity_main_bottomNavigationBar);

        textViewResult =(TextView)findViewById(R.id.textViewResult);
      //  startService(new Intent(this, ServicePedometer.class));
     //   mm();

    }
    public void mm(){
        MyRetrofit myRetrofit=new MyRetrofit();


        JsonPlaceHolder jsonPlaceHolderApi =myRetrofit.getRetrofit().create(JsonPlaceHolder.class);

        Call<List<Post>> call = jsonPlaceHolderApi.getPosts();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {

                if (!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code());
                    return;
                }

                List<Post> posts = response.body();

                for (Post post : posts) {
                    String content = "";
                    content += "ID: " + post.getId() + "\n";
                    content += "User ID: " + post.getUserId() + "\n";
                    content += "Title: " + post.getTitle() + "\n";
                    content += "Text: " + post.getText() + "\n\n";
                    textViewResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }
}
