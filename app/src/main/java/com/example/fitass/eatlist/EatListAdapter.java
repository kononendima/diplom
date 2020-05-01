package com.example.fitass.eatlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.fitass.R;

import java.util.List;

public class EatListAdapter extends RecyclerView.Adapter<EatListAdapter.ViewHolder>{
    private LayoutInflater inflater;
    private List<EatItem> eatItems;

    public EatListAdapter(Context context, List<EatItem> eatItems) {
        this.eatItems = eatItems;
        this.inflater = LayoutInflater.from(context);
    }
    public void updateList(List<EatItem> eatItems){
        this.eatItems=eatItems;
    }
    @Override
    public EatListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.eat_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EatListAdapter.ViewHolder holder, int position) {
        EatItem eatItem = eatItems.get(position);
        holder.eat.setText(eatItem.getEat());
        holder.date.setText(eatItem.getDate());
        holder.calorie.setText(eatItem.getCalorie());
    }

    @Override
    public int getItemCount() {
        return eatItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        final TextView eat, date,calorie;
        ViewHolder(View view){
            super(view);
            eat = (TextView) view.findViewById(R.id.eat_list_item_textViewType);
            date = (TextView) view.findViewById(R.id.eat_list_item_textViewDate);
            calorie = (TextView) view.findViewById(R.id.eat_list_item_textViewcalorie);
        }
    }
}
