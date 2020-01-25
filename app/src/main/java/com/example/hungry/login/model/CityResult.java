package com.example.hungry.login.model;

import com.google.gson.annotations.SerializedName;

public class CityResult {
    @SerializedName("message")
    public String message;
    @SerializedName("status")
    public  int status;
    @SerializedName("count")
    public String count;
    @SerializedName("result")
    public CityModel result;

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

    public String getCount() {
        return count;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public void setResult(CityModel result) {
        this.result = result;
    }

    public void setType(String type) {
        this.type = type;
    }

    public CityModel getResult() {
        return result;
    }

    public String getType() {
        return type;
    }

    @SerializedName("type")
    public String type;
}
