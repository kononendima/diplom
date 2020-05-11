package com.example.fitass.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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

public class ActivityFragment extends Fragment {
    @BindView(R.id.activity_list_btnStop)
    Button btnStop;
    @BindView(R.id.activity_list_btnStart)
    Button btnStart;
    @BindView(R.id.activity_list_recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    public ArrayList<Step> stepList;
    ActivityListManager activityListManager;
    ActivityListAdapter activityListAdapter;
    boolean flag;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.activity_list, null);
        ButterKnife.bind(this,v);
        activityListManager=new ActivityListManager(getActivity());

        activityListAdapter = new ActivityListAdapter(getActivity(), activityListManager.getStepList());
        // устанавливаем для списка адаптер
        recyclerView.setAdapter(activityListAdapter);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService();
            }
        });
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService();
            }
        });

        refreshRecycler(swipeRefreshLayout);
        return v;
    }
    public void stopService() {
        getActivity().stopService(new Intent(getActivity(),MyService.class));
        flag=false;
    }
    public void startService() {
        flag=true;
       // updateRecycler();
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
}
