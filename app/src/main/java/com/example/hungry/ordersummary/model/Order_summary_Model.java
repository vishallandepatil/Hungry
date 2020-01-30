package com.example.hungry.ordersummary.model;

public class Order_summary_Model {

    String hotel_name;
    String contry;


    public Order_summary_Model(String hotel_name, String contry) {
        this.hotel_name = hotel_name;
        this.contry = contry;

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


    }
