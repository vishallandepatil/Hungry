package com.example.hungry.myorder.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.hungry.R;
import com.example.hungry.app.PrefManager;
import com.example.hungry.databinding.FragmentOrderBinding;
import com.example.hungry.myorder.adapter.MyOrderAdapter;
import com.example.hungry.myorder.model.OrderResult;
import com.example.hungry.myorder.viewModel.OrderViewModel;

public class OrderFragment extends Fragment {
    OrderViewModel orderViewModel;

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
        final FragmentOrderBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_order, container, false);
        orderViewModel = ViewModelProviders.of(this).get(OrderViewModel.class);
        ((AppCompatActivity)getActivity()).setSupportActionBar(binding.toolbar);
        orderViewModel.getOrder(null,new PrefManager(getContext()).getUserDetails().getCM_ID()+"");
        orderViewModel.orderResultMutableLiveData.observeForever(new Observer<OrderResult>() {
            @Override
            public void onChanged(OrderResult orderResult) {
                MyOrderAdapter myOrderAdapter = new MyOrderAdapter(orderResult.result, getContext());
                binding.setOrderAdapter(myOrderAdapter);

            }
        });




//

        return binding.getRoot();



    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.hotel_action_menu, menu);
    }

}
