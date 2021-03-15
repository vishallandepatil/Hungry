package com.hungry.customer.promocode.api;

import com.hungry.customer.promocode.model.PromoCodeResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PromoCode {
    @GET("/index.php/api/promoCode/promocode")
    Call<PromoCodeResult> getPromoCode(@Query("api_key") String api_key, @Query("menu_id") String menu_id, @Query("hotel_id") String hotel_id, @Query("client_id") String client_id);

}
