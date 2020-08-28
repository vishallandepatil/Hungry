package com.example.hungry.promocode;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hungry.R;
import com.example.hungry.app.PrefManager;
import com.example.hungry.databinding.ActivityAddressBinding;
import com.example.hungry.databinding.PromocodeFragmentBinding;
import com.example.hungry.ordersummary.OrderSummary;
import com.example.hungry.promocode.model.PromoCode;
import com.example.hungry.promocode.model.PromoCodeResult;
import com.example.hungry.promocode.adapters.PromoCodeAdapter;
import com.example.hungry.promocode.viewmodel.PromocodeViewModel;
import com.google.gson.Gson;

public class PromocodeActivity extends AppCompatActivity {
    final static String  MENU="menu",HOTEL="Hotel";
    String hotelId;
    private PromocodeViewModel mViewModel;
    PromoCodeAdapter promoCodeAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        hotelId = getIntent().getStringExtra(HOTEL);

        final PromocodeFragmentBinding binding = DataBindingUtil.setContentView(this, R.layout.promocode_fragment);

        mViewModel = ViewModelProviders.of(this).get(PromocodeViewModel.class);
        setSupportActionBar(binding.toolbar);

        mViewModel.loadPromoCode(null,hotelId,new PrefManager(this).getUserDetails().getCM_ID()+"");
        binding.setPromocodemodel(mViewModel);
        binding.setLifecycleOwner(this);
        mViewModel.promoCodeResultMutableLiveData.observeForever(new Observer<PromoCodeResult>() {
            @Override
            public void onChanged(PromoCodeResult promoCodeResult) {
                PromoCodeAdapter promoCodeAdapter= new PromoCodeAdapter(promoCodeResult.result,PromocodeActivity.this);
                binding.setAdapter(promoCodeAdapter);
                promoCodeAdapter.selectedpromocode.observeForever(new Observer<PromoCode>() {
                    @Override
                    public void onChanged(PromoCode promoCode) {

                        Gson gson = new Gson();

                        String json = gson.toJson(promoCode);
                        Intent returnIntent = new Intent();
                        returnIntent.putExtra("result",json);
                        setResult(Activity.RESULT_OK,returnIntent);
                        finish();

                    }
                });
            }
        });



    }





}
