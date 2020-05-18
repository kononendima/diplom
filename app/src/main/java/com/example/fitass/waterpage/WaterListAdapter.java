package com.example.fitass.waterpage;

import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.fitass.R;
import com.example.fitass.eatlist.EatItem;
import java.util.List;


public class WaterListAdapter extends RecyclerView.Adapter<WaterListAdapter.ViewHolder> {
    private LayoutInflater inflater;
    private List<WaterItem> waterItems;

    public WaterListAdapter(Context context, List<WaterItem> waterItems) {
        this.waterItems = waterItems;
        this.inflater = LayoutInflater.from(context);
    }
    public void updateList(List<EatItem> eatItems){
        this.waterItems=waterItems;
    }
    @Override
    public WaterListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.water_list_item, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(WaterListAdapter.ViewHolder holder, int position) {
        WaterItem waterItem = waterItems.get(position);
        holder.type.setText(waterItem.getType());
        holder.date.setText(waterItem.getDate());
        holder.volume.setText(waterItem.getVolume());

    }

    @Override
    public int getItemCount() {
        return waterItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView type, date,volume;
        ViewHolder(View view){
            super(view);
            type = (TextView) view.findViewById(R.id.water_list_item_textViewType);
            date = (TextView) view.findViewById(R.id.water_list_item_textViewDate);
            volume = (TextView) view.findViewById(R.id.water_list_item_textViewVolume);
           
        }
    }
}
