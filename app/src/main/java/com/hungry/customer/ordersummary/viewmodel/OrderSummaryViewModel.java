package com.hungry.customer.ordersummary.viewmodel;

import android.view.View;
import android.widget.Toast;

import com.hungry.customer.HomePage;
import com.hungry.customer.address.model.AddressResult;
import com.hungry.customer.app.PrefManager;
import com.hungry.customer.hotel.model.Menu;
import com.hungry.customer.login.model.User;
import com.hungry.customer.myorder.model.OrderResult;
import com.hungry.customer.myorder.repository.OrderRepository;
import com.hungry.customer.ordersummary.model.Tax;
import com.hungry.customer.ordersummary.model.TaxResult;
import com.hungry.customer.ordersummary.repository.TaxRepository;
import com.hungry.customer.promocode.model.PromoCode;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class OrderSummaryViewModel extends ViewModel {
    public static String PAYMENT_STATUS_UNPAID = "UNPAID";
    public static String PAY_METHOD = "CASH";
    MutableLiveData<ArrayList<Menu>> arrayListMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<Double> netTotal = new MutableLiveData<>();
    public MutableLiveData<Double> deliveryFees = new MutableLiveData<>();
    public MutableLiveData<Double> servicecharge = new MutableLiveData<>();
    public MutableLiveData<Double> discount = new MutableLiveData<>();
    public MutableLiveData<Double> total = new MutableLiveData<>();
    public MutableLiveData<TaxResult> taxResultMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<AddressResult> addressResultMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<PromoCode> promoCodeMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<Boolean> isErrorShown = new MutableLiveData<>();
    public MutableLiveData<String> address = new MutableLiveData<>();
    public MutableLiveData<String> errorpromocode = new MutableLiveData<>();
    public MutableLiveData<String> suggestionforhpotel = new MutableLiveData<>();
    public MutableLiveData<OrderResult> orderResultMutableLiveData = new MutableLiveData<>();

    public OrderSummaryViewModel() {
        isErrorShown.setValue(true);
    }

    public void setTaxResult(TaxResult taxResult) {
        taxResultMutableLiveData.setValue(taxResult);
        calculateTotal();

    }


    public MutableLiveData<ArrayList<Menu>> getArrayListMutableLiveData() {
        return arrayListMutableLiveData;
    }

    public void setArrayMenu(ArrayList<Menu> menus) {
        this.arrayListMutableLiveData.setValue(menus);
        Double netTotal = 0.0;
        for (Menu menu : menus) {
            netTotal += menu.total;

        }
        this.netTotal.setValue(netTotal);
        calculateTotal();
    }

    public void calculateTotal() {

        if(taxResultMutableLiveData.getValue()!=null) {
            for (Tax taxResult : taxResultMutableLiveData.getValue().result) {
                if (taxResult.id == 1) {
                    if (taxResult.type.equalsIgnoreCase("AMOUNT")) {
                        deliveryFees.setValue(taxResult.taxrate);
                    }
                    if (taxResult.type.equalsIgnoreCase("PERCENT")) {
                        double tax = taxResult.taxrate;
                        //Percentage = (Value ⁄ Total Value) × 100
                        double taxamount = (tax * netTotal.getValue()) / 100;
                        deliveryFees.setValue(taxamount);
                    }

                }
                if (taxResult.id == 2) {
                    if (taxResult.type.equalsIgnoreCase("AMOUNT")) {
                        servicecharge.setValue(taxResult.taxrate);
                    }
                    if (taxResult.type.equalsIgnoreCase("PERCENT")) {
                        double tax = taxResult.taxrate;
                        //Percentage = (Value ⁄ Total Value) × 100
                        if (netTotal.getValue() != null) {
                            double taxamount = (tax * netTotal.getValue()) / 100;
                            servicecharge.setValue(taxamount);
                        }
                    }
                }


            }
            discount.setValue(Double.valueOf(0.0));
            PromoCode promoCode = promoCodeMutableLiveData.getValue();
            if (promoCode != null) {
                if (promoCode.maxTotal < netTotal.getValue()) {
                    errorpromocode.setValue(null);
                    String errormessage = "Procode is not applicable for Menu In Cart";

                    for (Menu menu : arrayListMutableLiveData.getValue()) {
                        if (menu.menuId == promoCode.menuId || promoCode.menuId == 0) {
                            if (promoCode.type.equalsIgnoreCase("FixedAmt")) {
                                if (promoCode.menuId != 0) {
                                    discount.setValue(Double.valueOf(promoCode.offer));
                                    menu.discount = Double.valueOf(promoCode.offer);
                                }
                                else {
                                    discount.setValue(Double.valueOf(promoCode.offer));
                                    menu.discount = Double.valueOf(promoCode.offer)/ arrayListMutableLiveData.getValue().size();
                                }
                            }
                            if (promoCode.type.equalsIgnoreCase("Percentage")) {
                                double percentage = Double.parseDouble(promoCode.offer);
                                //Percentage = (Value ⁄ Total Value) × 100
                                if (promoCode.menuId != 0) {
                                    //discount on perticular menu
                                    double discountamount = (percentage * (menu.qtyOrder * menu.amount)) / 100;
                                    discount.setValue(Double.valueOf(discountamount));
                                    menu.discount = Double.valueOf(discountamount);

                                } else {
                                    // discount for hotel witch is divided with menu
                                    double discountamount = (percentage * (netTotal.getValue())) / 100;
                                    discount.setValue(Double.valueOf(discountamount));
                                    menu.discount = (percentage * (menu.getTotal())) / 100;
                                }
                            }
                            errormessage = null;
                        }
                    }
                    errorpromocode.setValue(errormessage);
                    if (errormessage != null) {
                        promoCodeMutableLiveData.setValue(null);
                    }
                } else {
                    promoCodeMutableLiveData.setValue(null);
                    discount.setValue(Double.valueOf(0.0));

                    errorpromocode.setValue("To use Net Toatal Must be greater than Rs." + promoCode.maxTotal + ".");

                }


            } else {
                discount.setValue(Double.valueOf(0.0));
            }
        }
    }

    public void onClick(final View view) {
        if (addressResultMutableLiveData.getValue() != null && 50 <= netTotal.getValue()) {

            final SweetAlertDialog pDialog = new SweetAlertDialog(view.getContext(), SweetAlertDialog.WARNING_TYPE);
            pDialog.setTitleText("Are you sure?")
                    .setContentText("Your Order will be place!")
                    .setConfirmText("Yes,Confirm it!")
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            pDialog.changeAlertType(SweetAlertDialog.PROGRESS_TYPE);
                            pDialog.setTitleText("Please wait");
                            pDialog.setConfirmText("");
                            User user = new PrefManager(view.getContext()).getUserDetails();
                            String CM_MA_ID = String.valueOf(user.getCM_ID());
                            JSONArray jsonArray = new JSONArray();

                            try {
                                for (Menu item : ((HomePage) view.getContext()).cart) {
                                    JSONObject menu = new JSONObject();
                                    menu.put("MN_MA_ID", item.menuId);
                                    menu.put("NET_PRICE", item.amount);
                                    menu.put("TOTALPRICE", item.getTotal());
                                    menu.put("QUINTITY", item.getQtyOrder());
                                    menu.put("commission", item.commision);
                                    menu.put("DISCOUNT", item.discount);
                                    jsonArray.put(menu);


                                }
                            } catch (Exception e) {

                            }

                            String jsonitem = jsonArray.toString();
                            String hotel = ((HomePage) view.getContext()).cart.get(0).hotelID;
                            String addressid = addressResultMutableLiveData.getValue().result.get(0).ID;
                            String netTotalStr = netTotal.getValue() + "";
                            String tax = servicecharge.getValue() + "";
                            Double deleveryfees = deliveryFees.getValue();
                            Double nettoatalval = netTotal.getValue();
                            Double servicechargeValue = servicecharge.getValue();
                            Double discountValue = discount.getValue();

                            String value = nettoatalval + deleveryfees + servicechargeValue - discountValue + "";
                            String discountvalue = discount.getValue() + "";
                            String promocodevalue = promoCodeMutableLiveData.getValue() + "";

                            final MutableLiveData<OrderResult> orderResultMutableLiveData2 = new OrderRepository().createtOrder(CM_MA_ID, hotel, addressid, netTotalStr, tax, value, discountvalue, jsonitem, PAYMENT_STATUS_UNPAID, PAY_METHOD, promocodevalue, deleveryfees
                                    + "", suggestionforhpotel.getValue());
                            orderResultMutableLiveData2.observeForever(orderResult -> {
                                if (orderResult.status == 200) {
                                    pDialog.changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                                    pDialog.dismissWithAnimation();
                                    ((HomePage) view.getContext()).cart.clear();
                                    orderResultMutableLiveData.setValue(orderResult);
                                } else {
                                    pDialog.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                                    pDialog.setTitleText(orderResult.message);
                                }
                            });
                        }
                    })
                    .show();
        } else {
            Toast.makeText(view.getContext(), "Minimum Amount of Order Must be Rs. 50 ", Toast.LENGTH_LONG).show();

        }

    }


}
