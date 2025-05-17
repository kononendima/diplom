package com.example.fitass.profile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.fitAss.R;
import com.example.fitass.UserManager;
import com.example.fitass.activitypage.ActivityListManager;
import com.example.fitass.login.SignIn;
import com.example.fitass.receipe.RecipeListActivity;

public class ProfileFragment extends Fragment {

    private TextView textUsername, textEmail, textSteps, textWeight;
    private Button buttonLogout, buttonMyRecipes;

    ActivityListManager activityListManager;


    public ProfileFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(com.example.fitAss.R.layout.fragment_profile, container, false);

        activityListManager = new ActivityListManager(requireContext());

        textUsername = view.findViewById(R.id.textUsername);
        textEmail = view.findViewById(R.id.textEmail);
        buttonLogout = view.findViewById(R.id.buttonLogout);
        buttonMyRecipes = view.findViewById(R.id.buttonMyRecipes);
        textSteps = view.findViewById(R.id.textSteps);
        textWeight = view.findViewById(R.id.textWeight);

        // Получение данных пользователя из SharedPreferences
        SharedPreferences prefs = requireContext().getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        UserManager userManager = new UserManager(requireContext());

        textUsername.setText("Имя пользователя");
        textEmail.setText(userManager.getCurrentUserLogin());
        textSteps.setText(activityListManager.getCurrentUserSteps() + " шагов");
        textWeight.setText(userManager.getCurrentUserWeight() + " кг");


        // Кнопка выхода
        buttonLogout.setOnClickListener(v -> {
            SharedPreferences.Editor editor = prefs.edit();
            editor.clear();
            editor.apply();

            Intent intent = new Intent(getActivity(), SignIn.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });

        // Кнопка перехода к рецептам
        buttonMyRecipes.setOnClickListener(v -> {
            startActivity(new Intent(requireContext(), RecipeListActivity.class));
        });

        return view;
    }
}
