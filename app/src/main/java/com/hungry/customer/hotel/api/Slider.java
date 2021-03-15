package com.hungry.customer.hotel.api;


import com.hungry.customer.hotel.model.SliderResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Slider {

    @GET("/index.php/api/Slider/images")
    Call<SliderResult> getSliders(@Query("api_key") String api_key, @Query("citymaster_id") String citymaster_id);

}