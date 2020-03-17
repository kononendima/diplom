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
        EatItem phone = phones.get(position);
        holder.imageView.setImageResource(phone.getImage());
        holder.nameView.setText(phone.getName());
        holder.companyView.setText(phone.getCompany());
    }

    @Override
    public int getItemCount() {
        return phones.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView imageView;
        final TextView nameView, companyView;
        ViewHolder(View view){
            super(view);
            imageView = (ImageView)view.findViewById(R.id.image);
            nameView = (TextView) view.findViewById(R.id.name);
            companyView = (TextView) view.findViewById(R.id.company);
        }
    }
}
