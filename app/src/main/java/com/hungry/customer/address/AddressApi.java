package com.hungry.customer.address;

import com.hungry.customer.address.model.AddressResult;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface AddressApi {

    @FormUrlEncoded
    @POST("/index.php/api/Order/addDeliveryAddress")
    Call<AddressResult>  addAdress(
            @Field("api_key") String api_key,
            @Field("LANG") String lang,
            @Field("LAT") String lat,
            @Field("LINE1") String line1,
            @Field("LINE2") String line2,
            @Field("PIN_CODE") String pincode,
            @Field("CI_MA_ID") String client_id,
            @Field("CITY_ID") String CITY_ID
    );

}



