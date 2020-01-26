package com.example.hungry.myOrder.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hungry.R;
import com.example.hungry.myOrder.adapter.MyOrderAdapter;
import com.example.hungry.myOrder.model.OrderListModel;

import java.util.ArrayList;


public class OrderFragment extends Fragment {

    ArrayList<OrderListModel> orderList = new ArrayList<>();

    public OrderFragment() {
        // Required empty public constructor
    }

    public static OrderFragment newInstance(String param1, String param2) {
        OrderFragment fragment = new OrderFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_order, container, false);
        RecyclerView rv_order = (RecyclerView) rootView.findViewById(R.id.rv_order);

        Toolbar toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

 // My Order
        orderList.add(new OrderListModel(
                "Hotel Prasad",
                "Indian",
                "Aurangabad Road",
                "11: 55 AM to 11:00 PM"));
        orderList.add(new OrderListModel(
                "Hotel Prasad",
                "America",
                "Aurangabad Road",
                "11: 55 AM to 11:00 PM"));


        MyOrderAdapter myOrderAdapter = new MyOrderAdapter(orderList, getContext());
        rv_order.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_order.setAdapter(myOrderAdapter);

        return rootView;



    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.hotel_action_menu, menu);
    }

}
