package com.example.hungry.myorder.viewModel;

import com.example.hungry.myorder.model.OrderResult;
import com.example.hungry.myorder.repository.OrderRepository;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

public class OrderViewModel extends ViewModel {

    public MutableLiveData<OrderResult> orderResultMutableLiveData = new MutableLiveData<>();

    public void getOrder(String status, String client_id) {
        new OrderRepository().getOrder(null, null, client_id, null).observeForever(new Observer<OrderResult>() {
            @Override
            public void onChanged(OrderResult orderResult) {
                orderResultMutableLiveData.setValue(orderResult);
            }
        });

    }
}

