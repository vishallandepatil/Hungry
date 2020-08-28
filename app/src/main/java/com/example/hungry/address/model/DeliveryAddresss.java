package com.example.hungry.address.model;

import com.google.gson.annotations.SerializedName;

public  class DeliveryAddresss{
    public DeliveryAddresss(String LANG, String LAT, String LINE1, String LINE2) {
        this.LANG = LANG;
        this.LAT = LAT;
        this.LINE1 = LINE1;
        this.LINE2 = LINE2;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getCI_MA_ID() {
        return CI_MA_ID;
    }

    public void setCI_MA_ID(String CI_MA_ID) {
        this.CI_MA_ID = CI_MA_ID;
    }

    public String getLANG() {
        return LANG;
    }

    public void setLANG(String LANG) {
        this.LANG = LANG;
    }

    public String getLAT() {
        return LAT;
    }

    public void setLAT(String LAT) {
        this.LAT = LAT;
    }

    public String getLINE1() {
        return LINE1;
    }

    public void setLINE1(String LINE1) {
        this.LINE1 = LINE1;
    }

    public String getLINE2() {
        return LINE2;
    }

    public void setLINE2(String LINE2) {
        this.LINE2 = LINE2;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public String getUPDATED_AT() {
        return UPDATED_AT;
    }

    public void setUPDATED_AT(String UPDATED_AT) {
        this.UPDATED_AT = UPDATED_AT;
    }

    public String getPIN_CODE() {
        return PIN_CODE;
    }

    public void setPIN_CODE(String PIN_CODE) {
        this.PIN_CODE = PIN_CODE;
    }

    public String getCITY_ID() {
        return CITY_ID;
    }

    public void setCITY_ID(String CITY_ID) {
        this.CITY_ID = CITY_ID;
    }

    @SerializedName("LINE2")
    public String LINE2;
    @SerializedName("STATUS")
    public String STATUS;
    @SerializedName("UPDATED_AT")
    public String UPDATED_AT;
    @SerializedName("PIN_CODE")
    public String PIN_CODE;
    @SerializedName("CITY_ID")
    public String CITY_ID;
    @SerializedName("ID")
    public String ID;
    @SerializedName("CI_MA_ID")
    public String CI_MA_ID;
    @SerializedName("LANG")
    public String LANG;
    @SerializedName("LAT")
    public String LAT;
    @SerializedName("LINE1")
    public String LINE1;
@SerializedName("api_key")
    public String api_key;


}