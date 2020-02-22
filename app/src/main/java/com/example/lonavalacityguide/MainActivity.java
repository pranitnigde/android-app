package com.example.lonavalacityguide;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;

import com.example.lonavalacityguide.daily_planner.MainTaskActivity;
import com.example.lonavalacityguide.hospital.view.hospitalActivity;
import com.example.lonavalacityguide.hotel.view.HotelActivity;
import com.example.lonavalacityguide.place.view.PlacesActivity;
import com.example.lonavalacityguide.shopping.view.ShoppingActivity;

public class MainActivity extends AppCompatActivity  {

    CardView cvHotels,cvPlaces,cvHospitals,cvShopping,cvTheatre;
    FloatingActionButton fabDailyPlanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cvHospitals=findViewById(R.id.cvHospitals);
        cvHotels=findViewById(R.id.cvHotel);
        cvPlaces=findViewById(R.id.cvPlaces);
        cvShopping=findViewById(R.id.cvShopping);
        fabDailyPlanner=findViewById(R.id.btnFav);

        fabDailyPlanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MainTaskActivity.class));
            }
        });




        cvShopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,ShoppingActivity.class));

            }
        });

        cvHotels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this, HotelActivity.class));

            }
        });

        cvPlaces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, PlacesActivity.class));

            }
        });

        cvHospitals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, hospitalActivity.class));

            }
        });

    }
}
