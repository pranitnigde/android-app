package com.example.lonavalacityguide.hotel.view;

import android.app.ActionBar;
import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.*;
import com.example.lonavalacityguide.ApplicationClass;
import com.example.lonavalacityguide.R;
import com.example.lonavalacityguide.hotel.adapter.HotelAdapter;
import com.example.lonavalacityguide.hotel.fragments.HotelDetailFragment;
import com.example.lonavalacityguide.hotel.fragments.HotelFragment;
import com.example.lonavalacityguide.hotel.model.Hotel;

import java.util.ArrayList;

public class HotelActivity extends AppCompatActivity implements HotelAdapter.ItemClicked {

    TextView tvHotelName,tvContact,tvAddress,tvEmail,tvDescription;
    FragmentManager fragmentManager;
    HotelFragment hotelFragment;
    HotelDetailFragment hotelDetailFragment;
    Button btnNavigate;
    RatingBar ratingBar;
    ImageSwitcher imageSwitcher;
    ImageButton btnNext,btnPrev;
    ArrayList<Hotel> hotels;
    private static final int[] IMAGES={R.drawable.kumar,R.drawable.picadle,R.drawable.misty,R.drawable.della,R.drawable.fariyas};
    private int mPosition=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.app_name);

        hotels=new ArrayList<>();




        tvHotelName=findViewById(R.id.tvHotelDetailName);
        ratingBar=findViewById(R.id.ratingBar);
        tvAddress=findViewById(R.id.tvHotelAddress);
        tvContact=findViewById(R.id.tvHotelContact);
        tvEmail=findViewById(R.id.tvHotelEmail);
        tvDescription=findViewById(R.id.tvHotelDescription);
        btnNavigate=findViewById(R.id.btnNavigate);
        imageSwitcher=findViewById(R.id.imageSwitcher);
        btnNext=findViewById(R.id.btnNext);
        btnPrev=findViewById(R.id.btnPrev);



        fragmentManager=this.getSupportFragmentManager();

        hotelFragment= (HotelFragment) fragmentManager.findFragmentById(R.id.hotelfrag);
        hotelDetailFragment= (HotelDetailFragment) fragmentManager.findFragmentById(R.id.detailfrag);



        fragmentManager.beginTransaction()
                .show(hotelFragment)
                .hide(hotelDetailFragment)
                .commit();





    }

    @Override
    public void onItemClicked(final int index) {
        tvHotelName.setText(ApplicationClass.hotels.get(index).getName());
        ratingBar.setRating(Float.valueOf(ApplicationClass.hotels.get(index).getRating()));
        tvContact.setText(ApplicationClass.hotels.get(index).getContact());
        tvAddress.setText(ApplicationClass.hotels.get(index).getAddress());
        tvEmail.setText(ApplicationClass.hotels.get(index).getEmail());
        tvDescription.setText(ApplicationClass.hotels.get(index).getDescription());

        btnNavigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse(ApplicationClass.hotels.get(index).getMapid())));
            }
        });

        tvContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_DIAL,Uri.parse("tel:"+ApplicationClass.hotels.get(index).getContact())));
            }
        });

        tvEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String q = ApplicationClass.hotels.get(index).getEmail();
                Intent intent = new Intent(Intent.ACTION_WEB_SEARCH );
                intent.putExtra(SearchManager.QUERY, q);
                startActivity(intent);
            }
        });

        imageSwitcher.setBackgroundResource(IMAGES[mPosition]);
        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView=new ImageView(getApplicationContext());
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                imageView.setLayoutParams(new ImageSwitcher.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT,ActionBar.LayoutParams.WRAP_CONTENT));
                return imageView;
            }
        });

        imageSwitcher.setInAnimation(this, android.R.anim.slide_in_left);
        imageSwitcher.setOutAnimation(this, android.R.anim.slide_out_right);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mPosition<IMAGES.length-1){
                    mPosition+=1;
                    imageSwitcher.setBackgroundResource(IMAGES[mPosition]);

                }
                else{
                    Toast.makeText(HotelActivity.this,"No more Images",Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mPosition>0){
                    mPosition-=1;
                    imageSwitcher.setBackgroundResource(IMAGES[mPosition]);

                }  else{
                    Toast.makeText(HotelActivity.this,"No more Images",Toast.LENGTH_SHORT).show();
                }

            }
        });





        fragmentManager.beginTransaction()
                .hide(hotelFragment)
                .show(hotelDetailFragment)
                .commit();




    }
}
