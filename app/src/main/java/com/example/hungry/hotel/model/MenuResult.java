package com.example.hungry.hotel.model;

import com.example.hungry.hotel_detail.model.HotelModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MenuResult {

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

        public void setResult(ArrayList<Menu> result) {
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
        public ArrayList<Menu> result;
        @SerializedName("type")
        public String type;
    }


