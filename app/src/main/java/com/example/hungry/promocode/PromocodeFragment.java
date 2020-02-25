package com.example.hungry.promocode;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hungry.R;
import com.example.hungry.app.PrefManager;
import com.example.hungry.databinding.PromocodeFragmentBinding;
import com.example.hungry.ordersummary.OrderSummary;
import com.example.hungry.promocode.model.PromoCodeResult;
import com.example.hungry.promocode.adapters.PromoCodeAdapter;
import com.example.hungry.promocode.viewmodel.PromocodeViewModel;

public class PromocodeFragment extends Fragment {
    final static String  MENU="menu",HOTEL="Hotel";
    String hotelId;
    private PromocodeViewModel mViewModel;
    public static PromocodeFragment newInstance(String hotelId) {
        PromocodeFragment fragment = new PromocodeFragment();
        Bundle args = new Bundle();

        args.putString(HOTEL,hotelId);

        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        hotelId = args.getString(HOTEL);



    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final PromocodeFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.promocode_fragment, container, false);
        mViewModel = ViewModelProviders.of(this).get(PromocodeViewModel.class);
        ((AppCompatActivity)getActivity()).setSupportActionBar(binding.toolbar);

        mViewModel.loadPromoCode(null,hotelId,new PrefManager(getContext()).getUserDetails().getCM_ID()+"");
        binding.setPromocodemodel(mViewModel);
        binding.setLifecycleOwner(this);
        mViewModel.promoCodeResultMutableLiveData.observeForever(new Observer<PromoCodeResult>() {
            @Override
            public void onChanged(PromoCodeResult promoCodeResult) {
                binding.setAdapter(new PromoCodeAdapter(promoCodeResult.result,getContext()));
            }
        });
        return binding.getRoot();

    }



}
