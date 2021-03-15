package com.hungry.customer.login.api;

import com.hungry.customer.login.model.ResultVerifyOTP;
import com.hungry.customer.login.model.Token;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.PUT;

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


    @FormUrlEncoded
    @POST("/index.php/api/User/token")
    Call<Token> token(
            @Field("api_key") String api_key,
            @Field("clientmaster_id") String clientmaster_id,
            @Field("mobile") String mobile,
            @Field("token") String token,
            @Field("tokentype") String tokentype,
            @Field("Android_id") String Android_id,
            @Field("device_id") String device_id





    );

    @FormUrlEncoded
    @PUT("/index.php/api/User/update")
    Call<ResultVerifyOTP>  update(
            @Field("api_key") String api_key,
            @Field("CM_MOBILE") String mobile,
            @Field("CITY_MASTER_ID") String city_it

    );

}
