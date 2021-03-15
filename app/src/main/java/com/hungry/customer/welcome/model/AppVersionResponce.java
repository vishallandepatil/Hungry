package com.hungry.customer.welcome.model;

import com.google.gson.annotations.SerializedName;

public class AppVersionResponce {

    public static String TYPE = "Customer";
    @SerializedName("status")
    public int status;
    @SerializedName("count")
    public int count;
    @SerializedName("type")
    public String type;
    @SerializedName("result")
    public String result;
    @SerializedName("message")
    public String message;
}
