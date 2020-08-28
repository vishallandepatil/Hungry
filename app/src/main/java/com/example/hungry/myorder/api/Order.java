package com.example.hungry.myorder.api;

import com.example.hungry.login.model.ResultVerifyOTP;
import com.example.hungry.myorder.model.OrderResult;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface Order {
    @GET("/index.php/api//Order/orders")
    Call<OrderResult> getOrders(@Query("api_key") String api_key, @Query("HOT_MS_ID") String HOT_MS_ID, @Query("OR_STATUS") String OR_STATUS, @Query("CM_MS_ID") String CM_MS_ID, @Query("DL_BO_MA_ID") String DL_BO_MA_ID);


    @FormUrlEncoded
    @POST("/index.php/api/Order/order")
    Call<OrderResult> createOrder(
            @Field("api_key") String api_key,
            @Field("CM_MS_ID") String CM_MS_ID,
            @Field("HOT_MS_ID") String HOT_MS_ID,
            @Field("DL_AD_MA_ID") String DL_AD_MA_ID,
            @Field("NET_TOTAL") String NET_TOTAL,
            @Field("TAX") String TAX,
            @Field("TOTAL") String TOTAL,
            @Field("DISCOUNT") String DISCOUNT,
            @Field("items") String items,
            @Field("PAYMENT_STATUS") String PAYMENT_STATUS,
            @Field("PAY_METHOD") String PAY_METHOD,
            @Field("discount_code") String discount_code,
            @Field("delivery_fees") String delivery_fees
    );


    @FormUrlEncoded
    @PUT("/index.php/api/Order/order")
    Call<OrderResult> updateOrder(
            @Field("api_key") String api_key,
            @Field("OR_ID") String OR_ID,
            @Field("RATING") String rating,
            @Field("FEEDBACK") String feedback

    );

}
