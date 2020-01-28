package com.example.hungry.myOrder.api;

import com.example.hungry.myOrder.model.OrderResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Order {
    @GET("/index.php/api//Order/orders")
    Call<OrderResult> getOrders(@Query("api_key") String api_key, @Query("HOT_MS_ID") String HOT_MS_ID, @Query("OR_STATUS") String OR_STATUS, @Query("CM_MS_ID") String CM_MS_ID, @Query("DL_BO_MA_ID") String DL_BO_MA_ID);

}
