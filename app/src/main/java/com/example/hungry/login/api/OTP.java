package com.example.hungry.login.api;

import com.example.hungry.login.model.Result;
import com.example.hungry.login.model.ResultVerifyOTP;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface OTP {



        @FormUrlEncoded
        @POST("/index.php/api/otp/send")
        Call<Result>  sendOTP(
                @Field("api_key") String api_key,
                @Field("mobile") String mobile,
                @Field("source") String source

        );
        @FormUrlEncoded
        @PUT("/index.php/api/otp/verify")
        Call<ResultVerifyOTP>  verifyOTP(
                @Field("api_key") String api_key,
                @Field("mobile") String mobile,
                @Field("otp") String otp

        );

}