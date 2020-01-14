package com.example.hungry.hotel_detail.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hungry.OrderSummary.activity.OrderSummary;
import com.example.hungry.R;
import java.util.ArrayList;

public class Hotel_deteail_adapter extends RecyclerView.Adapter<Hotel_deteail_adapter.MyViewHolder> {

    ArrayList personNames;
    ArrayList personImages;
    Context context;

    public Hotel_deteail_adapter(Context context, ArrayList personNames, ArrayList personImages) {
        this.context = context;
        this.personNames = personNames;
        this.personImages = personImages;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.hotel_detail_list, parent, false);
        MyViewHolder vh = new MyViewHolder(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder( MyViewHolder holder, int position) {

      //  holder.image.setImageResource(Integer.parseInt(personImages.get(position).toString()));
        // implement setOnClickListener event on item view.
        holder.btn_add_to_card.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // open another activity on item click
                Intent intent = new Intent(context, OrderSummary.class);
                context.startActivity(intent); // start Intent
            }
        });
    }


    @Override
    public int getItemCount() {
        return personNames.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        // init the item view's
        TextView name;
        Button btn_add_to_card;
        public MyViewHolder(View itemView) {
            super(itemView);
            // get the reference of item view's
          //  name = (TextView) itemView.findViewById(R.id.name);
           // image = (ImageView) itemView.findViewById(R.id.image);
            btn_add_to_card=(Button)itemView.findViewById(R.id.btn_add_to_card);
        }
    }
}