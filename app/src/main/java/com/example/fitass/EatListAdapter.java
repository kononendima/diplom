package com.example.fitass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EatListAdapter extends RecyclerView.Adapter<EatListAdapter.ViewHolder>{
    private LayoutInflater inflater;
    private List<EatItem> eatItems;

    EatListAdapter(Context context, List<EatItem> eatItems) {
        this.eatItems = eatItems;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public EatListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.eat_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EatListAdapter.ViewHolder holder, int position) {
        EatItem eatItem = eatItems.get(position);
        holder.type.setText(eatItem.getEat());
        holder.date.setText(eatItem.getDate());
        holder.calory.setText(eatItem.getCalory());
    }

    @Override
    public int getItemCount() {
        return eatItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        final TextView type, date,calory;
        ViewHolder(View view){
            super(view);
            type = (TextView) view.findViewById(R.id.eat_list_item_textViewType);
            date = (TextView) view.findViewById(R.id.eat_list_item_textViewDate);
            calory = (TextView) view.findViewById(R.id.eat_list_item_textViewCalory);
        }
    }
}
