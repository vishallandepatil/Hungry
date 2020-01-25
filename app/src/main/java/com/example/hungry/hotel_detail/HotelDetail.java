package com.example.hungry.hotel_detail;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hungry.R;
import com.example.hungry.hotel_detail.adapter.Hotel_deteail_adapter;

import java.util.ArrayList;
import java.util.Arrays;


public class HotelDetail extends Fragment {
    ArrayList personNames = new ArrayList<>(Arrays.asList("Person 1", "Person 2", "Person 3", "Person 4", "Person 5", "Person 6", "Person 7", "Person 8", "Person 9", "Person 10", "Person 11", "Person 12", "Person 13", "Person 14"));
    ArrayList personImages = new ArrayList<>(Arrays.asList(R.drawable.food, R.drawable.food, R.drawable.food, R.drawable.food, R.drawable.food, R.drawable.food, R.drawable.food, R.drawable.food, R.drawable.food, R.drawable.food, R.drawable.food, R.drawable.food, R.drawable.food, R.drawable.food));

    public HotelDetail() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview= inflater.inflate(R.layout.fragment_hotel_detail, container, false);
        RecyclerView recyclerView = (RecyclerView) rootview.findViewById(R.id.rv_line);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView
        Hotel_deteail_adapter hotel_deteail_adapter = new Hotel_deteail_adapter(getActivity(), personNames, personImages);
        recyclerView.setAdapter(hotel_deteail_adapter); // set the Adapter to RecyclerView
        return  rootview;
    }




}