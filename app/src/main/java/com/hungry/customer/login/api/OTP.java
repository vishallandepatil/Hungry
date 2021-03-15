package com.hungry.customer.login.api;

import com.hungry.customer.login.model.Result;
import com.hungry.customer.login.model.ResultVerifyOTP;
import com.hungry.customer.welcome.model.AppVersionResponce;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

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
        @GET("/index.php/api/Hotels/appversion")
        Call<AppVersionResponce> appversion(
                @Query("partnertype") String partnertype,
                @Query("api_key") String api_key
                // lang: eng/mar

        );

}