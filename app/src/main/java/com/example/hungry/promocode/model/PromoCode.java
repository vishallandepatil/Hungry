package com.example.hungry.promocode.model;

import com.google.gson.annotations.SerializedName;

public class PromoCode {
    @SerializedName("promoCodeMaster")
    public int promoCodeMaster;
    @SerializedName("menu_id")
    public int menuId;
    @SerializedName("hotel_id")
    public int hotelId;
    @SerializedName("StartDate")
    public String startDate;
    @SerializedName("endDate")
    public String endDate;
    @SerializedName("maxClient")
    public String maxClient;
    @SerializedName("userType")
    public String userType;

    @SerializedName("saleType")
    public String saleType;
    @SerializedName("type")
    public String type;
    @SerializedName("offer")
    public String offer;
    @SerializedName("CreatedDate")
    public String createdDate;
    @SerializedName("Status")
    public String status;
    @SerializedName("discription")
    public String discription;

    @SerializedName("maxTotal")
    public int maxTotal;
    @SerializedName("promoCodeId")
    public int promoCodeId;
    @SerializedName("code")
    public String code;
    @SerializedName("promoCodeFor")
    public String promoCodeFor;


}
