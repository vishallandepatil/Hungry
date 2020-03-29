package com.example.hungry.address;

import com.example.hungry.hotel.model.HotelResult;
import com.example.hungry.hotel.model.MenuResult;
import com.example.hungry.login.model.CityResult;
import com.example.hungry.login.model.ResultVerifyOTP;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface AddressApi {
    @PUT("/index.php/api/Order/addDeliveryAddress")
    Call<ResultVerifyOTP>  addAdress(
            @Field("api_key") String api_key,
            @Field("LANG") String mobile,
            @Field("LAT") String otp,
            @Field("LINE1") String line1,
            @Field("LINE2") String line2,
            @Field("PIN_CODE") String pincode,
            @Field("CI_MA_ID") String client_id

    );

}

