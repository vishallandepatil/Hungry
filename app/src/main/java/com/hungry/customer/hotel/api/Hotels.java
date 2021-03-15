package com.hungry.customer.hotel.api;

import com.hungry.customer.hotel.model.HotelResult;
import com.hungry.customer.hotel.model.MenuResult;
import com.hungry.customer.login.model.CityResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Hotels {
    @GET("/index.php/api/Slider/images")
    Call<CityResult> getCity(@Query("api_key") String api_key, @Query("citymaster_id") String citymaster_id);
    @GET("/index.php/api//Order/orders")
    Call<CityResult> getOrders(@Query("api_key") String api_key, @Query("HOT_MS_ID") String HOT_MS_ID,@Query("OR_STATUS") String OR_STATUS,@Query("CM_MS_ID") String CM_MS_ID,@Query("DL_BO_MA_ID") String DL_BO_MA_ID);
    @GET("/index.php/api/Hotels/hotels")
    Call<HotelResult> getHotels(@Query("api_key") String api_key,@Query("citymaster_id") String citymaster_id,@Query("veg_only") String veg_only,@Query("NAME") String NAME,@Query("RATTING") String RATTING,@Query("limit") String limit,@Query("start") String start);
    @GET("/index.php/api/Hotels/menu")
    Call<MenuResult> getMunus(@Query("api_key") String api_key, @Query("TYPE") String type, @Query("IS_SHOWN") String isShown, @Query("HOT_MA_ID") String hotelID, @Query("limit") String limit, @Query("start") String start, @Query("searchPhrase") String searchPhrase, @Query("CATEGORY") String CATEGORY);

}
