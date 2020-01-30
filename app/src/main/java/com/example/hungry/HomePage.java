package com.example.hungry;

import android.os.Bundle;


import com.example.hungry.ordersummary.OrderSummary;
import com.example.hungry.dish.fragment.DishFragment;
import com.example.hungry.hotel.fragment.HotelDetail;
import com.example.hungry.hotel.fragment.HotelFragment;
import com.example.hungry.hotel.model.Menu;
import com.example.hungry.myorder.fragment.OrderFragment;
import com.example.hungry.profile.fragment.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;


import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

public class HomePage extends AppCompatActivity {
    public ArrayList<Menu> cart =new ArrayList<>();


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

                if (fragment instanceof HotelDetail ||fragment instanceof OrderSummary) {
                    navView.setVisibility(View.GONE);
                } else {
                    navView.setVisibility(View.VISIBLE);
                }


            }
        });
        loadFragment(new HotelFragment());


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