package com.hungry.customer.login.api;

import com.hungry.customer.login.model.CityResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface City {

    @GET("/index.php/api/city/city")
    Call<CityResult> getCity(@Query("api_key") String api_key, @Query("statemaster_id") String statemaster_id);

}
