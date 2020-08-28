package com.example.hungry.hotel.viewmodels;

import com.example.hungry.hotel.model.MenuResult;
import com.example.hungry.hotel.repository.MenuRepository;
import com.example.hungry.hotel.model.HotelModel;
import com.example.hungry.ordersummary.repository.TaxRepository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

public class HotelDetailViewModel extends ViewModel {

    public int max = 5;
    public MutableLiveData<HotelModel>  hotelModel=new MutableLiveData<>();
    public MutableLiveData<MenuResult> menuResultMutableLiveData =new MutableLiveData<>();
    public MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    public MutableLiveData<Boolean> isShown = new MutableLiveData<>();


    public HotelDetailViewModel() {
        isShown.setValue(true);
    }

    public void setMutaibleHotelModel(HotelModel hotelModel) {

        this.hotelModel.setValue(hotelModel);
    }
    public String getDateFormatted(){
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        DateFormat dateFormat2 = new SimpleDateFormat("HH:mm a");

        try {

            return dateFormat2.format(dateFormat.parse(hotelModel.getValue().getStarTtime()))
                    + " To " + dateFormat2.format(dateFormat.parse(hotelModel.getValue().getEndTime()));
        } catch (Exception e){
            return  "";
        }
    }
    public void loadMenus(String type){
        isLoading.setValue(true);
        MenuRepository repository =new  MenuRepository();
        repository.getMenus(type,"Y",hotelModel.getValue().getId()+"",null,null).observeForever(new Observer<MenuResult>() {
            @Override
            public void onChanged(MenuResult menuResult) {
                menuResultMutableLiveData.setValue(menuResult);
                isLoading.setValue(false);
                isShown.setValue(menuResult.status==200);

            }
        });


    }
    public void onCheckedChanged(boolean checked) {
        // implementation
        //Toast.makeText(sitchview.getContext(),"",Toast.LENGTH_LONG).show();
        loadMenus(checked?"VEG":null);
    }


    }
