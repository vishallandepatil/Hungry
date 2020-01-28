package com.example.hungry.hotel.model;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.gson.annotations.SerializedName;

import androidx.databinding.BindingAdapter;

public class Menu {

    @SerializedName("MENU_MA_ID")
    public int menuId;
    @SerializedName("NAME")
    public String name;
    @SerializedName("AMOUNT")
    public Double amount;
    @SerializedName("TYPE")
    public String type;
    @SerializedName("QUNTITY")
    public String qnty;
    @SerializedName("DESCRIPTION")
    public String description;
    @SerializedName("CATEGORY")
    public String categoery;
    @SerializedName("START_TIME")
    public String startTime;
    @SerializedName("END_TIME")
    public String endTime;
    @SerializedName("IS_SHOWN")
    public String isShown;
    @SerializedName("HOT_MA_ID")
    public String hotelID;
    @SerializedName("ADDED_BY")
    public String addedBy;
    @SerializedName("PRIORITY_ID")
    public String priority;
    @SerializedName("CREATED_AT")
    public String createdAt;
    @SerializedName("UPDATED_AT")
    public String updatedAt;
    @SerializedName("path")
    public String path;
    public  boolean isAddedToCart=false;

    @BindingAdapter("imageUrl")
    public static void loadImage(ImageView view, String url) {
        Glide.with(view.getContext())
                .load(url)
                .into(view);
    }
}
