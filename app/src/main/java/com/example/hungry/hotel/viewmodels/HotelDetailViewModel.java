package com.example.hungry.hotel.viewmodels;

import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.hungry.hotel.model.MenuResult;
import com.example.hungry.hotel.repositori.MenuRepository;
import com.example.hungry.hotel_detail.model.HotelModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

public class HotelDetailViewModel extends ViewModel {

    public int max = 5;
    public MutableLiveData<HotelModel>  hotelModel=new MutableLiveData<>();
    public MutableLiveData<MenuResult> menuResultMutableLiveData =new MutableLiveData<>();

    public HotelDetailViewModel() {
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
        MenuRepository repository =new  MenuRepository();
        repository.getMenus(type,"Y",/*hotelModel.getValue().getId()*/"1",null,null).observeForever(new Observer<MenuResult>() {
            @Override
            public void onChanged(MenuResult menuResult) {
                menuResultMutableLiveData.setValue(menuResult);
            }
        });


    }
    public void onCheckedChanged(boolean checked) {
        // implementation
        //Toast.makeText(sitchview.getContext(),"",Toast.LENGTH_LONG).show();
        loadMenus(checked?"VEG":null);
    }


    }
