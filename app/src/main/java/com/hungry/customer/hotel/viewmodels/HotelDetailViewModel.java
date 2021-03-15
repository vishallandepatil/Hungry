package com.hungry.customer.hotel.viewmodels;

import com.hungry.customer.hotel.model.MenuResult;
import com.hungry.customer.hotel.repository.MenuRepository;
import com.hungry.customer.hotel.model.HotelModel;

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
    public MutableLiveData<Boolean> isVeg = new MutableLiveData<>();


    public HotelDetailViewModel() {
        isShown.setValue(true);
    }

    public void setMutaibleHotelModel(HotelModel hotelModel) {

        this.hotelModel.setValue(hotelModel);
    }
    public String getDateFormatted(){
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        DateFormat dateFormat2 = new SimpleDateFormat("hh:mm aa");

        try {

            return dateFormat2.format(dateFormat.parse(hotelModel.getValue().getStarTtime()))
                    + " To " + dateFormat2.format(dateFormat.parse(hotelModel.getValue().getEndTime()));
        } catch (Exception e){
            return  "";
        }
    }
    public void loadMenus(String search,String category){
        isLoading.setValue(true);
        MenuRepository repository =new  MenuRepository();
        repository.getMenus(isVeg.getValue()?"VEG":null,"Y",hotelModel.getValue().getId()+"",null,null,search,category).observeForever(new Observer<MenuResult>() {
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
        isVeg.setValue(checked);
        //Toast.makeText(sitchview.getContext(),"",Toast.LENGTH_LONG).show();
        loadMenus(null,null);
    }


    }
