package com.example.hungry.ordersummary.viewmodel;

import com.example.hungry.hotel.model.Menu;
import com.example.hungry.ordersummary.model.Tax;
import com.example.hungry.ordersummary.model.TaxResult;

import java.util.ArrayList;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class OrderSummaryViewModel extends ViewModel {
    MutableLiveData<ArrayList<Menu>> arrayListMutableLiveData =new MutableLiveData<>();
    public MutableLiveData<Double>  netTotal= new MutableLiveData<>();
    public MutableLiveData<Double>  deliveryFees= new MutableLiveData<>();
    public MutableLiveData<Double>  servicecharge= new MutableLiveData<>();
    public MutableLiveData<Double>  discount= new MutableLiveData<>();
    public MutableLiveData<Double>  total= new MutableLiveData<>();
    public MutableLiveData<TaxResult> taxResultMutableLiveData= new MutableLiveData<>();
    public void setTaxResult(TaxResult taxResult) {
        taxResultMutableLiveData.setValue(taxResult);
        calculateTotal();

    }


    public MutableLiveData<ArrayList<Menu>> getArrayListMutableLiveData() {
        return arrayListMutableLiveData;
    }

    public void setArrayMenu(ArrayList<Menu> menus) {
        this.arrayListMutableLiveData.setValue(menus);
        Double netTotal=0.0;
        for (Menu menu:menus) {
            netTotal+=menu.total;

        }
        this.netTotal.setValue(netTotal);
        calculateTotal();
    }
    private void calculateTotal()
    {
        for (Tax taxResult:taxResultMutableLiveData.getValue().result) {
            if(taxResult.id==1){
                if(taxResult.type.equalsIgnoreCase("AMOUNT")){
                    deliveryFees.setValue(taxResult.taxrate);
                }
                if(taxResult.type.equalsIgnoreCase("PERCENT")){
                    double tax= taxResult.taxrate;
                    //Percentage = (Value ⁄ Total Value) × 100
                    double taxamount=  (tax*netTotal.getValue())/100;
                    deliveryFees.setValue(taxamount);
                }

            }
            if(taxResult.id==2){
                if(taxResult.type.equalsIgnoreCase("AMOUNT")){
                    servicecharge.setValue(taxResult.taxrate);
                }
                if(taxResult.type.equalsIgnoreCase("PERCENT")){
                    double tax= taxResult.taxrate;
                    //Percentage = (Value ⁄ Total Value) × 100
                    if (netTotal.getValue()!=null) {
                        double taxamount = (tax * netTotal.getValue()) / 100;
                        servicecharge.setValue(taxamount);
                    }
                }

            }

        }

    }

    public OrderSummaryViewModel() {
    }
}
