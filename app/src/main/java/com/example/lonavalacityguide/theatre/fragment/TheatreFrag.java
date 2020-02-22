package com.example.lonavalacityguide.theatre.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lonavalacityguide.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TheatreFrag extends Fragment {


    public TheatreFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_theatre, container, false);
    }

}
