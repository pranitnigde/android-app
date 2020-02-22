package com.example.lonavalacityguide.hospital.view;

import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.lonavalacityguide.ApplicationClass;
import com.example.lonavalacityguide.R;
import com.example.lonavalacityguide.hospital.adapter.HospitalAdapter;
import com.example.lonavalacityguide.hospital.fragments.HospitalDetailFragment;
import com.example.lonavalacityguide.hospital.fragments.HospitalFragment;
import com.example.lonavalacityguide.hospital.model.hospital;

import java.util.ArrayList;

public class hospitalActivity extends AppCompatActivity implements HospitalAdapter.ItemClicked {

    TextView tvHospitalName,tvContact,tvAddress,tvEmail,tvDescription;
    FragmentManager fragmentManager;
    HospitalFragment hospitalFragment;
    HospitalDetailFragment hospitalDetailFragment;
    Button btnNavigate;
    RatingBar ratingBar;
    ImageSwitcher imageSwitcher;
    ImageButton btnNext,btnPrev;
    ArrayList<hospital> hospitals;
    private static final int[] IMAGES={R.drawable.kumar,R.drawable.picadle,R.drawable.misty,R.drawable.della,R.drawable.fariyas};
    private int mPosition=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.app_name);

        hospitals =new ArrayList<>();




        tvHospitalName=findViewById(R.id.tvHospitalDetailName);
        ratingBar=findViewById(R.id.ratingBar);
        tvAddress=findViewById(R.id.tvHospitalAddress);
        tvContact=findViewById(R.id.tvHospitalContact);
        tvEmail=findViewById(R.id.tvHospitalEmail);
        tvDescription=findViewById(R.id.tvHospitalDescription);
        btnNavigate=findViewById(R.id.btnNavigate);




        fragmentManager=this.getSupportFragmentManager();

        hospitalFragment= (HospitalFragment) fragmentManager.findFragmentById(R.id.hospitalfrag);
        hospitalDetailFragment= (HospitalDetailFragment) fragmentManager.findFragmentById(R.id.detailfrag);



        fragmentManager.beginTransaction()
                .show(hospitalFragment)
                .hide(hospitalDetailFragment)
                .commit();


    }

    @Override
    public void onItemClicked(final int index) {
        tvHospitalName.setText(ApplicationClass.hospitals.get(index).getName());
        ratingBar.setRating(Float.valueOf(ApplicationClass.hospitals.get(index).getRating()));
        tvContact.setText(ApplicationClass.hospitals.get(index).getContact());
        tvAddress.setText(ApplicationClass.hospitals.get(index).getAddress());
        tvEmail.setText(ApplicationClass.hospitals.get(index).getEmail());
        tvDescription.setText(ApplicationClass.hospitals.get(index).getDescription());

        btnNavigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse(ApplicationClass.hospitals.get(index).getMapid())));
            }
        });

        tvContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_DIAL,Uri.parse("tel:"+ApplicationClass.hospitals.get(index).getContact())));
            }
        });

        tvEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String q = ApplicationClass.hospitals.get(index).getEmail();
                Intent intent = new Intent(Intent.ACTION_WEB_SEARCH );
                intent.putExtra(SearchManager.QUERY, q);
                startActivity(intent);
            }
        });







        fragmentManager.beginTransaction()
                .hide(hospitalFragment)
                .show(hospitalDetailFragment)
                .commit();


    }
}
