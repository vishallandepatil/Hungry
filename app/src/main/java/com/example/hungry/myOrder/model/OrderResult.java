package com.example.hungry.myOrder.model;

import com.example.hungry.hotel.model.Menu;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class OrderResult {

    @SerializedName("message")
    public String message;
    @SerializedName("status")
    public  int status;
    @SerializedName("count")
    public String count;
    @SerializedName("result")
    public ArrayList<Order> result;
    @SerializedName("type")
    public String type;
}
