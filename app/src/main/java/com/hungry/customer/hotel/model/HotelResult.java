package com.hungry.customer.hotel.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class HotelResult {
    @SerializedName("message")
    public String message;

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public void setResult(ArrayList<HotelModel> result) {
        this.result = result;
    }

    public void setType(String type) {
        this.type = type;
    }

    @SerializedName("status")
    public  int status;
    @SerializedName("count")
    public String count;
    @SerializedName("result")
    public ArrayList<HotelModel> result;
    @SerializedName("type")
    public String type;
    @SerializedName("trendings")
    public ArrayList<Menu> trendings;
}
