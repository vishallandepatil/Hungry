package com.example.hungry.ordersummary.api;

import com.example.hungry.ordersummary.model.TaxResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Tax {

    //,@Query("franchise_id") String franchise_id
    @GET("/index.php/api/tax/tax")
    Call<TaxResult> getTAx(@Query("api_key") String api_key, @Query("city_id") String citymaster_id);

}
