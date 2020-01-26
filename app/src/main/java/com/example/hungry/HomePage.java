package com.example.hungry;

import android.os.Bundle;


import com.example.hungry.dish.fragment.DishFragment;
import com.example.hungry.hotel.fragment.HotelFragment;
import com.example.hungry.myOrder.fragment.OrderFragment;
import com.example.hungry.profile.fragment.ProfileFragment;
import com.example.hungry.hotel.model.ImageModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;


import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;

public class HomePage extends AppCompatActivity {
    private TextView mTextMessage;

    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private ArrayList<ImageModel> imageModelArrayList;

   /* private int[] myImageList = new int[]{R.drawable.shop, R.drawable.shop
            ,R.drawable.shop,R.drawable.shop};*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

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