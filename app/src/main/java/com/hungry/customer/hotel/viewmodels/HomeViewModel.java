package com.hungry.customer.hotel.viewmodels;

import com.hungry.customer.hotel.model.HotelResult;
import com.hungry.customer.hotel.model.MenuResult;
import com.hungry.customer.hotel.model.SliderResult;
import com.hungry.customer.hotel.repository.HotelRepository;
import com.hungry.customer.hotel.repository.MenuRepository;
import com.hungry.customer.hotel.repository.SliderRepository;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    public HomeViewModel() {
    }

    public MutableLiveData<SliderResult> sliderResultMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<HotelResult> hotelResultMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<MenuResult> menuResultMutableLiveData =new MutableLiveData<>();
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
    public void loadMenu(String searchPhrase){
        isLoading.setValue(true);
        new MenuRepository().getMenus(null,"Y",null,null,null,searchPhrase,null).observeForever(new Observer<MenuResult>() {
            @Override
            public void onChanged(MenuResult menuResult) {
                menuResultMutableLiveData.setValue(menuResult);
                isLoading.setValue(false);
            }
        });


    }


}
