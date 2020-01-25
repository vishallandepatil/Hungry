package com.example.hungry.login.api;

import com.example.hungry.login.model.Result;
import com.example.hungry.login.model.ResultVerifyOTP;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Registration {
    @FormUrlEncoded
    @POST("/index.php/api/User/register")
    Call<ResultVerifyOTP> registerUser(
            @Field("api_key") String api_key,
            @Field("CM_MOBILE") String mobile,
            @Field("CM_FIRST_NAME") String fname,
            @Field("CM_LAST_NAME") String lname,
            @Field("CM_EMAIL") String email,
            @Field("STATE_MASTER_ID") String state_id,
            @Field("CITY_MASTER_ID") String city_it,
            @Field("CM_ADDRESS") String address,
            @Field("CM_GENDER") String gender,
            @Field("CM_REG_SOURCE") String source





    );
}
