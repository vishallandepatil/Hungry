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
import com.example.hungry.ordersummary.OrderSummary;
import com.example.hungry.R;
import com.example.hungry.databinding.FragmentHotelDetailBinding;
import com.example.hungry.hotel.CartListner;
import com.example.hungry.hotel.model.Menu;
import com.example.hungry.hotel.model.MenuResult;
import com.example.hungry.hotel.viewmodels.HotelDetailViewModel;
import com.example.hungry.hotel.adapter.MenuAdapter;
import com.example.hungry.hotel.model.HotelModel;
import com.example.hungry.util.GridSpacingItemDecoration;

import java.util.ArrayList;


public class HotelDetail extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    HotelModel hotelModel;
    HotelDetailViewModel hotelDetailViewModel;
    FragmentHotelDetailBinding binding;
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
    public void onResume() {
        super.onResume();

            try {
                String size = ((HomePage) getActivity()).cart.size()+"";
                binding.ibcartCount.setText(size+"");
                binding.itemcount.setText("Items: "+((HomePage) getActivity()).cart.size());
                double total=0;
                for(Menu menu : ((HomePage) getActivity()).cart){
                    total+= menu.amount;
                }
                binding.total.setText("Total: Rs. "+total);
            } catch (Exception e){

            }



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_hotel_detail, container, false);
        hotelDetailViewModel = ViewModelProviders.of(this).get(HotelDetailViewModel.class);
        hotelDetailViewModel.setMutaibleHotelModel(hotelModel);
        binding.setLifecycleOwner(this);
        binding.setHotelDetails(hotelDetailViewModel);
        hotelDetailViewModel.loadMenus(null);
        ((AppCompatActivity)getActivity()).setSupportActionBar(binding.toolbar);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        binding.rvLine.setLayoutManager(gridLayoutManager);
        binding.rvLine.addItemDecoration(new GridSpacingItemDecoration(2,  /*getResources().getDimensionPixelSize(R.dimen.grid_margin)*/6,false));

        // set LayoutManager to RecyclerView
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
                        binding.itemcount.setText("Items: "+((HomePage) getActivity()).cart.size());
                        double total=0;
                        for(Menu menu : ((HomePage) getActivity()).cart){
                            total+= menu.amount;
                        }
                        binding.total.setText("Total: Rs. "+total);
                    }
                });
            }
        });
        String size = ((HomePage) getActivity()).cart.size()+"";
        binding.ibcartCount.setText(size+"");
        binding.ibcartCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((HomePage) getActivity()).cart.size() > 0) {
                    OrderSummary fragment = new OrderSummary();
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.frame_container, fragment).addToBackStack(null)
                            .commit();
                }
            }
        });
        binding.ibcartCount1.setText(size+"");
        binding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((HomePage) getActivity()).cart.size() > 0) {
                    OrderSummary fragment = new OrderSummary();
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.frame_container, fragment).addToBackStack(null)
                            .commit();
                }
            }
        });
        return  binding.getRoot();
    }




}
