package com.example.lonavalacityguide.theatre.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lonavalacityguide.R;
import com.example.lonavalacityguide.shopping.model.Shopping;

import java.util.ArrayList;

public class TheaterAdapter extends RecyclerView.Adapter<TheaterAdapter.ViewHolder> {

    private ArrayList<Shopping> shoppings;
    private ItemClicked activity;


    public interface ItemClicked{
        public void onItemClicked(int index);
    }

    public TheaterAdapter(ArrayList<Shopping> shoppings, ItemClicked activity) {
        this.shoppings = shoppings;
        this.activity = activity;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvShoppingName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvShoppingName=itemView.findViewById(R.id.tvShoppingName);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    activity.onItemClicked(shoppings.indexOf(view.getTag()));
                }
            });
        }
    }

    @NonNull
    @Override
    public TheaterAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_shopping_item   ,viewGroup,false);

        return new TheaterAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TheaterAdapter.ViewHolder viewHolder, int i) {
        viewHolder.itemView.setTag(shoppings.get(i));
        viewHolder.tvShoppingName.setText(shoppings.get(i).getName());

    }

    @Override
    public int getItemCount() {
        return shoppings.size();
    }
}
