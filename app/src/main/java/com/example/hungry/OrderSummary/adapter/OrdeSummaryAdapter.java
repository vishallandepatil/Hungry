package com.example.hungry.OrderSummary.adapter;

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
import com.example.hungry.OrderSummary.model.Order_summary_Model;
import com.example.hungry.hotel_detail.model.HomePageHotelList_Model;

import java.util.ArrayList;

public class OrdeSummaryAdapter extends RecyclerView.Adapter<OrdeSummaryAdapter.MyViewHolder> {
    ArrayList<Order_summary_Model> ordersummary;
    Context context;

    public OrdeSummaryAdapter(ArrayList<Order_summary_Model> ordersummary, Context context) {
        this.ordersummary = ordersummary;
        this.context = context;
    }

    @Override
    public OrdeSummaryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_summary_list_item, parent, false);

        return new OrdeSummaryAdapter.MyViewHolder(itemView);
    }

    @Override
    public int getItemCount() {
        return ordersummary == null ? 0 : ordersummary.size();
    }

    @Override
    public void onBindViewHolder(final OrdeSummaryAdapter.MyViewHolder holder, final int position) {




        Order_summary_Model linesModel = ordersummary.get(position);

        holder.hotel_name.setText(linesModel.getHotel_name());
        holder.contry.setText(linesModel.getContry());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              /*  Intent i = new Intent(context, Hotel_Detail_activity.class);
                context.startActivity(i);*/

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
