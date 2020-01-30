package com.example.hungry.myorder.model;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.annotations.SerializedName;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

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
        switch (status){
            case "OPEN":
                text="Waiting for Hotel Responce";
                break;
            case "ACCEPT":
                text="Waiting for Hotel Responce";
                break;
            case "READY":
                text="Your Order is Ready";
                break;
            case "DIPACHED":
                text="Delivery Boy is Heading to you";
                break;
            case "DILIVERED":
                text="Delivered  successfully";
                break;
            case "CANCELD":
                text="you cancel your order";
                break;
            case "REJECTED":
                text="Hotel Reject your order";
                break;

        }
        view.setText(text);

    }

}


