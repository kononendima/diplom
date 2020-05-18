package com.example.fitass.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.fitass.R;
import com.example.fitass.eatlist.EatItemManager;
import com.example.fitass.eatlist.EatListAdapter;

import butterknife.ButterKnife;

public class WaterFragment extends Fragment {

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
    }
}