package com.example.lonavalacityguide.place.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lonavalacityguide.R;
import com.example.lonavalacityguide.place.model.Place;

import java.util.ArrayList;

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.ViewHolder> {

    private ArrayList<Place> places;
    private ItemClicked activity;


    public interface ItemClicked{
        public void onItemClicked(int index);
    }


    public PlaceAdapter(ArrayList<Place> places, ItemClicked activity) {
        this.places = places;
        this.activity = activity;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvPlacewName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvPlacewName=itemView.findViewById(R.id.tvPlaceName);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    activity.onItemClicked(places.indexOf(view.getTag()));
                }
            });
        }
    }

    @NonNull
    @Override
    public PlaceAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_place_item,viewGroup,false);

        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull PlaceAdapter.ViewHolder viewHolder, int i) {

        viewHolder.itemView.setTag(places.get(i));
        viewHolder.tvPlacewName.setText(places.get(i).getName());

    }

    @Override
    public int getItemCount() {
        return places.size();
    }
}
