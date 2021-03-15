package com.hungry.customer.hotel.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.hungry.customer.BR;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;

public class Menu  extends BaseObservable  implements Parcelable {


    @SerializedName("MENU_MA_ID")
    public int menuId;
    @SerializedName("NAME")
    public String name;
    @Expose(serialize = true)
    @SerializedName("AMOUNT")
    public Double amount;
    @Expose(serialize = true)
    @SerializedName("AMOUNT_ORIGNAL")
    public Double AMOUNTORIGNAL;
    @SerializedName("TYPE")
    public String type;
    @SerializedName("QUNTITY")
    public String qnty;
    @SerializedName("HOTEL_NAME")
    public String hotelname;
    @SerializedName("commision")
    public String commision;

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
    public Double discount;

    protected Menu(Parcel in) {
        menuId = in.readInt();
        name = in.readString();
        if (in.readByte() == 0) {
            amount = null;
        } else {
            amount = in.readDouble();
        }
        type = in.readString();
        qnty = in.readString();
        hotelname = in.readString();
        description = in.readString();
        categoery = in.readString();
        startTime = in.readString();
        endTime = in.readString();
        isShown = in.readString();
        hotelID = in.readString();
        addedBy = in.readString();
        priority = in.readString();
        createdAt = in.readString();
        updatedAt = in.readString();
        path = in.readString();
        isAddedToCart = in.readByte() != 0;
        qtyOrder = in.readInt();
        if (in.readByte() == 0) {
            total = null;
        } else {
            total = in.readDouble();
        }
    }

    public static final Creator<Menu> CREATOR = new Creator<Menu>() {
        @Override
        public Menu createFromParcel(Parcel in) {
            return new Menu(in);
        }

        @Override
        public Menu[] newArray(int size) {
            return new Menu[size];
        }
    };

    @Bindable
    public int getQtyOrder() {
        return qtyOrder;
    }

    public int qtyOrder=1;
    public Double total;

    @BindingAdapter("imageUrl")
    public static void loadImage(ImageView view, String url) {
        Glide.with(view.getContext()).load(url).into(view);
    }
    @Bindable
    public Double getTotal() {
        return total;
    }

    @BindingAdapter("amount")
    public static void getTotal(TextView view, Menu menu) {
        Double total = menu.qtyOrder*menu.amount;
        view.setText("Rs. "+total+"");
    }

    public boolean getVisiblity(){
        if((AMOUNTORIGNAL.intValue()) <= ( amount. intValue())){

            return false;
        } else {
            return true;
        }
    }
    public void onAddQntity(final View view) {
        qtyOrder++;
        total = qtyOrder*amount;
        notifyPropertyChanged(BR.qtyOrder);
        notifyPropertyChanged(BR.total);

    }
    public void onRemovieQntity(final View view) {
        if(qtyOrder ==1 ){
            return;
        }
        qtyOrder--;
        total = qtyOrder*amount;
        notifyPropertyChanged(BR.qtyOrder);
        notifyPropertyChanged(BR.total);

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(menuId);
        parcel.writeString(name);
        if (amount == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(amount);
        }
        if (AMOUNTORIGNAL == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(AMOUNTORIGNAL);
        }
        parcel.writeString(type);
        parcel.writeString(qnty);
        parcel.writeString(hotelname);
        parcel.writeString(description);
        parcel.writeString(categoery);
        parcel.writeString(startTime);
        parcel.writeString(endTime);
        parcel.writeString(isShown);
        parcel.writeString(hotelID);
        parcel.writeString(addedBy);
        parcel.writeString(priority);
        parcel.writeString(createdAt);
        parcel.writeString(updatedAt);
        parcel.writeString(path);
        parcel.writeByte((byte) (isAddedToCart ? 1 : 0));
        parcel.writeInt(qtyOrder);
        if (total == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(total);
        }
    }
}
