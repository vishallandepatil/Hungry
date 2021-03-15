package com.hungry.customer.address.viewmodel;

import android.graphics.Color;
import android.view.View;
import android.widget.Toast;

import com.hungry.customer.address.model.AddressResult;
import com.hungry.customer.address.model.DeliveryAddresss;
import com.hungry.customer.address.repository.DeliveryAddressRepository;
import com.hungry.customer.app.PrefManager;
import com.hungry.customer.login.model.User;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class DeliveryAddessModel extends ViewModel {
    public MutableLiveData<String> line1 = new MutableLiveData<>();
    public MutableLiveData<String> line2 = new MutableLiveData<>();
    public MutableLiveData<Boolean> isErrorShown = new MutableLiveData<>();
    public DeliveryAddessModel() {


        isErrorShown.setValue(false);
        isErrorShown2.setValue(false);


    }
    public MutableLiveData<Boolean> isErrorShown2 = new MutableLiveData<>();
    public MutableLiveData<AddressResult> addressResultMutableLiveData = new MutableLiveData<>();
    public DeliveryAddresss deliveryAddresss;

    public void onClick(final View view) {

        if (deliveryAddresss != null ) {
            if(line1.getValue()==null ) {
                isErrorShown.setValue(true);
            } else  if(line2.getValue()==null ){
                isErrorShown2.setValue(true);
            }
            else {
                isErrorShown.setValue(false);
                isErrorShown2.setValue(false);
                final SweetAlertDialog pDialog = new SweetAlertDialog(view.getContext(), SweetAlertDialog.PROGRESS_TYPE);
                pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                pDialog.setTitleText("Loading");
                pDialog.setCancelable(false);
                pDialog.show();
                User user = new PrefManager(view.getContext()).getUserDetails();
                String id = user.getCM_MA_ID();
                int city_master_id = user.getCITY_MASTER_ID();

                new DeliveryAddressRepository().addAddress(deliveryAddresss.LANG, deliveryAddresss.getLAT(), line1.getValue(), line2.getValue(), deliveryAddresss.PIN_CODE, id + "", city_master_id + "").observeForever(new Observer<AddressResult>() {
                    @Override
                    public void onChanged(AddressResult addressResult) {

                        if (addressResult.status == 200) {
                            pDialog.changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                            pDialog.dismissWithAnimation();
                            addressResultMutableLiveData.setValue(addressResult);
                        } else {
                            pDialog.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                            pDialog.dismissWithAnimation();
                        }


                    }
                });
            }

        } else {
            Toast.makeText(view.getContext(), "Make sure your currunt location is selected ", Toast.LENGTH_LONG).show();
        }
    }
}
