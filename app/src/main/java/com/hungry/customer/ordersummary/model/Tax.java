package com.hungry.customer.ordersummary.model;

import com.google.gson.annotations.SerializedName;

public class Tax {

    @SerializedName("id")
    public int id;
    @SerializedName("taxname")
    public String taxName;
    @SerializedName("taxrate")
    public Double taxrate;
    @SerializedName("type")
    public String type;


}

