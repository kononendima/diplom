package com.example.fitass.activitypage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.fitass.R;
import com.example.fitass.UserManager;

import java.util.List;

public class ActivityListAdapter extends RecyclerView.Adapter<ActivityListAdapter.ViewHolder> {
    private LayoutInflater inflater;
    private List<Step> stepList;

    int userHeight,userWeight;

    public ActivityListAdapter(Context context, List<Step> stepList) {
        this.stepList = stepList;

        this.inflater = LayoutInflater.from(context);
        UserManager userManager=new UserManager(context);
        userHeight=userManager.getCurrentUserHeightFromMemory();
        userWeight=userManager.getCurrentUserWeight();

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
        Step stepInDay = stepList.get(position);
        holder.steps.setText(stepInDay.getSteps());
        holder.date.setText(stepInDay.getDate());
        if(calcDistance(stepInDay).length()>3)
            holder.distance.setText(calcDistance(stepInDay).substring(0,4).concat(" км"));
        else
            holder.distance.setText(calcDistance(stepInDay).substring(0,3).concat(" км"));
        if(calcDistance(stepInDay).length()>3)
            holder.calorie.setText(String.valueOf(Double.parseDouble(calcDistance(stepInDay))*0.5*userWeight).substring(0,4));
        else
            holder.calorie.setText(String.valueOf(Double.parseDouble(calcDistance(stepInDay))*0.5*userWeight).substring(0,3));
    }
    public String calcDistance(Step stepInDay){
        String distance =String.valueOf(Double.parseDouble(String.valueOf((((userHeight/ 4 + 35 )) * Integer.parseInt(stepInDay.getSteps()))))/100000);
        return distance;
    }



    @Override
    public int getItemCount() {
        return stepList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        final TextView steps, date,distance,calorie ;
        ViewHolder(View view){
            super(view);

            steps = (TextView) view.findViewById(R.id.activity_list_item_textViewSteps);
            date = (TextView) view.findViewById(R.id.activity_list_item_textViewDate);
            distance=(TextView)view.findViewById(R.id.activity_list_item_textViewDistance);
            calorie=(TextView)view.findViewById(R.id.activity_list_item_textViewCalorie);
        }
    }
}


