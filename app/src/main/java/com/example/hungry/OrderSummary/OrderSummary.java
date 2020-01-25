package com.example.hungry.OrderSummary;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hungry.OrderSummary.adapter.OrdeSummaryAdapter;
import com.example.hungry.OrderSummary.model.Order_summary_Model;
import com.example.hungry.R;

import java.util.ArrayList;

public class OrderSummary extends Fragment {
    ArrayList<Order_summary_Model> ordersummary = new ArrayList<>();

    public OrderSummary() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static OrderSummary newInstance(String param1, String param2) {
        OrderSummary fragment = new OrderSummary();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =inflater.inflate(R.layout.fragment_order_summary, container, false);
        RecyclerView rv_line = (RecyclerView)rootView.findViewById(R.id.summary_rv);


        ordersummary.add(new Order_summary_Model(
                "Palak Paneer Bhurji",
                "Hotel Prasad"));
        ordersummary.add(new Order_summary_Model(
                "Palak Paneer Bhurji",
                "Hotel Prasad"));



        OrdeSummaryAdapter ordeSummaryAdapter = new OrdeSummaryAdapter(ordersummary, getActivity());
        rv_line.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_line.setAdapter(ordeSummaryAdapter);
        return rootView;
    }





}
