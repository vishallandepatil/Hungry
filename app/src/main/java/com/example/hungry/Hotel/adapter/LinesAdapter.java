package com.example.hungry.Hotel.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import com.example.hungry.R;
import com.example.hungry.hotel_detail.activity.Hotel_Detail_activity;
import com.example.hungry.hotel_detail.model.HomePageHotelList_Model;

import java.util.ArrayList;

public class LinesAdapter  extends RecyclerView.Adapter<LinesAdapter.MyViewHolder> {
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

                Intent i = new Intent(context, Hotel_Detail_activity.class);
                context.startActivity(i);

            }
        });


    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView hotel_name, contry, address, time;
        private ImageView ivEdit, add;
        private EditText etQtyShipped, etReceivedQty, etQtyToShip, etReceive, etReason;

        public MyViewHolder(View view) {
            super(view);
            hotel_name = (TextView) view.findViewById(R.id.home_name);
            contry = (TextView) view.findViewById(R.id.id_type);
            address = (TextView) view.findViewById(R.id.address);
            time = (TextView) view.findViewById(R.id.id_date_time);


        }
    }
}
