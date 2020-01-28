package com.example.hungry.myOrder.model;

import com.google.gson.annotations.SerializedName;

public class OrderItem {

    @SerializedName("OR_ITM_ID")
    int id;
    @SerializedName("MN_MA_ID")
    String menuMasterID;
    @SerializedName("NET_PRICE")
    String netPrice;
    @SerializedName("DISCOUNT")
    String discount;
    @SerializedName("TOTALPRICE")
    String totalPrice;
    @SerializedName("QUINTITY")
    String quntity;
    @SerializedName("OR_MA_ID")
    String orderMasterID;
    @SerializedName("CREATED_AT")
    String createdAt;
    @SerializedName("UPDADATED_AT")
    String updatedAt;
    @SerializedName("NAME")
    String name;
    @SerializedName("TYPE")
    String type;



}
