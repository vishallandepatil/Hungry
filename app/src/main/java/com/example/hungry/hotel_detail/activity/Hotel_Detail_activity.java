package com.example.hungry.hotel_detail.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.hungry.R;
import com.example.hungry.hotel_detail.adapter.Hotel_deteail_adapter;

import java.util.ArrayList;
import java.util.Arrays;

public class Hotel_Detail_activity extends AppCompatActivity {
    // ArrayList for person names

    ArrayList personNames = new ArrayList<>(Arrays.asList("Person 1", "Person 2", "Person 3", "Person 4", "Person 5", "Person 6", "Person 7", "Person 8", "Person 9", "Person 10", "Person 11", "Person 12", "Person 13", "Person 14"));
    ArrayList personImages = new ArrayList<>(Arrays.asList(R.drawable.food, R.drawable.food, R.drawable.food, R.drawable.food, R.drawable.food, R.drawable.food, R.drawable.food, R.drawable.food, R.drawable.food, R.drawable.food, R.drawable.food, R.drawable.food, R.drawable.food, R.drawable.food));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel__detail_activity);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_line);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView
        Hotel_deteail_adapter hotel_deteail_adapter = new Hotel_deteail_adapter(Hotel_Detail_activity.this, personNames, personImages);
        recyclerView.setAdapter(hotel_deteail_adapter); // set the Adapter to RecyclerView

    }
}