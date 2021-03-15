package com.hungry.customer;

import android.os.Bundle;


import com.hungry.customer.app.PrefManager;
import com.hungry.customer.myorder.fragment.TrackOrderFragment;
import com.hungry.customer.ordersummary.OrderSummary;
import com.hungry.customer.dish.fragment.DishFragment;
import com.hungry.customer.hotel.fragment.HotelDetail;
import com.hungry.customer.hotel.fragment.HotelFragment;
import com.hungry.customer.hotel.model.Menu;
import com.hungry.customer.myorder.fragment.OrderFragment;
import com.hungry.customer.ordersummary.model.TaxResult;
import com.hungry.customer.ordersummary.repository.TaxRepository;
import com.hungry.customer.profile.fragment.ProfileFragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;


import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

public class HomePage extends AppCompatActivity {
    public ArrayList<Menu> cart =new ArrayList<>();
    public MutableLiveData<TaxResult> taxResultMutableLiveData;

   /* private int[] myImageList = new int[]{R.drawable.shop, R.drawable.shop
            ,R.drawable.shop,R.drawable.shop};*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        final BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        final FragmentManager manager = getSupportFragmentManager();
        manager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                //  FragmentDesignDetails
                Fragment fragment = manager.findFragmentById(R.id.frame_container);
                int i = manager.getBackStackEntryCount();

                if (fragment instanceof HotelDetail ||fragment instanceof OrderSummary || fragment instanceof TrackOrderFragment) {
                    navView.setVisibility(View.GONE);
                } else {
                    navView.setVisibility(View.VISIBLE);
                }


            }
        });
        loadFragment(new HotelFragment());

        taxResultMutableLiveData=new TaxRepository().getTax(new PrefManager(this).getUserDetails().getCITY_MASTER_ID()+"");
        taxResultMutableLiveData.observeForever(new Observer<TaxResult>() {
            @Override
            public void onChanged(TaxResult taxResult) {
                if(taxResult==null){
                    new TaxRepository().getTax(new PrefManager(getApplicationContext()).getUserDetails().getCITY_MASTER_ID()+"");
                }

            }
        });

        //  imageModelArrayList = new ArrayList<>();
        //imageModelArrayList = populateList();

        //   init();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.home_frag:
                    fragment = new HotelFragment();
                    loadFragment(fragment);
                    
                    return true;
                case R.id.dish_frag:
                    fragment = new DishFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.order_frag:
                    fragment = new OrderFragment();
                    loadFragment(fragment);
                    return true;

                case R.id.profile_frag:
                    fragment = new ProfileFragment();
                    loadFragment(fragment);
                    return true;
            }
            return false;
        }
    };


    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }



    }