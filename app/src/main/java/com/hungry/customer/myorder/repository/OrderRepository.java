package com.hungry.customer.myorder.repository;

import com.hungry.customer.myorder.api.Order;
import com.hungry.customer.myorder.model.OrderResult;
import com.hungry.customer.retrofitsetting.RetrofitClientInstance;

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
    public MutableLiveData<OrderResult> createtOrder(String CM_MS_ID, String HOT_MS_ID,String DL_AD_MA_ID,String NET_TOTAL,String TAX,String TOTAL,String DISCOUNT, String items,String PAYMENT_STATUS,
                                                     String PAY_METHOD,String discount_code,String deliveryFees,String suggestionforhpotel){
        Order orderRepository = RetrofitClientInstance.getRetrofitInstance().create(Order.class);
        final MutableLiveData<OrderResult> resultMutableLiveData = new MutableLiveData<>();

        Call<OrderResult> call = orderRepository.createOrder(RetrofitClientInstance.API_KEY,CM_MS_ID,HOT_MS_ID,DL_AD_MA_ID,NET_TOTAL,TAX,TOTAL,DISCOUNT,items,PAYMENT_STATUS,PAY_METHOD,discount_code,deliveryFees,suggestionforhpotel);
        call.enqueue(new Callback<OrderResult>() {
            @Override
            public void onResponse(Call<OrderResult> call, Response<OrderResult> response) {
                OrderResult data = response.body();
                resultMutableLiveData.setValue(data);
            }

            @Override
            public void onFailure(Call<OrderResult> call, Throwable t) {
                OrderResult result =new OrderResult();
                if(t.getLocalizedMessage().equalsIgnoreCase("Unable to resolve host \"hungryindia.co.in\": No address associated with hostname"))
                {
                    result.setMessage("Please Check Enternet Connection");

                } else {
                    result.setMessage(t.getLocalizedMessage());
                }
                resultMutableLiveData.setValue(result);
            }
        });
        return resultMutableLiveData;

    }
    public MutableLiveData<OrderResult> UpdateOrder(String orderID, String rating, String feedback){
        Order orderRepository = RetrofitClientInstance.getRetrofitInstance().create(Order.class);
        final MutableLiveData<OrderResult> resultMutableLiveData = new MutableLiveData<>();

        Call<OrderResult> call = orderRepository.updateOrder(RetrofitClientInstance.API_KEY,orderID,rating,feedback);
        call.enqueue(new Callback<OrderResult>() {
            @Override
            public void onResponse(Call<OrderResult> call, Response<OrderResult> response) {
                OrderResult data = response.body();
                resultMutableLiveData.setValue(data);
            }

            @Override
            public void onFailure(Call<OrderResult> call, Throwable t) {
                OrderResult result =new OrderResult();
                if(t.getLocalizedMessage().equalsIgnoreCase("Unable to resolve host \"hungryindia.co.in\": No address associated with hostname"))
                {
                    result.setMessage("Please Check Enternet Connection");

                } else {
                    result.setMessage(t.getLocalizedMessage());
                }
                resultMutableLiveData.setValue(result);
            }
        });
        return resultMutableLiveData;

    }

}



