package com.example.hungry.ordersummary.model;

import com.example.hungry.ordersummary.model.Tax;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


public class TaxResult {
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

    public void setResult(ArrayList<Tax> result) {
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
    public ArrayList<Tax> result;
    @SerializedName("type")
    public String type;
}
