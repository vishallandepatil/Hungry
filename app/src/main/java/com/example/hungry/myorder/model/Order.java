package com.example.hungry.myorder.model;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.annotations.SerializedName;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;

public class Order {
    @SerializedName("OR_ID")
    public int id;
    @SerializedName("OR_NO")
    public String orderNo;
    @SerializedName("CM_MS_ID")
    public String cmMsId;
    @SerializedName("HOT_MS_ID")
    public String HotelMasterId;
    @SerializedName("OR_STATUS")
    public String status;
    @SerializedName("EX_DILIVERY_TIME")
    public String expectedDeliveryITme;
    @SerializedName("DL_BOY_STATUS")
    public String deliveryBoyStatus;
    @SerializedName("DL_BO_MA_ID")
    public String DeliveryBoyId;
    @SerializedName("DL_DATE_TIME")
    public String diliveryDateTime;
    @SerializedName("RATING")
    public String ratting;
    @SerializedName("FEEDBACK")
    public String feedback;

    @SerializedName("DL_AD_MA_ID")
    public String deliveryAddressid;
    @SerializedName("PAY_METHOD")
    public String paymentMethod;
    @SerializedName("NET_TOTAL")
    public String netTotal;
    @SerializedName("TAX")
    public String tax;
    @SerializedName("TOTAL")
    public String total;
    @SerializedName("PAYMENT_STATUS")
    public String paymentStatus;

    @SerializedName("DISCOUNT")
    public String discount;
    @SerializedName("COMM_PERCENT")
    public String commision;
    @SerializedName("COMM_AMMOUNT")
    public String commisionAmmount;
    @SerializedName("CREATED_AT")
    public String createdAt;

    @SerializedName("UPDATED_AT")
    public String updatedAt;
    @SerializedName("DB_RATING")
    public String dbRating;
    @SerializedName("DISPATCH_CODE")
    public String dispatchCode;
    @SerializedName("DB_FEEDBACK")
    public String dbFeedback;
    @SerializedName("LNAME")
    public String lName;
    @SerializedName("FNAME")
    public String fName;
    @SerializedName("NAME")
    public String hotelName;
    @SerializedName("IMG_URL")
    public String imagPath;
    @SerializedName("delivery_fees")
    public String delivery_fees;

    @SerializedName("items")
    public ArrayList<OrderItem> items;

    public String getDateFormatted() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");

        try {

            return dateFormat.format(dateFormat.parse(diliveryDateTime));

        } catch (Exception e) {
            return "";

        }
    }
    @BindingAdapter("imageUrl")
    public static void loadImage(ImageView view, String url) {
        Glide.with(view.getContext()).load(url).into(view);
    }
    @BindingAdapter("orderstatus")
    public static void statusOrder(TextView view, String status) {
        //'','','READY','','DILIVERED','CANCELD','REJECTED'
        String text="";

            if(status.equalsIgnoreCase("OPEN")) {
                text = "Waiting for Hotel Responce";
            } else if (status.equalsIgnoreCase("ACCEPTED")) {

                    text = "Accepted by Hotel";
            } else if (status.equalsIgnoreCase("READY")) {

                text = "Your Order is Ready";
            } else if (status.equalsIgnoreCase("DISPATCHED")) {

                text = "Delivery Boy is Heading to you";
            } else if (status.equalsIgnoreCase("DELIVERED")) {

                text = "Delivered  successfully";
            } else if (status.equalsIgnoreCase("CANCELED")) {

                text = "you cancel your order";
            }else if (status.equalsIgnoreCase("REJECTED")) {

                text = "Hotel Reject your order";
            }



        view.setText(text);

    }
    @BindingAdapter("bindcreatedAt")
    public  static void bindcreatedAt(@NonNull TextView textView, Order order) {
        SimpleDateFormat outputdatformat = new SimpleDateFormat("dd-MMM hh:mm: a");
        SimpleDateFormat inputdsf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        if(order.createdAt !=null) {
            Date datestr = null;
            try {
                datestr = inputdsf.parse(order.createdAt);
                textView.setText(outputdatformat.format(datestr));
            } catch (ParseException e) {
                e.printStackTrace();
            }

        } else {
            textView.setText("");
        }
    }



}


