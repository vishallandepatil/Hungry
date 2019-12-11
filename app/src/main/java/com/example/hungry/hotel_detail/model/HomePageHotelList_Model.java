package com.example.hungry.hotel_detail.model;

public class HomePageHotelList_Model {


    String hotel_name;
    String contry;
    String address;
    String date_time;

    public HomePageHotelList_Model(String hotel_name, String contry, String address, String date_time) {
        this.hotel_name = hotel_name;
        this.contry = contry;
        this.address = address;
        this.date_time = date_time;
    }

    public String getHotel_name() {
        return hotel_name;
    }

    public void setHotel_name(String hotel_name) {
        this.hotel_name = hotel_name;
    }

    public String getContry() {
        return contry;
    }

    public void setContry(String contry) {
        this.contry = contry;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDate_time() {
        return date_time;
    }

    public void setDate_time(String date_time) {
        this.date_time = date_time;
    }
}