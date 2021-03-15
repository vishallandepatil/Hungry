package com.hungry.customer.myorder.viewModel;

import android.widget.TextView;

import com.hungry.customer.myorder.model.Order;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TrackOrderViewModel extends ViewModel {
    public MutableLiveData<Order> order = new MutableLiveData<>();
    public MutableLiveData<Integer> openProgress = new MutableLiveData<>();
    public MutableLiveData<Integer> acceptedProgress = new MutableLiveData<>();
    public MutableLiveData<Integer> readyProgress = new MutableLiveData<>();
    public MutableLiveData<Integer> dispatchedProgress = new MutableLiveData<>();
    public MutableLiveData<Integer> diliveredProgress = new MutableLiveData<>();


    @BindingAdapter("bindArivalTime")
    public  static void bindServerDate(@NonNull TextView textView, Order order) {
        SimpleDateFormat outputdatformat = new SimpleDateFormat("hh:mm: a");
        SimpleDateFormat inputdsf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = null;
        if (order.status.equalsIgnoreCase("OPEN")){
            date =order.expectedDeliveryITme;
        }
        else if(order.status.equalsIgnoreCase("ACCEPTED")) {
            date =order.expectedDeliveryITme;
        }
        else if(order.status.equalsIgnoreCase("READY")) {
            date =order.expectedDeliveryITme;
        }
        else if(order.status.equalsIgnoreCase("DISPATCHED")) {
            date =order.expectedDeliveryITme;
        }
        else if(order.status.equalsIgnoreCase("DELIVERED")) {
            date =order.diliveryDateTime;
            outputdatformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
        else if(order.status.equalsIgnoreCase("CANCELED")) {
            date =null;
        }
        else if(order.status.equalsIgnoreCase("REJECTED")) {
            date =null;
        }
        try {
            if(date !=null) {
                Date datestr = inputdsf.parse(date);
                textView.setText(outputdatformat.format(datestr));
            } else {
                textView.setText("");
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
