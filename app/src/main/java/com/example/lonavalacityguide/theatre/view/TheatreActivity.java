package com.example.lonavalacityguide.theatre.view;

import android.app.SearchManager;
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
import com.example.lonavalacityguide.theatre.adapter.TheaterAdapter;
import com.example.lonavalacityguide.theatre.fragment.TheatreDetailFragment;
import com.example.lonavalacityguide.theatre.fragment.TheatreFrag;
import com.example.lonavalacityguide.theatre.model.Theatre;

import java.util.ArrayList;

public class TheatreActivity extends AppCompatActivity implements TheaterAdapter.ItemClicked {
    TextView tvShoppingDetailName,tvShoppingRating,tvShoppingAddress,tvShoppingContact,tvShoppingEmail,tvShoppingDescription;

    TheatreDetailFragment theatreDetailFragment;
    TheatreFrag theatreFrag;
    FragmentManager fragmentManager;
    ArrayList<Theatre> theatres;
    Button btnNavigate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theatre);

        theatres=new ArrayList<>();
        fragmentManager=getSupportFragmentManager();
        theatreFrag= (TheatreFrag) fragmentManager.findFragmentById(R.id.shoppingFragment);
        theatreDetailFragment= (TheatreDetailFragment) fragmentManager.findFragmentById(R.id.theatreDetailFragment);

        tvShoppingDetailName=findViewById(R.id.tvShoppingDetailName);
        tvShoppingRating=findViewById(R.id.tvShoppingRating);
        tvShoppingAddress=findViewById(R.id.tvShoppingAddress);
        tvShoppingContact=findViewById(R.id.tvShoppingContact);
        tvShoppingEmail=findViewById(R.id.tvShoppingEmail);
        tvShoppingDescription=findViewById(R.id.tvShoppingDescription);
        btnNavigate=findViewById(R.id.btnNavigate);

        fragmentManager.beginTransaction()
                .show(theatreFrag)
                .hide(theatreDetailFragment)
                .commit();

    }

    @Override
    public void onItemClicked(final int index) {
        tvShoppingDetailName.setText(ApplicationClass.shoppings.get(index).getName());
        tvShoppingRating.setText("Rating : "+ ApplicationClass.shoppings.get(index).getRating());
        tvShoppingAddress.setText(ApplicationClass.shoppings.get(index).getAddress());
        tvShoppingContact.setText(ApplicationClass.shoppings.get(index).getContact());
        tvShoppingEmail.setText(ApplicationClass.shoppings.get(index).getEmail());
        tvShoppingDescription.setText(ApplicationClass.shoppings.get(index).getDescription());

        btnNavigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(ApplicationClass.shoppings.get(index).getMapid())));
            }
        });

        tvShoppingContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_DIAL,Uri.parse("tel:"+ApplicationClass.shoppings.get(index).getContact())));
            }
        });

        tvShoppingEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String q = ApplicationClass.shoppings.get(index).getEmail();
                Intent intent = new Intent(Intent.ACTION_WEB_SEARCH );
                intent.putExtra(SearchManager.QUERY, q);
                startActivity(intent);
            }
        });



        fragmentManager.beginTransaction()
                .show(theatreDetailFragment)
                .hide(theatreFrag)
                .commit();
    }
}
