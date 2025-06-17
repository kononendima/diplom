package com.example.fitass.profile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.fitAss.R;
import com.example.fitass.User;
import com.example.fitass.UserManager;
import com.example.fitass.activitypage.ActivityListManager;
import com.example.fitass.eatlist.EatItemManager;
import com.example.fitass.login.SignIn;
import com.example.fitass.note.NoteListActivity;
import com.example.fitass.receipe.RecipeListActivity;
import com.example.fitass.waterpage.WaterItemManager;

public class ProfileFragment extends Fragment {
    private EditText editWeight;
    private TextView textEmail, textSteps, textCalories, textVolume;
    private Button buttonWeightMinus, buttonWeightPlus, buttonLogout, buttonMyRecipes, buttonMyNotes;

    ActivityListManager activityListManager;

    public ProfileFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(com.example.fitAss.R.layout.fragment_profile, container, false);

        activityListManager = new ActivityListManager(requireContext());

        textEmail = view.findViewById(R.id.textEmail);
        buttonLogout = view.findViewById(R.id.buttonLogout);
        buttonMyRecipes = view.findViewById(R.id.buttonMyRecipes);
        textSteps = view.findViewById(R.id.textSteps);
        textCalories = view.findViewById(R.id.textCalories);
        textVolume = view.findViewById(R.id.textVolume);
        editWeight = view.findViewById(R.id.editWeight);
        buttonWeightMinus = view.findViewById(R.id.buttonWeightMinus);
        buttonWeightPlus = view.findViewById(R.id.buttonWeightPlus);

        buttonMyNotes = view.findViewById(R.id.buttonMyNotes);
        buttonMyNotes.setOnClickListener(v -> {
            startActivity(new Intent(requireContext(), NoteListActivity.class));
        });

        SharedPreferences prefs = requireContext().getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        UserManager userManager = new UserManager(requireContext());
        EatItemManager eatItemManager = new EatItemManager(requireContext());
        WaterItemManager waterItemManager = new WaterItemManager(requireContext());

        textEmail.setText(userManager.getCurrentUserLogin());
        textSteps.setText(activityListManager.getCurrentUserSteps() + " шагов");
        editWeight.setText(userManager.getCurrentUserWeight() + " кг");
        textCalories.setText(eatItemManager.getTodayCalories(String.valueOf(userManager.getCurrentUserIdFromMemory())) + " калорий");
        textVolume.setText(waterItemManager.getTodayWater(String.valueOf(userManager.getCurrentUserIdFromMemory())) + " мл");
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

        buttonWeightMinus.setOnClickListener(v -> {
            try {
                String text = editWeight.getText().toString();
                float weight = Float.parseFloat(text.substring(0, text.length() - 3));
                weight = Math.max(0, weight - 0.1f); // не даем уйти в минус
                editWeight.setText(String.format("%.1f", weight));
                userManager.updateUserWeight(userManager.getCurrentUserIdFromMemory(), weight);
            } catch (NumberFormatException ignored) {
            }
        });

        buttonWeightPlus.setOnClickListener(v -> {
            try {
                String text = editWeight.getText().toString();
                float weight = Float.parseFloat(text.substring(0, text.length() - 3));
                weight += 0.1f;
                editWeight.setText(String.format("%.1f", weight));
                userManager.updateUserWeight(userManager.getCurrentUserIdFromMemory(), weight);
            } catch (NumberFormatException ignored) {
            }
        });

        int userId = userManager.getCurrentUserIdFromMemory();
        Handler handler = new Handler(Looper.getMainLooper());

        Runnable saveWeightRunnable = () -> {
            try {
                float newWeight = Float.parseFloat(editWeight.getText().toString());
                userManager.updateUserWeight(userId, newWeight);
            } catch (NumberFormatException e) {
            }
        };


        User user = userManager.getUserById(userId);

        double dailyCalories = calculateCalories(Double.parseDouble(user.getWeight()), Double.parseDouble(user.getHeight()), Integer.parseInt(user.getAge()), user.getLifestyle(), user.getGoal(), user.getGender());

        textCalories.setText("Съедено: " + eatItemManager.getTodayCalories(String.valueOf(userManager.getCurrentUserIdFromMemory())) + " из " + (int) dailyCalories + " ккал");

        editWeight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // сбрасываем предыдущее сохранение
                handler.removeCallbacks(saveWeightRunnable);
                // откладываем новое на 1 секунду
                handler.postDelayed(saveWeightRunnable, 1000);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        return view;
    }

    private double calculateCalories(double weight, double height, int age, String lifestyle, String goal, String gender) {
        double bmr;
        double activityCoefficient = 1.2;

        if (lifestyle.equals("средний")) activityCoefficient = 1.55;
        else if (lifestyle.equals("активный")) activityCoefficient = 1.725;

        if (goal.equals("похудеть")) {
            bmr = gender.equals("мужчина") ?
                    (10 * weight + 6.25 * height - 5 * age + 5) :
                    (10 * weight + 6.25 * height - 5 * age - 161);

        } else if (goal.equals("набрать массу")) {
            bmr = gender.equals("мужчина") ?
                    (88.362 + 13.397 * weight + 4.799 * height - 5.677 * age) * activityCoefficient :
                    (447.593 + 9.247 * weight + 3.098 * height - 4.330 * age) * activityCoefficient;

        } else { // поддерживать вес
            if (gender.equals("мужчина")) {
                if (lifestyle.equals("сидячий")) bmr = weight * 31 + 375;
                else if (lifestyle.equals("средний")) bmr = weight * 33 + 415;
                else bmr = weight * 35 + 500;
            } else {
                if (lifestyle.equals("сидячий")) bmr = weight * 27 + 375;
                else if (lifestyle.equals("средний")) bmr = weight * 29 + 415;
                else bmr = weight * 31 + 500;
            }
        }
        return Math.round(bmr);
    }

}
