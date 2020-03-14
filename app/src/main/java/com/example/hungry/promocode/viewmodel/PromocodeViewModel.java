package com.example.hungry.promocode.viewmodel;

import com.example.hungry.promocode.model.PromoCodeResult;
import com.example.hungry.promocode.repository.PromoCodeRepository;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

public class PromocodeViewModel extends ViewModel {
    // TODO: Implement the ViewModel\


    public PromocodeViewModel() {
        isLoading.setValue(false);
    }

    public MutableLiveData<PromoCodeResult> promoCodeResultMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    public MutableLiveData<Boolean> isErrorShown = new MutableLiveData<>();


    public void loadPromoCode(String menuID, String hotelID,String client_id) {
        isLoading.setValue(true);
        new PromoCodeRepository().getPromoCodes(menuID,hotelID , client_id).observeForever(new Observer<PromoCodeResult>() {
            @Override
            public void onChanged(PromoCodeResult orderResult) {
                promoCodeResultMutableLiveData.setValue(orderResult);
                isLoading.setValue(false);
                isErrorShown.setValue(orderResult.status!=200);
            }
        });

    }
}
