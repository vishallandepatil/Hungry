package com.example.hungry.hotel.viewmodels;

import android.util.Log;
import android.widget.Toast;

import com.example.hungry.hotel.model.HotelResult;
import com.example.hungry.hotel.model.SliderResult;
import com.example.hungry.hotel.repositori.HotelRepository;
import com.example.hungry.hotel.repositori.SliderRepository;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    public HomeViewModel() {
    }

    public MutableLiveData<SliderResult> sliderResultMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<HotelResult> hotelResultMutableLiveData = new MutableLiveData<>();

    public void loadSliderImages(String city_id){

        sliderResultMutableLiveData = new SliderRepository().getSliderImages(city_id);


    }
    public void loadHotels(String city_id, String veg_only, String name, String ratting, String limit, String satrt){

         new HotelRepository().getHotel(city_id,veg_only,name,ratting,limit,satrt).observeForever(new Observer<HotelResult>() {
            @Override
            public void onChanged(HotelResult hotelResult) {
                hotelResultMutableLiveData.setValue(hotelResult);
            }
        });


    }


}
