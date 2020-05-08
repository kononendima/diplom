package com.example.fitass.Fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import com.example.fitass.eatlist.Product;
import com.example.fitass.R;
import com.example.fitass.eatlist.EatItem;
import com.example.fitass.eatlist.EatItemManager;
import com.example.fitass.eatlist.EatListAdapter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class EatFragment extends Fragment implements View.OnClickListener {
    @BindView(R.id.activity_eat_list_btnAdd)
    Button btnAdd;
    @BindView(R.id.activity_eat_list_recyclerView)
    RecyclerView recyclerView;
    EatItemManager eatItemManager;

    Dialog dialog;
    List<EatItem> eatItems;
    AutoCompleteTextView editTextProductTitle;
    EatListAdapter adapterEat;
    Button btnCreate;
    EditText editTextWeight;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        eatItemManager = new EatItemManager(getActivity());
        eatItems = eatItemManager.getEatItemsList();
        View v=inflater.inflate(R.layout.activity_eat_list, null);
        ButterKnife.bind(this,v);

        btnAdd.setOnClickListener(this);

        // создаем адаптер
         adapterEat = new EatListAdapter(getActivity(), eatItems);
        // устанавливаем для списка адаптер
        recyclerView.setAdapter(adapterEat);

        return v;
    }

    public Dialog createDialog(){


        dialog = new Dialog(getActivity());
        dialog.setTitle("Заголовок диалога");
        dialog.setContentView(R.layout.eat_list_item_add);// ссылка на разметку
        editTextProductTitle=(AutoCompleteTextView)dialog.findViewById(R.id.calorie_list_item_add_AutoComplereRextViewProduct);
        editTextWeight=(EditText)dialog.findViewById(R.id.calorie_list_item_add_editTextWeight);
        btnCreate=(Button)dialog.findViewById(R.id.eat_list_item_add_btnAdd);
        btnCreate.setOnClickListener(this);
        // адаптер
        editTextProductTitle.setAdapter(new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_dropdown_item_1line, getProductTitles(eatItemManager.getProductList())));

        return dialog;
    }
    public static ArrayList<String> getProductTitles(List<Product> productList){
        ArrayList<String> titles=new ArrayList<>();
        int index=0;
        for(Product p:productList) {
            titles.add(productList.get(index).getTitle());
            index++;
        }
        return titles;
    }
    public void calcCalorie(){
       Date date=new Date();
        String stringDate=date.toString();
        String stringeditTextProductTitle=editTextProductTitle.getText().toString();
        EatItemManager eatItemManager=new EatItemManager(getActivity());
        int calorie=Integer.parseInt( eatItemManager.getCalorieProduct(stringeditTextProductTitle));
        int result=calorie*(Integer.parseInt(editTextWeight.getText().toString())/100);

        eatItemManager.addEatItem(new EatItem(stringeditTextProductTitle,stringDate,"Калорий "+result));
    }



    public void onClick(View v)
    {

        switch (v.getId()) {
            case R.id.activity_eat_list_btnAdd:
                createDialog();
                dialog.show();
                break;
            case R.id.eat_list_item_add_btnAdd:
                calcCalorie();
                dialog.dismiss();
                eatItems=eatItemManager.getEatItemsList();
                adapterEat.updateList(eatItems);
                adapterEat.notifyDataSetChanged();
                break;
        }
    }
}

