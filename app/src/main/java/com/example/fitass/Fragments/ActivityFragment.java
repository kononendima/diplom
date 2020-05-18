package com.example.fitass.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.example.fitass.activitypage.ActivityListAdapter;
import com.example.fitass.activitypage.ActivityListManager;
import com.example.fitass.activitypage.Step;
import com.example.fitass.service.MyService;
import com.example.fitass.R;
import java.util.ArrayList;


import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.Context.MODE_PRIVATE;

public class ActivityFragment extends Fragment {

    @BindView(R.id.water_list_recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipeRefreshLayoutEat)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.activity_list_switchPedometer)
    Switch switchPedometer;
    public ArrayList<Step> stepList;
    ActivityListManager activityListManager;
    ActivityListAdapter activityListAdapter;
    SharedPreferences sPref;
    boolean flag;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.activity_list, null);
        ButterKnife.bind(this,v);
        activityListManager=new ActivityListManager(getActivity());
        activityListAdapter = new ActivityListAdapter(getActivity(), activityListManager.getStepList());
        recyclerView.setAdapter(activityListAdapter);
        checkSwitcherCondition();
        switchPedometer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                    if(switchPedometer.isChecked()){
                    startService();
                    saveToMemorySwitcherCondition(true);
                    }else{
                        saveToMemorySwitcherCondition(false);
                        stopService();
                    }
                }
            }
        );

        refreshRecycler(swipeRefreshLayout);
        return v;
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
            switchPedometer.setChecked(true);
        }
    }
    public void saveToMemorySwitcherCondition(Boolean condition){

        sPref = getActivity().getSharedPreferences("Switcher",MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString("condition",condition.toString());
        ed.commit();

    }
}
