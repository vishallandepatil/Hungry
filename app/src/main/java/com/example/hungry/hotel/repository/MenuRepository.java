package com.example.hungry.hotel.repository;

import com.example.hungry.hotel.api.Hotels;
import com.example.hungry.hotel.model.MenuResult;
import com.example.hungry.retrofitsetting.RetrofitClientInstance;

import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuRepository {

    public MutableLiveData<MenuResult> getMenus(String type,String isShown, String hotelID, String limit, String satrt){

        Hotels hotels = RetrofitClientInstance.getRetrofitInstance().create(Hotels.class);
        final MutableLiveData<MenuResult> resultMutableLiveData = new MutableLiveData<>();
        Call<MenuResult> call = hotels.getMunus(RetrofitClientInstance.API_KEY,type,isShown,hotelID,limit,satrt);
        final MenuResult result = new MenuResult();

        call.enqueue(new Callback<MenuResult>() {
            @Override
            public void onResponse(Call<MenuResult> call, Response<MenuResult> response) {
                MenuResult data = response.body();
                resultMutableLiveData.setValue(data);

            }

            @Override
            public void onFailure(Call<MenuResult> call, Throwable t) {
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
