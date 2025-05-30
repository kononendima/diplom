package com.example.fitass.receipeDetails;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.fitAss.R;

public class RecipeDetailActivity extends AppCompatActivity {

    TextView textTitle, textDescription, textCalories;
    ImageView imageRecipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        textTitle = findViewById(R.id.textDetailTitle);
        textDescription = findViewById(R.id.textDetailDescription);
        textCalories = findViewById(R.id.textDetailCalories);
        imageRecipe = findViewById(R.id.imageDetailRecipe);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String description = intent.getStringExtra("description");
        int calories = intent.getIntExtra("calories", 0);
        String imageUrl = intent.getStringExtra("imageUrl");

        textTitle.setText(title);
        textDescription.setText(description);
        textCalories.setText(calories + " ккал");

        Glide.with(this).load(imageUrl).into(imageRecipe);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
