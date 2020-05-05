package com.example.fitass.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.fitass.MainActivity;
import com.example.fitass.MyService;
import com.example.fitass.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActivityFragment extends Fragment {
    @BindView(R.id.activity_list_btnStop)
    Button btnStop;
    @BindView(R.id.activity_list_btnStart)
    Button btnStart;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.activity_list, null);
        ButterKnife.bind(this,v);
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

}
