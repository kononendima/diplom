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
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import waterpage.WaterItem;
import waterpage.WaterItemManager;
import waterpage.WaterListAdapter;


public class WaterFragment extends Fragment implements View.OnClickListener {
    Dialog dialog;
    List<WaterItem> waterItems;
    AutoCompleteTextView editTextProductType;
    @BindView(R.id.water_list_btnAdd)
    FloatingActionButton btnAdd;
    @BindView(R.id.water_list_recyclerView)
    RecyclerView recyclerView;

    Button btnCreate;
    EditText editTextProductVolume;
    WaterItemManager waterItemManager;

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
        return v;
    }
    public Dialog createDialog(){


        dialog = new Dialog(getActivity());
        dialog.setTitle("Заголовок диалога");
        dialog.setContentView(R.layout.eat_list_item_add);
        editTextProductType=(AutoCompleteTextView)dialog.findViewById(R.id.water_list_item_add_AutoComplereRextViewProduct);
        editTextProductVolume=(EditText)dialog.findViewById(R.id.water_list_item_add_editTextVolume);
        btnCreate=(Button)dialog.findViewById(R.id.eat_list_item_add_btnAdd);

        return dialog;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.water_list_btnAdd:
                createDialog();
                dialog.show();
                break;
        }
    }
}
