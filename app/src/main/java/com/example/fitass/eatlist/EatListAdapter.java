package com.example.fitass.eatlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.example.fitAss.R;

import java.util.List;

public class EatListAdapter extends RecyclerView.Adapter<EatListAdapter.ViewHolder>{
    private LayoutInflater inflater;
    private List<EatItem> eatItems;
    private EatItemManager eatItemManager;
    public EatListAdapter(Context context, List<EatItem> eatItems) {
        this.eatItems = eatItems;
        this.inflater = LayoutInflater.from(context);
        eatItemManager=new EatItemManager(context);
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
    public void onBindViewHolder(final EatListAdapter.ViewHolder holder, final int position) {

        final EatItem eatItem = eatItems.get(position);
        holder.eat.setText(eatItem.getEat());

        holder.date.setText(eatItem.getDate());
        holder.calorie.setText(eatItem.getCalorie());
        holder.weight.setText("Масса "+eatItem.getWeight()+" грамм");
        holder.imageView.setOnClickListener(v -> {
            eatItemManager.deleteEatItem(eatItem.getUuid());
            eatItems.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, eatItems.size());
        });
    }

    @Override
    public int getItemCount() {
        return eatItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView eat, date,calorie,weight;
        ImageView imageView;

        ViewHolder(View view){
            super(view);
            eat = (TextView) view.findViewById(R.id.eatListItemType);
            date = (TextView) view.findViewById(R.id.eat_list_item_textViewDate);
            calorie = (TextView) view.findViewById(R.id.eat_list_item_textViewCalorie);
            weight = (TextView) view.findViewById(R.id.eatListItemTextViewWeight);
            imageView=(ImageView) view.findViewById(R.id.eatListItemBtnCross);
        }
    }
}