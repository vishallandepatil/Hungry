package com.example.hungry.hotel.fragment;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hungry.HomePage;
import com.example.hungry.R;
import com.example.hungry.databinding.FragmentHotelDetailBinding;
import com.example.hungry.hotel.CartListner;
import com.example.hungry.hotel.model.MenuResult;
import com.example.hungry.hotel.viewmodels.HotelDetailViewModel;
import com.example.hungry.hotel.adapter.MenuAdapter;
import com.example.hungry.hotel.model.HotelModel;


public class HotelDetail extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    HotelModel hotelModel;
    HotelDetailViewModel hotelDetailViewModel;

    public HotelDetail() {
        // Required empty public constructor
    }
    public static HotelDetail getInstance(HotelModel linesModel){

        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM1, linesModel);
        HotelDetail fragment =new HotelDetail();
        fragment.setArguments(args);
        return fragment;

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            hotelModel = (HotelModel) getArguments().getParcelable(ARG_PARAM1);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final FragmentHotelDetailBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_hotel_detail, container, false);
        hotelDetailViewModel = ViewModelProviders.of(this).get(HotelDetailViewModel.class);
        hotelDetailViewModel.setMutaibleHotelModel(hotelModel);
        binding.setLifecycleOwner(this);
        binding.setHotelDetails(hotelDetailViewModel);
        hotelDetailViewModel.loadMenus(null);
        ((AppCompatActivity)getActivity()).setSupportActionBar(binding.toolbar);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        binding.rvLine.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView
        hotelDetailViewModel.menuResultMutableLiveData.observeForever(new Observer<MenuResult>() {
            @Override
            public void onChanged(MenuResult menuResult) {
                MenuAdapter hotel_deteail_adapter = new MenuAdapter((HomePage) getActivity(), menuResult.result);
                binding.setMenuAdapter(hotel_deteail_adapter);
                hotel_deteail_adapter.setListner(new CartListner() {
                    @Override
                    public void onChange() {
                        String size = ((HomePage) getActivity()).cart.size()+"";
                        binding.ibcartCount.setText(size+"");
                    }
                });
            }
        });
        String size = ((HomePage) getActivity()).cart.size()+"";
        binding.ibcartCount.setText(size+"");
        return  binding.getRoot();
    }




}
