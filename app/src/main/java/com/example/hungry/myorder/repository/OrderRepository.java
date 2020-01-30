package com.example.hungry.myorder.repository;

import com.example.hungry.myorder.api.Order;
import com.example.hungry.myorder.model.OrderResult;
import com.example.hungry.retrofitsetting.RetrofitClientInstance;

import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderRepository {
    public MutableLiveData<OrderResult> getOrder(String hotel_id,String status,String client_id,String dlBoyId){

        Order hotels = RetrofitClientInstance.getRetrofitInstance().create(Order.class);
        final MutableLiveData<OrderResult> resultMutableLiveData = new MutableLiveData<>();
        Call<OrderResult> call = hotels.getOrders(RetrofitClientInstance.API_KEY,hotel_id,status,client_id,dlBoyId);
        final OrderResult result = new OrderResult();

        call.enqueue(new Callback<OrderResult>() {
            @Override
            public void onResponse(Call<OrderResult> call, Response<OrderResult> response) {
                OrderResult data = response.body();
                resultMutableLiveData.setValue(data);

            }

            @Override
            public void onFailure(Call<OrderResult> call, Throwable t) {
                if(t.getLocalizedMessage().equalsIgnoreCase("Unable to resolve host \"hungryindia.co.in\": No address associated with hostname"))
                { result.setMessage("Please Check Enternet Connection");

                } else {
                    result.setMessage(t.getLocalizedMessage());
                }
                resultMutableLiveData.setValue(result);


            }
        });
        return resultMutableLiveData;

    }

}
