package com.example.fitass.Fragments;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.example.fitass.R;
import com.example.fitass.UserManager;
import com.example.fitass.eatlist.EatItem;
import com.example.fitass.eatlist.EatListAdapter;
import com.example.fitass.waterpage.WaterItem;
import com.example.fitass.waterpage.WaterItemManager;
import com.example.fitass.waterpage.WaterListAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;



public class WaterFragment extends Fragment implements View.OnClickListener {
    Dialog dialog;

    AutoCompleteTextView editTextProductType;
    WaterItemManager waterItemManager;

    TextView textViewError;
    @BindView(R.id.water_list_btnAdd)
    FloatingActionButton btnAdd;
    @BindView(R.id.water_list_recyclerView)
    RecyclerView recyclerView;
    WaterListAdapter waterListAdapter;
    Button btnCreate;
    EditText editTextProductVolume;
    UserManager userManager;
    List<WaterItem> waterItems;
    @Override
    public void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.water_list, container, false);
        ButterKnife.bind(this,v);
        btnAdd.setOnClickListener(this);

        waterItemManager=new WaterItemManager(getActivity());
        waterItems=waterItemManager.getWaterList();
        waterListAdapter = new WaterListAdapter(getActivity(), waterItems);

        recyclerView.setAdapter(waterListAdapter);
        return v;
    }
    public Dialog createDialog(){

        dialog = new Dialog(getActivity());
        dialog.setTitle("Заголовок диалога");
        dialog.setContentView(R.layout.water_list_item_add);
        editTextProductType=(AutoCompleteTextView)dialog.findViewById(R.id.water_list_item_add_AutoComplereRextViewProduct);
        editTextProductVolume=(EditText)dialog.findViewById(R.id.water_list_item_add_editTextVolume);
        btnCreate=(Button)dialog.findViewById(R.id.eat_list_item_add_btnAdd);
        btnCreate.setOnClickListener(this);
        textViewError=(TextView)dialog.findViewById(R.id.water_list_item_add_editTextError);

        return dialog;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.water_list_btnAdd:
                createDialog();
                dialog.show();
                break;
            case R.id.eat_list_item_add_btnAdd:
                if(editTextProductVolume.length()==0 || editTextProductType.length()==0) {
                    textViewError.setText("Данные неверные");
                }else {
                    userManager = new UserManager(getActivity());
                    String id = String.valueOf(userManager.getCurrentUserIdFromMemory());
                    String todayDate = new SimpleDateFormat("dd MMMM yyyy").format(new Date());
                    WaterItem waterItem = new WaterItem(id, editTextProductVolume.getText().toString(), todayDate, editTextProductType.getText().toString());
                    waterItemManager.saveStepsToDb(waterItem);
                    waterItems=waterItemManager.getWaterList();
                    waterListAdapter.updateList(waterItems);
                    dialog.dismiss();
                }
                break;
        }
    }


}
