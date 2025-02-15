package com.example.fitass.Fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitAss.R;
import com.example.fitAss.databinding.ActivityEatListBinding;
import com.example.fitAss.databinding.WaterListBinding;
import com.example.fitass.UserManager;
import com.example.fitass.waterpage.WaterItem;
import com.example.fitass.waterpage.WaterItemManager;
import com.example.fitass.waterpage.WaterListAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class WaterFragment extends Fragment {
    private WaterListBinding binding;

    Dialog dialog;

    AutoCompleteTextView editTextProductType;
    WaterItemManager waterItemManager;

    TextView textViewError;
    WaterListAdapter waterListAdapter;
    Button btnCreate;
    EditText editTextProductVolume;
    UserManager userManager;
    List<WaterItem> waterItems;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = WaterListBinding.inflate(inflater, container, false);
        binding.waterListBtnAdd.setOnClickListener(view -> {
            createDialog();
            dialog.show();
        });
        waterItemManager = new WaterItemManager(getActivity());
        waterItems = waterItemManager.getWaterList();
        waterListAdapter = new WaterListAdapter(getActivity(), waterItems);

        binding.waterListRecyclerView.setAdapter(waterListAdapter);
        return binding.getRoot();
    }

    public Dialog createDialog() {

        dialog = new Dialog(getActivity());
        dialog.setTitle("Заголовок диалога");
        dialog.setContentView(R.layout.water_list_item_add);
        editTextProductType = (AutoCompleteTextView) dialog.findViewById(R.id.water_list_item_add_AutoComplereRextViewProduct);
        editTextProductVolume = (EditText) dialog.findViewById(R.id.water_list_item_add_editTextVolume);
        btnCreate = (Button) dialog.findViewById(R.id.eat_list_item_add_btnAdd);
        btnCreate.setOnClickListener(view -> {
            if (editTextProductVolume.length() == 0 || editTextProductType.length() == 0) {
                textViewError.setText("Данные неверные");
            } else {
                userManager = new UserManager(getActivity());
                String id = String.valueOf(userManager.getCurrentUserIdFromMemory());
                String todayDate = new SimpleDateFormat("dd MMMM yyyy").format(new Date());
                UUID uuid = UUID.randomUUID();
                WaterItem waterItem = new WaterItem(id, editTextProductVolume.getText().toString(), todayDate, editTextProductType.getText().toString(), uuid.toString());
                waterItemManager.addWater(waterItem);
                waterItems = waterItemManager.getWaterList();
                waterListAdapter.updateList(waterItems);
                dialog.dismiss();
            }
        });
        textViewError = (TextView) dialog.findViewById(R.id.water_list_item_add_editTextError);

        return dialog;
    }
}
