package com.example.lonavalacityguide.hospital.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lonavalacityguide.R;
import com.example.lonavalacityguide.hospital.model.hospital;

import java.util.ArrayList;

public class HospitalAdapter extends RecyclerView.Adapter<HospitalAdapter.ViewHolder> {

    private ArrayList<hospital> hospitals;
    ItemClicked activity;

    public interface ItemClicked{
        void onItemClicked(int index);
    }

    public HospitalAdapter() {
    }

    public HospitalAdapter(Context context, ArrayList<hospital> hospital) {
        activity= (ItemClicked) context;
        this.hospitals = hospital;

    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvHotelName;
        ImageView tvHotelImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvHotelName=itemView.findViewById(R.id.tvHotelName);
            tvHotelImage=itemView.findViewById(R.id.tvHotelImage);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    activity.onItemClicked(hospitals.indexOf(view.getTag()));
                }
            });
        }
    }

    @NonNull
    @Override
    public HospitalAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_hospital_item,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HospitalAdapter.ViewHolder viewHolder, int i) {
        viewHolder.itemView.setTag(hospitals.get(i));
        viewHolder.tvHotelName.setText(hospitals.get(i).getName());
        viewHolder.tvHotelImage.setImageResource(R.drawable.hospitals);

    }





    @Override
    public int getItemCount() {
        return hospitals.size();
    }
}
