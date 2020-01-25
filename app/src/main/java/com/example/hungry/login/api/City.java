package com.example.hungry.login.api;

import com.example.hungry.login.model.CityResult;
import com.example.hungry.login.model.Result;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface City {

    @GET("/index.php/api/city/city")
    Call<CityResult> getCity(@Query("api_key") String api_key, @Query("statemaster_id") String statemaster_id);

}
