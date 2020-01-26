package com.example.hungry.hotel.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hungry.R;

import com.example.hungry.hotel.fragment.HotelDetail;
import com.example.hungry.hotel_detail.model.HotelModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class HotelsAdapter extends RecyclerView.Adapter<HotelsAdapter.MyViewHolder> {
    ArrayList<HotelModel> homelist;
    AppCompatActivity context;

    public HotelsAdapter(ArrayList<HotelModel> homelist, AppCompatActivity context) {
        this.homelist = homelist;
        this.context = context;
    }

    @Override
    public HotelsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_list_item, parent, false);

        return new HotelsAdapter.MyViewHolder(itemView);
    }

    @Override
    public int getItemCount() {
        return homelist == null ? 0 : homelist.size();
    }

    @Override
    public void onBindViewHolder(final HotelsAdapter.MyViewHolder holder, final int position) {

        final HotelModel linesModel = homelist.get(position);

        holder.hotel_name.setText(linesModel.getName());
        holder.contry.setText(linesModel.getMealType());
        holder.address.setText(linesModel.getAddress());
        try {
            DateFormat dateFormat = new SimpleDateFormat("hh:mm a");
            holder.time.setText(dateFormat.parse(linesModel.getStarTtime())
                    + " To " + dateFormat.parse(linesModel.getEndTime()));
        }catch (Exception e){

        }
        holder.ratingBar.setNumStars(5);
        holder.ratingBar.setRating(Float.parseFloat(linesModel.getRatting()));
        holder.discout.setVisibility(View.GONE);
        Glide.with(context)
                .load(linesModel.getVegOnly().equalsIgnoreCase("Y")?R.drawable.vegimg:R.drawable.nonvegimg)
                .into(holder.ivVeg);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Intent i = new Intent(context, Hotel_Detail_activity.class);
//                context.startActivity(i);
                HotelDetail hotel = HotelDetail.getInstance(linesModel);
                ((AppCompatActivity)context).getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame_container, hotel)
                        .commit();

            }
        });


    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView hotel_name, contry, address, time,discout;
        private ImageView ivVeg, add;
        private RatingBar ratingBar;

        public MyViewHolder(View view) {
            super(view);
            hotel_name = (TextView) view.findViewById(R.id.home_name);
            contry = (TextView) view.findViewById(R.id.id_type);
            address = (TextView) view.findViewById(R.id.address);
            time = (TextView) view.findViewById(R.id.id_date_time);
            ratingBar = (RatingBar) view.findViewById(R.id.ratingBar);
            discout = (TextView) view.findViewById(R.id.discout);
            ivVeg = (ImageView) view.findViewById(R.id.ivVeg);



        }
    }
}
