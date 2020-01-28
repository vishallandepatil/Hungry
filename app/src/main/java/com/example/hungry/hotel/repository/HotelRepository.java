package com.example.hungry.hotel.repository;

import com.example.hungry.hotel.api.Hotels;
import com.example.hungry.hotel.model.HotelResult;
import com.example.hungry.retrofitsetting.RetrofitClientInstance;

import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HotelRepository {
    public MutableLiveData<HotelResult> getHotel(String city_id, String veg_only, String name, String ratting, String limit, String satrt){

        Hotels hotels = RetrofitClientInstance.getRetrofitInstance().create(Hotels.class);
        final MutableLiveData<HotelResult> resultMutableLiveData = new MutableLiveData<>();
        Call<HotelResult> call = hotels.getHotels(RetrofitClientInstance.API_KEY,city_id,veg_only,name,ratting,limit,satrt);
        final HotelResult result = new HotelResult();

        call.enqueue(new Callback<HotelResult>() {
            @Override
            public void onResponse(Call<HotelResult> call, Response<HotelResult> response) {
                HotelResult data = response.body();
                resultMutableLiveData.setValue(data);

            }

            @Override
            public void onFailure(Call<HotelResult> call, Throwable t) {
                if(t.getLocalizedMessage().equalsIgnoreCase("Unable to resolve host \"hungryindia.co.in\": No address associated with hostname"))
                { result.setMessage("Please Check Enternet Connection");

                } else {
                    result.setMessage(t.getLocalizedMessage());
                }
                resultMutableLiveData.setValue(result);


            }
        });
        return resultMutableLiveData;

    }

}
