package com.example.fitass.activitypage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitass.R;
import com.example.fitass.eatlist.EatItem;
import com.example.fitass.eatlist.EatListAdapter;

import java.util.List;

public class ActivityListAdapter extends RecyclerView.Adapter<ActivityListAdapter.ViewHolder> {
    private LayoutInflater inflater;
    private List<Step> stepList;

    public ActivityListAdapter(Context context, List<Step> stepList) {
        this.stepList = stepList;
        this.inflater = LayoutInflater.from(context);
    }
    public void updateList(List<Step> stepList){
        this.stepList=stepList;

    }
    @Override
    public ActivityListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.activity_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Step stepItem = stepList.get(position);
        holder.steps.setText(stepItem.getSteps());
        holder.date.setText(stepItem.getDate());

    }



    @Override
    public int getItemCount() {
        return stepList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        final TextView steps, date;
        ViewHolder(View view){
            super(view);
            steps = (TextView) view.findViewById(R.id.activity_list_item_textViewSteps);
            date = (TextView) view.findViewById(R.id.activity_list_item_textViewDate);

        }
    }
}


