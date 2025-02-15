package com.example.fitass.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.fitAss.databinding.ActivityEatListBinding;
import com.example.fitAss.databinding.ActivityListBinding;
import com.example.fitass.activitypage.ActivityListAdapter;
import com.example.fitass.activitypage.ActivityListManager;
import com.example.fitass.activitypage.Step;
import com.example.fitass.service.MyService;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class ActivityFragment extends Fragment {
    private ActivityListBinding binding;
    private ArrayList<Step> stepList;
    private ActivityListManager activityListManager;
    private ActivityListAdapter activityListAdapter;
    private SharedPreferences sPref;
    boolean flag;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = ActivityListBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        activityListManager=new ActivityListManager(getActivity());
        activityListAdapter = new ActivityListAdapter(getActivity(), activityListManager.getStepList());
        binding.waterListRecyclerView.setAdapter(activityListAdapter);
        checkSwitcherCondition();
        binding.activityListSwitchPedometer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                    if( binding.activityListSwitchPedometer.isChecked()){
                    startService();
                    saveToMemorySwitcherCondition(true);
                    }else{
                        saveToMemorySwitcherCondition(false);
                        stopService();
                    }
                }
            }
        );

        refreshRecycler(binding.swipeRefreshLayoutEat);
        return view;
    }
    public void stopService() {
        getActivity().stopService(new Intent(getActivity(),MyService.class));
    }
    public void startService() {
        Intent serviceIntent = new Intent(getActivity(), MyService.class);
        serviceIntent.putExtra("inputExtra", "Foreground Service Example in Android");
        ContextCompat.startForegroundService(getActivity(), serviceIntent);
    }

    public void refreshRecycler(final SwipeRefreshLayout swipeRefreshLayout){
       swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                activityListManager = new ActivityListManager(getActivity());
                stepList=activityListManager.getStepList();
                activityListAdapter.updateList(stepList);
                activityListAdapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }
    public void checkSwitcherCondition(){

        sPref = getActivity().getSharedPreferences("Switcher", Context.MODE_PRIVATE);
        boolean condition =Boolean.parseBoolean(sPref.getString("condition","0"));
        if(condition==true){
            binding.activityListSwitchPedometer.setChecked(true);
        }
    }
    public void saveToMemorySwitcherCondition(Boolean condition){
        sPref = getActivity().getSharedPreferences("Switcher",MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString("condition",condition.toString());
        ed.commit();
    }
}
