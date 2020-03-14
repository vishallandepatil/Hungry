package com.example.hungry.promocode.repository;

import com.example.hungry.promocode.model.PromoCodeResult;
import com.example.hungry.promocode.api.PromoCode;
import com.example.hungry.retrofitsetting.RetrofitClientInstance;

import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PromoCodeRepository {
    public MutableLiveData<PromoCodeResult> getPromoCodes(String hotelID, String menuID, String clientID){

        PromoCode procodeapi = RetrofitClientInstance.getRetrofitInstance().create(PromoCode.class);
        final MutableLiveData<PromoCodeResult> resultMutableLiveData = new MutableLiveData<>();
        Call<PromoCodeResult> call = procodeapi.getPromoCode(RetrofitClientInstance.API_KEY,menuID,hotelID,clientID);
        final PromoCodeResult result = new PromoCodeResult();

        call.enqueue(new Callback<PromoCodeResult>() {
            @Override
            public void onResponse(Call<PromoCodeResult> call, Response<PromoCodeResult> response) {
                PromoCodeResult data = response.body();
                resultMutableLiveData.setValue(data);

            }

            @Override
            public void onFailure(Call<PromoCodeResult> call, Throwable t) {
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
