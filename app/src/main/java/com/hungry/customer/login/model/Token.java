package com.hungry.customer.login.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Token {
    @SerializedName("message")
    public String message;
    @SerializedName("status")
    public  int status;
    @SerializedName("count")
    public String count;
    @SerializedName("type")
    public String type;

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setCount(String count) {
        this.count = count;
    }


    public void setType(String type) {
        this.type = type;
    }
}
