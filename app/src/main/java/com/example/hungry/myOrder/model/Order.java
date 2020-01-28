package com.example.hungry.myOrder.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Order {
    @SerializedName("OR_ID")
    int id;
    @SerializedName("OR_NO")
    String orderNo;
    @SerializedName("CM_MS_ID")
    String cmMsId;
    @SerializedName("HOT_MS_ID")
    String HotelMasterId;
    @SerializedName("OR_STATUS")
    String status;
    @SerializedName("EX_DILIVERY_TIME")
    String expectedDeliveryITme;
    @SerializedName("DL_BOY_STATUS")
    String deliveryBoyStatus;
    @SerializedName("DL_BO_MA_ID")
    String DeliveryBoyId;
    @SerializedName("DL_DATE_TIME")
    String diliveryDateTime;
    @SerializedName("RATING")
    String ratting;
    @SerializedName("FEEDBACK")
    String feedback;

    @SerializedName("DL_AD_MA_ID")
    String deliveryAddressid;
    @SerializedName("PAY_METHOD")
    String paymentMethod;
    @SerializedName("NET_TOTAL")
    String netTotal;
    @SerializedName("TAX")
    String tax;
    @SerializedName("TOTAL")
    String total;
    @SerializedName("PAYMENT_STATUS")
    String paymentStatus;

    @SerializedName("DISCOUNT")
    String discount;
    @SerializedName("COMM_PERCENT")
    String commision;
    @SerializedName("COMM_AMMOUNT")
    String commisionAmmount;
    @SerializedName("CREATED_AT")
    String createdAt;

    @SerializedName("UPDATED_AT")
    String updatedAt;
    @SerializedName("DB_RATING")
    String dbRating;
    @SerializedName("DISPATCH_CODE")
    String dispatchCode;
    @SerializedName("DB_FEEDBACK")
    String dbFeedback;
    @SerializedName("LNAME")
    String lName;
    @SerializedName("FNAME")
    String fName;

    @SerializedName("items")
    ArrayList<OrderItem> items;


}


