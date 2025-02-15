package com.example.fitass.Fragments;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitAss.R;
import com.example.fitAss.databinding.ActivityEatListBinding;
import com.example.fitass.UserManager;
import com.example.fitass.eatlist.EatItem;
import com.example.fitass.eatlist.EatItemManager;
import com.example.fitass.eatlist.EatListAdapter;
import com.example.fitass.eatlist.Product;
import com.example.fitass.waterpage.WaterItemManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


public class EatFragment extends Fragment {

    private ActivityEatListBinding binding;
    EatItemManager eatItemManager;

    Dialog dialog;
    List<EatItem> eatItems;
    AutoCompleteTextView editTextProductTitle;
    EatListAdapter adapterEat;
    Button btnCreate;
    EditText editTextWeight;
    TextView editTextError;
    WaterItemManager waterItemManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = ActivityEatListBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        eatItemManager = new EatItemManager(getActivity());
        eatItems = eatItemManager.getEatItemsList();

        binding.activityEatListBtnAdd.setOnClickListener(v -> {
            createDialog();
            dialog.show();
        });
        eatItemManager = new EatItemManager(getActivity());
        List<EatItem> eatItems = eatItemManager.getEatItemsList();
        // создаем адаптер
        adapterEat = new EatListAdapter(getActivity(), eatItems);
        // устанавливаем для списка адаптер
        binding.activityEatListRecyclerView.setAdapter(adapterEat);


        return view;
    }

    public Dialog createDialog() {

        dialog = new Dialog(getActivity());
        dialog.setTitle("Заголовок диалога");
        dialog.setContentView(R.layout.eat_list_item_add);
        editTextProductTitle = (AutoCompleteTextView) dialog.findViewById(R.id.calorie_list_item_add_AutoComplereRextViewProduct);
        editTextWeight = (EditText) dialog.findViewById(R.id.calorie_list_item_add_editTextWeight);
        editTextError = (TextView) dialog.findViewById(R.id.calorie_list_item_add_textViewError);
        btnCreate = (Button) dialog.findViewById(R.id.eat_list_item_add_btnAdd);
        btnCreate.setOnClickListener(v -> {
            calcCalorie();
            eatItems = eatItemManager.getEatItemsList();
            adapterEat.updateList(eatItems);
            adapterEat.notifyDataSetChanged();
        });
        // адаптер
        editTextProductTitle.setAdapter(new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_dropdown_item_1line, getProductTitles(eatItemManager.getProductList())));

        return dialog;
    }

    public static ArrayList<String> getProductTitles(List<Product> productList) {
        ArrayList<String> titles = new ArrayList<>();
        int index = 0;
        for (Product p : productList) {
            titles.add(productList.get(index).getTitle());
            index++;
        }
        return titles;
    }

    public void calcCalorie() {
        if (editTextWeight.length() == 0 || editTextProductTitle.length() == 0) {
            editTextError.setText("Данные неверные");
        } else {
            UserManager userManager = new UserManager(getContext());
            int currentUser = userManager.getCurrentUserIdFromMemory();

            String todayDate = new SimpleDateFormat("dd MMMM yyyy").format(new Date());
            String stringEditTextProductTitle = editTextProductTitle.getText().toString();
            EatItemManager eatItemManager = new EatItemManager(getActivity());
            int calorie = Integer.parseInt(eatItemManager.getCalorieProduct(stringEditTextProductTitle));
            int result = (calorie * (Integer.parseInt(editTextWeight.getText().toString()))) / 100;
            String uuid = UUID.randomUUID().toString();
            eatItemManager.addEatItem(new EatItem(stringEditTextProductTitle, todayDate, "Калорий " + result, String.valueOf(currentUser), editTextWeight.getText().toString(), uuid));
            dialog.dismiss();
        }
    }
}

