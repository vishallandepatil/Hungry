package com.example.hungry.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hungry.R;
import com.example.hungry.model.HomePageHotelList_Model;

import java.util.ArrayList;
import java.util.Arrays;


public class HomeFragment extends Fragment {

    ArrayList personNames = new ArrayList<>(Arrays.asList("Person 1", "Person 2", "Person 3", "Person 4", "Person 5", "Person 6", "Person 7"));
    ArrayList<HomePageHotelList_Model> homelist = new ArrayList<>();


    public HomeFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        // 1. get a reference to recyclerView

       RecyclerView rv_line = (RecyclerView) rootView.findViewById(R.id.rv_line);

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
                contry = (TextView)view.findViewById(R.id.id_contry);
                address =(TextView) view.findViewById(R.id.address);
                time =(TextView) view.findViewById(R.id.id_date_time);





            }
        }
    }


}