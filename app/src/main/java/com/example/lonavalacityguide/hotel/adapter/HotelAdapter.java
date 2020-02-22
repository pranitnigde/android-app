package com.example.lonavalacityguide.hotel.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.lonavalacityguide.R;
import com.example.lonavalacityguide.hotel.model.Hotel;

import java.util.ArrayList;
import java.util.List;

public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.ViewHolder> {

    private ArrayList<Hotel> hotels;
    ItemClicked activity;

    public interface ItemClicked{
        void onItemClicked(int index);
    }

    public HotelAdapter() {
    }

    public HotelAdapter(Context context, ArrayList<Hotel> hotels) {
        activity= (ItemClicked) context;
        this.hotels = hotels;

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
                    activity.onItemClicked(hotels.indexOf(view.getTag()));
                }
            });
        }
    }

    @NonNull
    @Override
    public HotelAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_hotel_item,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HotelAdapter.ViewHolder viewHolder, int i) {
        viewHolder.itemView.setTag(hotels.get(i));
        viewHolder.tvHotelName.setText(hotels.get(i).getName());
        viewHolder.tvHotelImage.setImageResource(hotels.get(i).getImage());

    }

    public void updateList(List<Hotel> hotelList){
        hotels=new ArrayList<>();
        hotels.addAll(hotelList);
        notifyDataSetChanged();
    }



    @Override
    public int getItemCount() {
        return hotels.size();
    }
}
