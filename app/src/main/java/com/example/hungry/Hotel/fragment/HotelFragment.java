package com.example.hungry.Hotel.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;


import android.os.Handler;


import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;


import com.example.hungry.Hotel.adapter.LinesAdapter;
import com.example.hungry.R;
import com.example.hungry.Hotel.adapter.SlidingImage_Adapter;
import com.example.hungry.hotel_detail.model.HomePageHotelList_Model;
import com.example.hungry.Hotel.model.ImageModel;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

// homefragment changes
public class HotelFragment extends Fragment {

    ArrayList personNames = new ArrayList<>(Arrays.asList("Person 1", "Person 2", "Person 3", "Person 4", "Person 5", "Person 6", "Person 7"));
    ArrayList<HomePageHotelList_Model> homelist = new ArrayList<>();

    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;

    private ArrayList<ImageModel> imageModelArrayList;

    private int[] myImageList = new int[]{R.drawable.shop, R.drawable.shop
            ,R.drawable.shop,R.drawable.shop};


    ImageView filter;
    AlertDialog alertDialog1;
    String[] listItems = {"Less price ","Max price ","Rating","Veg only","Non_veg only"};




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
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        RecyclerView rv_line = (RecyclerView) rootView.findViewById(R.id.rv_line);

        Toolbar toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        //setting the title
        setHasOptionsMenu(true);
        filter = (ImageView)rootView.findViewById(R.id.filter);
        mPager = (ViewPager)rootView.findViewById(R.id.pager);

        imageModelArrayList = new ArrayList<>();
        imageModelArrayList = imageList();

        PagerSlider();

        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                filter_list() ;

            }
        });

        homelist.add(new HomePageHotelList_Model(
                "Hotel Prasad",
                "Indian",
                "Aurangabad Road",
                "11: 55 AM to 11:00 PM"));
        homelist.add(new HomePageHotelList_Model(
                "Hotel Prasad",
                "America",
                "Aurangabad Road",
                "11: 55 AM to 11:00 PM"));


        LinesAdapter linesAdapter = new LinesAdapter(homelist, getContext());
        rv_line.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_line.setAdapter(linesAdapter);
        return rootView;
    }



    public void filter_list() {


        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Choose item");

        int checkedItem = 0; //this will checked the item when user open the dialog
        builder.setSingleChoiceItems(listItems, checkedItem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getContext(), "Position: " + which + " Value: " + listItems[which], Toast.LENGTH_LONG).show();
            }
        });

        builder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();


    }

    private void PagerSlider() {
        mPager.setAdapter(new SlidingImage_Adapter(getContext(),imageModelArrayList));
        //indicator.setViewPager(mPager);

        final float density = getResources().getDisplayMetrics().density;

//Set circle indicator radius
        //indicator.setRadius(5 * density);

        NUM_PAGES =imageModelArrayList.size();

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
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

    private ArrayList<ImageModel> imageList(){

        ArrayList<ImageModel> list = new ArrayList<>();

        for(int i = 0; i < 3; i++){
            ImageModel imageModel = new ImageModel();
            imageModel.setImage_drawable(myImageList[i]);
            list.add(imageModel);
        }

        return list;
    }




}