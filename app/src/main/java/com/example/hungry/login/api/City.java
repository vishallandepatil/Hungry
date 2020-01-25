package com.example.hungry.login.api;

import com.example.hungry.login.model.CityResult;
import com.example.hungry.login.model.Result;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface City {

    @GET("/index.php/api/city/city")
    Call<CityResult> getCity(
            @Field("api_key") String api_key,
            @Field("statemaster_id") String statemaster_id
    );
}
