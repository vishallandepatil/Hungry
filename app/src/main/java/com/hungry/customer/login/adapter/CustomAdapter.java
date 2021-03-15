package com.hungry.customer.login.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.hungry.customer.R;
import com.hungry.customer.login.model.CityModel;


/***** Adapter class extends with ArrayAdapter ******/
public class CustomAdapter extends ArrayAdapter<String> {
    private Context activity;
    private ArrayList<CityModel> data;
    LayoutInflater inflater;

    /*************  CustomAdapter Constructor *****************/
    public CustomAdapter(Context activitySpinner,int textViewResourceId,ArrayList objects){
        super(activitySpinner, textViewResourceId, objects);
        activity = activitySpinner;
        data=objects;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    public View getCustomView(int position, View convertView, ViewGroup parent) {
        /********** Inflate spinner_rows.xml file for each row ( Defined below ) ************/
        View row = inflater.inflate(R.layout.spinner_item, parent, false);
        TextView et= (TextView) row.findViewById(R.id.text_name);
        /***** Get each Model object from Arraylist ********/
        data.get(position).getCity_name();
        et.setText(data.get(position).getCity_name());
        return row;
    }

}