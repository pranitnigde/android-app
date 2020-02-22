package com.example.lonavalacityguide.place.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.lonavalacityguide.ApplicationClass;
import com.example.lonavalacityguide.R;
import com.example.lonavalacityguide.place.adapter.PlaceAdapter;
import com.example.lonavalacityguide.place.fragment.PlaceDetailFragment;
import com.example.lonavalacityguide.place.fragment.PlaceFragment;
import com.example.lonavalacityguide.place.model.Place;

import java.util.ArrayList;

public class PlacesActivity extends AppCompatActivity implements PlaceAdapter.ItemClicked {

    TextView tvPlaceDetailName,tvPlaceRating,tvPlaceAddress,tvPlaceContact,tvPlaceEmail,tvPlaceDescription;

    PlaceDetailFragment placeDetailFragment;
    PlaceFragment placeFragment;
    FragmentManager fragmentManager;
    ArrayList<Place> places;
    Button btnNavigate;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places);

        places=new ArrayList<>();



        tvPlaceDetailName=findViewById(R.id.tvPlaceDetailName);
        tvPlaceRating=findViewById(R.id.tvPlaceRating);
        tvPlaceAddress=findViewById(R.id.tvPlaceAddress);
        tvPlaceContact=findViewById(R.id.tvPlaceContact);
        tvPlaceEmail=findViewById(R.id.tvPlaceEmail);
        tvPlaceDescription=findViewById(R.id.tvPlaceDescription);
        btnNavigate=findViewById(R.id.btnNavigate);

        fragmentManager=this.getSupportFragmentManager();

        placeDetailFragment= (PlaceDetailFragment) fragmentManager.findFragmentById(R.id.detailplacefrag);
        placeFragment= (PlaceFragment) fragmentManager.findFragmentById(R.id.placefrag);

        fragmentManager.beginTransaction()
                .show(placeFragment)
                .hide(placeDetailFragment)
                .commit();




    }

    @Override
    public void onItemClicked(final int index) {

        tvPlaceDetailName.setText(ApplicationClass.places.get(index).getName());
        tvPlaceRating.setText("Rating : "+ ApplicationClass.places.get(index).getRating());
        tvPlaceAddress.setText(ApplicationClass.places.get(index).getAddress());
        tvPlaceContact.setText(ApplicationClass.places.get(index).getContact());
        tvPlaceEmail.setText(ApplicationClass.places.get(index).getEmail());
        tvPlaceDescription.setText(ApplicationClass.places.get(index).getDescription());

        btnNavigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(ApplicationClass.places.get(index).getMapid())));
            }
        });



        fragmentManager.beginTransaction()
                .show(placeDetailFragment)
                .hide(placeFragment)
                .addToBackStack(null)
                .commit();

    }
}
