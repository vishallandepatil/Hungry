package com.example.hungry.hotel.viewmodels;

import com.example.hungry.hotel.model.HotelResult;
import com.example.hungry.hotel.model.SliderResult;
import com.example.hungry.hotel.repository.HotelRepository;
import com.example.hungry.hotel.repository.SliderRepository;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    public HomeViewModel() {
    }

    public MutableLiveData<SliderResult> sliderResultMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<HotelResult> hotelResultMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<Boolean> isLoading = new MutableLiveData<>();


    public void loadSliderImages(String city_id){

        sliderResultMutableLiveData = new SliderRepository().getSliderImages(city_id);


    }
    public void loadHotels(String city_id, String veg_only, String name, String ratting, String limit, String satrt){
        isLoading.setValue(true);
         new HotelRepository().getHotel(city_id,veg_only,name,ratting,limit,satrt).observeForever(new Observer<HotelResult>() {
            @Override
            public void onChanged(HotelResult hotelResult) {
                hotelResultMutableLiveData.setValue(hotelResult);
                isLoading.setValue(false);
            }
        });


    }


}
