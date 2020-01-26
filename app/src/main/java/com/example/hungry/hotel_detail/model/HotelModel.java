package com.example.hungry.hotel_detail.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class HotelModel implements Parcelable {
    @SerializedName("ID")
    int id;
    @SerializedName("NAME")
    String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getLang() {
        return lang;
    }

    public void setLang(Double lang) {
        this.lang = lang;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public String getGoogleid() {
        return googleid;
    }

    public void setGoogleid(String googleid) {
        this.googleid = googleid;
    }

    public String getVegOnly() {
        return vegOnly;
    }

    public void setVegOnly(String vegOnly) {
        this.vegOnly = vegOnly;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStarTtime() {
        return starTtime;
    }

    public void setStarTtime(String starTtime) {
        this.starTtime = starTtime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDeliveryIn() {
        return deliveryIn;
    }

    public void setDeliveryIn(String deliveryIn) {
        this.deliveryIn = deliveryIn;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getRatting() {
        return ratting;
    }

    public void setRatting(String ratting) {
        this.ratting = ratting;
    }

    public String getReg_id() {
        return reg_id;
    }

    public void setReg_id(String reg_id) {
        this.reg_id = reg_id;
    }

    public String getPanNo() {
        return panNo;
    }

    public void setPanNo(String panNo) {
        this.panNo = panNo;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getFranchaices() {
        return franchaices;
    }

    public void setFranchaices(String franchaices) {
        this.franchaices = franchaices;
    }

    @SerializedName("ADDRESS")
    String address;
    @SerializedName("LANG")
    Double lang;
    @SerializedName("LAT")
    Double lat;
    @SerializedName("GOOGLE_ID")
    String  googleid;
    @SerializedName("VEG_ONLY")
    String vegOnly;
    @SerializedName("IMG_URL")
    String image;
    @SerializedName("START_TIME")
    String starTtime;
    @SerializedName("END_TIME")
    String endTime;
    @SerializedName("DELIVER_IN")
    String deliveryIn;
    @SerializedName("MEAL_TYPE")
    String mealType;
    @SerializedName("CI_MA_ID")
    String cityId;
    @SerializedName("RATTING")
    String ratting;
    @SerializedName("REG_ID")
    String reg_id;
    @SerializedName("PAN_NO")
    String panNo;
    @SerializedName("MOBILE_NO")
    String mobileNo;
    @SerializedName("IS_ACTIVE")
    String isActive;
    @SerializedName("franchaices")
    String franchaices;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}