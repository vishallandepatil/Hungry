package com.hungry.customer.myorder.model;

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

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getQuntity() {
        return quntity;
    }

    public void setQuntity(String quntity) {
        this.quntity = quntity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @SerializedName("NAME")
    String name;
    @SerializedName("TYPE")
    String type;



}
