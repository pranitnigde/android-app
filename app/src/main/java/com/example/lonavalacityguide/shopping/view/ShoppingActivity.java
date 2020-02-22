package com.example.lonavalacityguide.shopping.view;

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
import com.example.lonavalacityguide.shopping.adapter.ShoppingAdapter;
import com.example.lonavalacityguide.shopping.fragment.ShoppingDetailFragment;
import com.example.lonavalacityguide.shopping.fragment.ShoppingFragment;
import com.example.lonavalacityguide.shopping.model.Shopping;

import java.util.ArrayList;

public class ShoppingActivity extends AppCompatActivity implements ShoppingAdapter.ItemClicked {
    TextView tvShoppingDetailName,tvShoppingRating,tvShoppingAddress,tvShoppingContact,tvShoppingEmail,tvShoppingDescription;

    ShoppingDetailFragment shoppingDetailFragment;
    ShoppingFragment shoppingFragment;
    FragmentManager fragmentManager;
    ArrayList<Shopping> shoppings;
    Button btnNavigate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);

        shoppings=new ArrayList<>();
        fragmentManager=getSupportFragmentManager();
        shoppingFragment= (ShoppingFragment) fragmentManager.findFragmentById(R.id.shoppingFragment);
        shoppingDetailFragment= (ShoppingDetailFragment) fragmentManager.findFragmentById(R.id.shoppingDetailFragment);

        tvShoppingDetailName=findViewById(R.id.tvShoppingDetailName);
        tvShoppingRating=findViewById(R.id.tvShoppingRating);
        tvShoppingAddress=findViewById(R.id.tvShoppingAddress);
        tvShoppingContact=findViewById(R.id.tvShoppingContact);
        tvShoppingEmail=findViewById(R.id.tvShoppingEmail);
        tvShoppingDescription=findViewById(R.id.tvShoppingDescription);
        btnNavigate=findViewById(R.id.btnNavigate);

        fragmentManager.beginTransaction()
                .show(shoppingFragment)
                .hide(shoppingDetailFragment)
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
                .show(shoppingDetailFragment)
                .hide(shoppingFragment)
                .commit();


    }
}
