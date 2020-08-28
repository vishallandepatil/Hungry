package com.example.hungry.ordersummary;

import android.content.Intent;
import android.location.Geocoder;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hungry.HomePage;
import com.example.hungry.address.Address;
import com.example.hungry.address.model.AddressResult;
import com.example.hungry.databinding.PromocodeFragmentBinding;
import com.example.hungry.hotel.CartListner;
import com.example.hungry.myorder.fragment.TrackOrderFragment;
import com.example.hungry.myorder.model.OrderResult;
import com.example.hungry.ordersummary.adapter.OrderSummaryAdapter;
import com.example.hungry.ordersummary.model.TaxResult;
import com.example.hungry.ordersummary.viewmodel.OrderSummaryViewModel;
import com.example.hungry.R;
import com.example.hungry.databinding.FragmentOrderSummaryBinding;
import com.example.hungry.promocode.PromocodeActivity;
import com.example.hungry.promocode.model.PromoCode;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static android.app.Activity.RESULT_OK;

public class OrderSummary extends Fragment {
    private static  int  REQUESTFORADDRESS= 1,REQUESTPROMOCODE= 2;
    private OrderSummaryViewModel orderSummaryViewModel;
    private FragmentOrderSummaryBinding binding;

    public OrderSummary() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static OrderSummary newInstance(String param1, String param2) {
        OrderSummary fragment = new OrderSummary();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_order_summary, container, false);
        orderSummaryViewModel= ViewModelProviders.of(this).get(OrderSummaryViewModel.class);
        binding.setOrderSummaryViewModel(orderSummaryViewModel);
        binding.setLifecycleOwner(this);
        orderSummaryViewModel.setTaxResult(((HomePage) getActivity()).taxResultMutableLiveData.getValue());
        orderSummaryViewModel.setArrayMenu(((HomePage) getActivity()).cart);
        orderSummaryViewModel.addressResultMutableLiveData.observeForever(new Observer<AddressResult>() {
            @Override
            public void onChanged(AddressResult addressResult) {
                orderSummaryViewModel.isErrorShown.setValue(false);
                Geocoder geocoder;
                List<android.location.Address> addresses;
                geocoder = new Geocoder(getContext(), Locale.getDefault());

                try {
                    Double langitude = Double.valueOf(addressResult.result.get(0).getLANG());
                    Double latitude = Double.valueOf(addressResult.result.get(0).getLAT());
                    addresses = geocoder.getFromLocation(langitude,latitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
                    String address  = addressResult.result.get(0).getLINE1() +"  "+addressResult.result.get(0).getLINE2()+"\n"+addresses.get(0).getAddressLine(0);
                    orderSummaryViewModel.address.setValue(address);

                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });

        OrderSummaryAdapter ordeSummaryAdapter = new OrderSummaryAdapter(((HomePage)getActivity()).cart, getActivity());

        ordeSummaryAdapter.setCartListner(new CartListner() {
            @Override
            public void onChange() {
                orderSummaryViewModel.setArrayMenu(((HomePage) getActivity()).cart);
            }
        });
        binding.setAdapter(ordeSummaryAdapter);
        binding.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), Address.class);
                startActivityForResult(intent,REQUESTFORADDRESS);


            }
        });
        binding.promoadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), PromocodeActivity.class);
                intent.putExtra("Hotel", ((HomePage) getActivity()).cart.get(0).hotelID);
                startActivityForResult(intent,REQUESTPROMOCODE);

            }
        });
        binding.promoremove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                orderSummaryViewModel.promoCodeMutableLiveData.setValue(null);
                orderSummaryViewModel.calculateTotal();
            }
        });
        orderSummaryViewModel.orderResultMutableLiveData.observeForever(new Observer<OrderResult>() {
            @Override
            public void onChanged(OrderResult orderResult) {

                Gson gson = new Gson();
                String json = gson.toJson(orderResult.result.get(0));


                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame_container, TrackOrderFragment.newInstance(json))
                        .commit();


            }
        });
        return binding.getRoot();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUESTFORADDRESS  && resultCode == RESULT_OK){


            String coins= data.getStringExtra("result");
            Gson gson = new Gson();
            AddressResult addressResult = gson.fromJson(coins, AddressResult.class);

                orderSummaryViewModel.addressResultMutableLiveData.setValue(addressResult);


        }
        if(requestCode == REQUESTPROMOCODE  && resultCode == RESULT_OK){
            String coins= data.getStringExtra("result");
            Gson gson = new Gson();
            PromoCode promoCode = gson.fromJson(coins, PromoCode.class);
            Log.d("","");
            orderSummaryViewModel.promoCodeMutableLiveData.setValue(promoCode);
            orderSummaryViewModel.calculateTotal();
        }


    }
}
