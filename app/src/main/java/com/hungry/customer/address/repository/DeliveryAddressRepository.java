package com.hungry.customer.address.repository;

import com.hungry.customer.address.AddressApi;
import com.hungry.customer.address.model.AddressResult;
import com.hungry.customer.retrofitsetting.RetrofitClientInstance;

import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeliveryAddressRepository {


    public MutableLiveData<AddressResult> addAddress(String lang, String lat, String line1, String line2, String pincode, String clientid, String city_master_id){
        AddressApi deliveryAddress = RetrofitClientInstance.getRetrofitInstance().create(AddressApi.class);
        final MutableLiveData<AddressResult> resultMutableLiveData = new MutableLiveData<>();
        final AddressResult result = new AddressResult();
        pincode = pincode==null? "NA":pincode;
        Call<AddressResult> call = deliveryAddress.addAdress(RetrofitClientInstance.API_KEY,lang,lat,line1,line2,pincode,clientid,city_master_id);
        call.enqueue(new Callback<AddressResult>() {
            @Override
            public void onResponse(Call<AddressResult> call, Response<AddressResult> response) {
                AddressResult data = response.body();
                resultMutableLiveData.setValue(data);
            }

            @Override
            public void onFailure(Call<AddressResult> call, Throwable t) {
                if(t.getLocalizedMessage().equalsIgnoreCase("Unable to resolve host \"hungryindia.co.in\": No address associated with hostname"))
                {
                    result.setMessage("Please Check Enternet Connection");

                } else {
                    result.setMessage(t.getLocalizedMessage());
                }
                resultMutableLiveData.setValue(result);
            }
        });
        return resultMutableLiveData;

    }

}
