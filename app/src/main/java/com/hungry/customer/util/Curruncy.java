package com.hungry.customer.util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.Format;
import java.text.NumberFormat;
import java.util.Locale;

public class Curruncy {
    public static String getCurruncy(String  amount){

        try {

            return "Rs. " + String.format("%.2f", Double.parseDouble(amount));
        }catch (Exception e){
            return "Rs. 0.00";
        }

    }
    public static String getDiscountCurruncy(String  amount){


        return "Rs. -" + String.format("%.2f",Double.parseDouble(amount));


    }

}
