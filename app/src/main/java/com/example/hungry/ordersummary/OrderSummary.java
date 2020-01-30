package com.example.hungry.ordersummary;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hungry.HomePage;
import com.example.hungry.ordersummary.adapter.OrderSummaryAdapter;
import com.example.hungry.ordersummary.viewmodel.OrderSummaryViewModel;
import com.example.hungry.R;
import com.example.hungry.databinding.FragmentOrderSummaryBinding;

public class OrderSummary extends Fragment {
    private OrderSummaryViewModel orderSummaryViewModel;
    private FragmentOrderSummaryBinding binding;
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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_order_summary, container, false);
        orderSummaryViewModel= ViewModelProviders.of(this).get(OrderSummaryViewModel.class);
        //binding.setOrderSummeryViewModel(orderSummaryViewModel);
        binding.setLifecycleOwner(this);
        OrderSummaryAdapter ordeSummaryAdapter = new OrderSummaryAdapter(((HomePage)getActivity()).cart, getActivity());
        binding.setAdapter(ordeSummaryAdapter);
        return binding.getRoot();
    }





}
