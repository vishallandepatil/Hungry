package com.hungry.customer.ordersummary.repository;

import com.hungry.customer.ordersummary.api.Tax;
import com.hungry.customer.ordersummary.model.TaxResult;
import com.hungry.customer.retrofitsetting.RetrofitClientInstance;

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
                getTax (city_id);

            }
        });
        return resultMutableLiveData;

    }


}
