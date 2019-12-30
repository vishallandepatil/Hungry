package com.example.hungry.OrderSummary.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.hungry.R;
import com.example.hungry.OrderSummary.adapter.OrdeSummaryAdapter;
import com.example.hungry.OrderSummary.model.Order_summary_Model;

import java.util.ArrayList;

public class OrderSummary extends AppCompatActivity {


    ArrayList<Order_summary_Model> ordersummary = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_summary);

        RecyclerView rv_line = (RecyclerView)findViewById(R.id.summary_rv);


        ordersummary.add(new Order_summary_Model(
                "Palak Paneer Bhurji",
                "Hotel Prasad"));
        ordersummary.add(new Order_summary_Model(
                "Palak Paneer Bhurji",
                "Hotel Prasad"));



        OrdeSummaryAdapter ordeSummaryAdapter = new OrdeSummaryAdapter(ordersummary, this);
        rv_line.setLayoutManager(new LinearLayoutManager(this));
        rv_line.setAdapter(ordeSummaryAdapter);


    }
}
