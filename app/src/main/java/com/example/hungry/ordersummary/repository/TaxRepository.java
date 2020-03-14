package com.example.hungry.ordersummary.repository;

import com.example.hungry.hotel.api.Hotels;
import com.example.hungry.hotel.model.HotelResult;
import com.example.hungry.ordersummary.api.Tax;
import com.example.hungry.ordersummary.model.TaxResult;
import com.example.hungry.retrofitsetting.RetrofitClientInstance;

import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TaxRepository {
    public MutableLiveData<TaxResult> getTax(String city_id){

        Tax taxapi = RetrofitClientInstance.getRetrofitInstance().create(Tax.class);
        final MutableLiveData<TaxResult> resultMutableLiveData = new MutableLiveData<>();
        taxapi.getTAx(RetrofitClientInstance.API_KEY,city_id);
        Call<TaxResult> call = taxapi.getTAx(RetrofitClientInstance.API_KEY,city_id);
        final TaxResult result = new TaxResult();

        call.enqueue(new Callback<TaxResult>() {
            @Override
            public void onResponse(Call<TaxResult> call, Response<TaxResult> response) {
                TaxResult data = response.body();
                resultMutableLiveData.setValue(data);

            }

            @Override
            public void onFailure(Call<TaxResult> call, Throwable t) {
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
