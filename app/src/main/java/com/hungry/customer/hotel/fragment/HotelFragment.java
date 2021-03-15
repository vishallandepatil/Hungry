package com.hungry.customer.hotel.fragment;

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


import com.hungry.customer.HomePage;
import com.hungry.customer.app.PrefManager;
import com.hungry.customer.hotel.adapter.HotelsAdapter;

import com.hungry.customer.hotel.adapter.SearchMenuAdapter;
import com.hungry.customer.hotel.adapter.SlidingImage_Adapter;
import com.hungry.customer.hotel.model.HotelModel;
import com.hungry.customer.hotel.model.HotelResult;
import com.hungry.customer.hotel.model.MenuResult;
import com.hungry.customer.hotel.model.SliderResult;
import com.hungry.customer.hotel.viewmodels.HomeViewModel;
import com.hungry.customer.R;
import com.hungry.customer.databinding.FragmentHomeBinding;
import com.hungry.customer.login.model.User;
import com.hungry.customer.util.OnItemClickListner;


import java.util.Timer;
import java.util.TimerTask;

// homefragment changes
public class HotelFragment extends Fragment {


    private static int currentPage = 0;
    private static int NUM_PAGES = 0;

    private   String veg_only=null, name=null,ratting=null,  limit=null,  satrt=null;

    String[] listItems = {"Rating","Veg only","Nonveg only","None"};
    int checkedItem = 3;
    User user;

    FragmentHomeBinding binding;
    HomeViewModel homeFragmnetViewModel;

    public HotelFragment() {

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
    public void onResume() {
        super.onResume();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        homeFragmnetViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        binding.setLifecycleOwner(this);
        try {
            ((HomePage) getActivity()).cart.clear();
        } catch (Exception e){

        }

        binding.setHomeViewModel(homeFragmnetViewModel);
//        ((AppCompatActivity)getActivity()).setSupportActionBar(binding.toolbar);
        user= new PrefManager(getContext()).getUserDetails();
        homeFragmnetViewModel.loadSliderImages(user.getCITY_MASTER_ID()+"");
        homeFragmnetViewModel.sliderResultMutableLiveData.observeForever(new Observer<SliderResult>() {
            @Override
            public void onChanged(SliderResult sliderResult) {
                try {
                    PagerSlider(sliderResult);
                } catch (Exception e){

                }
            }
        });
        homeFragmnetViewModel.loadHotels(user.getCITY_MASTER_ID()+"", null, null, null, null, null);
        homeFragmnetViewModel.hotelResultMutableLiveData.observeForever(new Observer<HotelResult>() {

            @Override
            public void onChanged(HotelResult hotelResult) {
                HotelsAdapter linesAdapter = new HotelsAdapter(hotelResult.result,hotelResult.trendings, (AppCompatActivity ) getActivity());
                binding.rvLine.setLayoutManager(new LinearLayoutManager(getContext()));
                binding.rvLine.setAdapter(linesAdapter);
                if(hotelResult.status!=200){
                    binding.llNoRecord.setVisibility(View.VISIBLE);
                    binding.rvLine.setVisibility(View.GONE);
                }
            }
        });

        homeFragmnetViewModel.menuResultMutableLiveData.observeForever(new Observer<MenuResult>() {
            @Override
            public void onChanged(MenuResult menuResult) {
                SearchMenuAdapter linesAdapter= new SearchMenuAdapter((HomePage) getActivity(),menuResult.result);
                binding.rvSearchResult.setLayoutManager(new LinearLayoutManager(getContext()));
                binding.rvSearchResult.setAdapter(linesAdapter);
                if(menuResult.status!=200){
                    binding.llNoRecord.setVisibility(View.VISIBLE);
                    binding.rvLine.setVisibility(View.GONE);
                }
                linesAdapter.setOnItemClicklistner(position -> {

                    try {
                        for (HotelModel hotelModel : homeFragmnetViewModel.hotelResultMutableLiveData.getValue().result) {
                            if (menuResult.result.get(position).hotelID.equalsIgnoreCase(hotelModel.getId() + "")) {
                                HotelDetail hotel = HotelDetail.getInstance(hotelModel, menuResult.result.get(position),"Your Search Result");

                                ((AppCompatActivity) getActivity()).getSupportFragmentManager()
                                        .beginTransaction()
                                        .replace(R.id.frame_container, hotel).addToBackStack(null)
                                        .commit();
                                binding.simpleSearchView.setQuery("",true);
                            }
                        }
                    } catch (Exception e){

                    }
                });

            }
        });

        binding.simpleSearchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){

                   // binding.pager.setVisibility(View.GONE);


                } else {

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
                if(newText!=null && newText.length()>0) {
                   // name=newText;
                    binding.pagerparent.setVisibility(View.GONE);
                    binding.rvLine.setVisibility(View.GONE);
                    binding.rvSearchResult.setVisibility(View.VISIBLE);
                    binding.llNoRecord.setVisibility(View.GONE);
                    homeFragmnetViewModel.loadMenu(newText);
                } else {
                    //name=null;
                    binding.pagerparent.setVisibility(View.VISIBLE);
                    binding.rvSearchResult.setVisibility(View.GONE);
                    binding.rvLine.setVisibility(View.VISIBLE);
                    binding.llNoRecord.setVisibility(View.GONE);
                   //homeFragmnetViewModel.loadHotels(user.getCITY_MASTER_ID()+"",veg_only,name,ratting,limit,satrt);

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
        builder.setTitle("Choose filter");
        builder.setSingleChoiceItems(listItems, checkedItem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                //Toast.makeText(getContext(), "Position: " + which + " Value: " + listItems[which], Toast.LENGTH_LONG).show();
                veg_only=null;
                ratting= null;
                checkedItem=which;
                switch (which) {
                    case 0:
                        ratting = "desc";
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
        final float density = getResources().getDisplayMetrics().density;

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