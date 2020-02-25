package com.example.hungry.hotel.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;


import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;


import com.example.hungry.app.PrefManager;
import com.example.hungry.hotel.adapter.HotelsAdapter;

import com.example.hungry.hotel.adapter.SlidingImage_Adapter;
import com.example.hungry.hotel.model.HotelResult;
import com.example.hungry.hotel.model.SliderResult;
import com.example.hungry.hotel.viewmodels.HomeViewModel;
import com.example.hungry.R;
import com.example.hungry.databinding.FragmentHomeBinding;
import com.example.hungry.login.model.User;


import java.util.Timer;
import java.util.TimerTask;

// homefragment changes
public class HotelFragment extends Fragment {


    private static int currentPage = 0;
    private static int NUM_PAGES = 0;

    private   String veg_only=null, name=null,  ratting=null,  limit=null,  satrt=null;

    String[] listItems = {"Rating","Veg only","Nonveg only","None"};
    int checkedItem = 0;
    User user;

    FragmentHomeBinding binding;
    HomeViewModel homeFragmnetViewModel;

    public HotelFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.hotel_action_menu, menu);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        homeFragmnetViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        binding.setLifecycleOwner(this);
        binding.setHomeViewModel(homeFragmnetViewModel);
        ((AppCompatActivity)getActivity()).setSupportActionBar(binding.toolbar);
        user= new PrefManager(getContext()).getUserDetails();
        homeFragmnetViewModel.loadSliderImages(user.getCITY_MASTER_ID()+"");
        homeFragmnetViewModel.sliderResultMutableLiveData.observeForever(new Observer<SliderResult>() {
            @Override
            public void onChanged(SliderResult sliderResult) {
                PagerSlider( sliderResult);
            }
        });
        homeFragmnetViewModel.loadHotels(user.getCITY_MASTER_ID()+"", null, null, null, null, null);
        homeFragmnetViewModel.hotelResultMutableLiveData.observeForever(new Observer<HotelResult>() {
            @Override
            public void onChanged(HotelResult hotelResult) {
                HotelsAdapter linesAdapter = new HotelsAdapter(hotelResult.result, (AppCompatActivity ) getActivity());
                binding.rvLine.setLayoutManager(new LinearLayoutManager(getContext()));
                binding.rvLine.setAdapter(linesAdapter);
                if(hotelResult.status!=200){
                    binding.llNoRecord.setVisibility(View.VISIBLE);
                    binding.rvLine.setVisibility(View.GONE);
                }
            }
        });
        binding.simpleSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {


                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(newText!=null && newText.length()>0){
                    name=newText;
                    homeFragmnetViewModel.loadHotels(user.getCITY_MASTER_ID()+"",veg_only,name,ratting,limit,satrt);

                } else {
                    name=null;
                    homeFragmnetViewModel.loadHotels(user.getCITY_MASTER_ID()+"",veg_only,name,ratting,limit,satrt);

                }
                return false;
            }
        });


//        PagerSlider();

        binding.filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                filter_list();

            }
        });
        binding.btnRefress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeFragmnetViewModel.loadHotels(user.getCITY_MASTER_ID()+"",veg_only,name,ratting,limit,satrt);

            }
        });




        return binding.getRoot();
    }



    public void filter_list() {


        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Choose item");

         //this will checked the item when user open the dialog
        builder.setSingleChoiceItems(listItems, checkedItem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                //Toast.makeText(getContext(), "Position: " + which + " Value: " + listItems[which], Toast.LENGTH_LONG).show();
                veg_only=null;
                ratting= null;
                checkedItem=which;
                switch (which) {
                    case 0:
                        ratting = "asc";
                        break;
                    case 1:
                        veg_only = "Y";
                        break;
                    case 2:
                        veg_only = "N";
                        break;
                    case 3:
                        veg_only = null;
                        ratting= null;
                        break;
                }
                homeFragmnetViewModel.loadHotels(user.getCITY_MASTER_ID()+"",veg_only,name,ratting,limit,satrt);
                dialog.dismiss();

            }
        });


        AlertDialog dialog = builder.create();
        dialog.show();


    }

    private void PagerSlider(SliderResult sliderResult) {
        binding.pager.setAdapter(new SlidingImage_Adapter(getContext(),sliderResult.result));
        //indicator.setViewPager(mPager);

        final float density = getResources().getDisplayMetrics().density;

//Set circle indicator radius
        //indicator.setRadius(5 * density);
        if (sliderResult.result!=null) {
            NUM_PAGES = sliderResult.result.size();
        } else {
            NUM_PAGES=0;
        }

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                binding.pager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 5000, 5000);




    }






}