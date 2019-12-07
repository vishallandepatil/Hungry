package com.example.hungry.fragment;

import android.app.AlertDialog;
import android.content.Context;
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
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.hungry.R;
import com.example.hungry.adapter.SlidingImage_Adapter;
import com.example.hungry.model.HomePageHotelList_Model;
import com.example.hungry.model.ImageModel;
import com.google.android.material.bottomappbar.BottomAppBar;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;


public class HomeFragment extends Fragment {

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




    public HomeFragment() {
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
        imageModelArrayList = populateList();

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


        LinesAdapter   linesAdapter = new LinesAdapter(homelist, getContext());
        rv_line.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_line.setAdapter(linesAdapter);
        return rootView;
    }


    private class LinesAdapter extends RecyclerView.Adapter<LinesAdapter.MyViewHolder> {
        ArrayList<HomePageHotelList_Model> homelist;
        Context context;

        public LinesAdapter(ArrayList<HomePageHotelList_Model> homelist, Context context) {
            this.homelist = homelist;
            this.context = context;
        }

        @Override
        public LinesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_list_item, parent, false);

            return new LinesAdapter.MyViewHolder(itemView);
        }

        @Override
        public int getItemCount() {
            return homelist == null ? 0 : homelist.size();
        }

        @Override
        public void onBindViewHolder(final LinesAdapter.MyViewHolder holder, final int position) {

            HomePageHotelList_Model linesModel = homelist.get(position);

            holder.hotel_name.setText(linesModel.getHotel_name());
            holder.contry.setText(linesModel.getContry());
            holder.address.setText(linesModel.getAddress());
            holder.time.setText(linesModel.getDate_time());





            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Index = position;
                    //lineDetailDialog(linesModels.get(position));

                }
            });






        }

        public class MyViewHolder extends RecyclerView.ViewHolder {

            private TextView hotel_name, contry, address,time;
            private ImageView ivEdit,add;
            private EditText etQtyShipped,etReceivedQty,etQtyToShip,etReceive,etReason;

            public MyViewHolder(View view) {
                super(view);
                hotel_name = (TextView) view.findViewById(R.id.home_name);
                contry = (TextView)view.findViewById(R.id.id_type);
                address =(TextView) view.findViewById(R.id.address);
                time =(TextView) view.findViewById(R.id.id_date_time);





            }
        }
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

        // Pager listener over indicator
//        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });


    }

    private ArrayList<ImageModel> populateList(){

        ArrayList<ImageModel> list = new ArrayList<>();

        for(int i = 0; i < 3; i++){
            ImageModel imageModel = new ImageModel();
            imageModel.setImage_drawable(myImageList[i]);
            list.add(imageModel);
        }

        return list;
    }




}