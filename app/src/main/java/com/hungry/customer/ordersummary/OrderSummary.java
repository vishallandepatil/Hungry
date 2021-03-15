package com.hungry.customer.ordersummary;

import android.content.Intent;
import android.location.Geocoder;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hungry.customer.HomePage;
import com.hungry.customer.address.Address;
import com.hungry.customer.address.Address2;
import com.hungry.customer.address.model.AddressResult;
import com.hungry.customer.app.PrefManager;
import com.hungry.customer.databinding.PromocodeFragmentBinding;
import com.hungry.customer.hotel.CartListner;
import com.hungry.customer.myorder.fragment.TrackOrderFragment;
import com.hungry.customer.myorder.model.OrderResult;
import com.hungry.customer.ordersummary.adapter.OrderSummaryAdapter;
import com.hungry.customer.ordersummary.model.TaxResult;
import com.hungry.customer.ordersummary.repository.TaxRepository;
import com.hungry.customer.ordersummary.viewmodel.OrderSummaryViewModel;
import com.hungry.customer.R;
import com.hungry.customer.databinding.FragmentOrderSummaryBinding;
import com.hungry.customer.promocode.PromocodeActivity;
import com.hungry.customer.promocode.model.PromoCode;
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
       if(((HomePage) getActivity()).taxResultMutableLiveData.getValue() !=null) {
           ((HomePage) getActivity()).taxResultMutableLiveData = new TaxRepository().getTax(new PrefManager(getContext()).getUserDetails().getCITY_MASTER_ID()+"");
           ((HomePage) getActivity()).taxResultMutableLiveData.observeForever(new Observer<TaxResult>() {
               @Override
               public void onChanged(TaxResult taxResult) {
                   orderSummaryViewModel.setTaxResult(((HomePage) getActivity()).taxResultMutableLiveData.getValue());
               }
           });
       } else {
           orderSummaryViewModel.setTaxResult(((HomePage) getActivity()).taxResultMutableLiveData.getValue());
       }
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
                    Toast.makeText(getContext(), "error1", Toast.LENGTH_SHORT).show();
                }
            }
        });

        OrderSummaryAdapter ordeSummaryAdapter = new OrderSummaryAdapter(((HomePage)getActivity()).cart, getActivity());

        ordeSummaryAdapter.setCartListner(() -> {
            try {
                orderSummaryViewModel.setArrayMenu(((HomePage) getActivity()).cart);
            } catch (Exception e){

            }
        });
        binding.setAdapter(ordeSummaryAdapter);
        binding.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Address2.class);
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
        if(requestCode == REQUESTFORADDRESS  && resultCode == RESULT_OK) {
            String coins= data.getStringExtra("result");
            Gson gson = new Gson();
            AddressResult addressResult = gson.fromJson(coins, AddressResult.class);
            orderSummaryViewModel.addressResultMutableLiveData.setValue(addressResult);
        }
        if(requestCode == REQUESTPROMOCODE  && resultCode == RESULT_OK){
            String coins = data.getStringExtra("result");
            Gson gson = new Gson();
            PromoCode promoCode = gson.fromJson(coins, PromoCode.class);
            Log.d("","");
            orderSummaryViewModel.promoCodeMutableLiveData.setValue(promoCode);
            orderSummaryViewModel.calculateTotal();
        }


    }
}
