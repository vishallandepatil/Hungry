package com.hungry.customer.promocode;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.hungry.customer.R;
import com.hungry.customer.app.PrefManager;
import com.hungry.customer.databinding.ActivityAddressBinding;
import com.hungry.customer.databinding.PromocodeFragmentBinding;
import com.hungry.customer.promocode.model.PromoCode;
import com.hungry.customer.promocode.model.PromoCodeResult;
import com.hungry.customer.promocode.adapters.PromoCodeAdapter;
import com.hungry.customer.promocode.viewmodel.PromocodeViewModel;
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
        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

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
