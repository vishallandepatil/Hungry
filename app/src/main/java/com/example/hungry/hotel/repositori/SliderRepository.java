package com.example.hungry.hotel.repositori;

import com.example.hungry.hotel.api.Hotels;
import com.example.hungry.hotel.api.Slider;
import com.example.hungry.hotel.model.HotelResult;
import com.example.hungry.hotel.model.SliderResult;
import com.example.hungry.retrofitsetting.RetrofitClientInstance;

import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SliderRepository {

    public MutableLiveData<SliderResult> getSliderImages(String city_id){

        Slider city = RetrofitClientInstance.getRetrofitInstance().create(Slider.class);
        final MutableLiveData<SliderResult> resultMutableLiveData = new MutableLiveData<>();
        Call<SliderResult> call = city.getSliders(RetrofitClientInstance.API_KEY,city_id);
        final SliderResult result = new SliderResult();

        call.enqueue(new Callback<SliderResult>() {
            @Override
            public void onResponse(Call<SliderResult> call, Response<SliderResult> response) {
                SliderResult data = response.body();
                resultMutableLiveData.setValue(data);

            }

            @Override
            public void onFailure(Call<SliderResult> call, Throwable t) {
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
