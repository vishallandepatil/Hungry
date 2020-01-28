package com.example.hungry.hotel.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.library.baseAdapters.BR;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hungry.HomePage;
import com.example.hungry.R;
import com.example.hungry.databinding.HotelDetailListBinding;
import com.example.hungry.generated.callback.OnClickListener;
import com.example.hungry.hotel.model.Menu;

import java.util.ArrayList;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MyViewHolder> {

    ArrayList<Menu> menus;
    HomePage context;


    public MenuAdapter(HomePage context, ArrayList<Menu> menus) {
        this.context = context;
        this.menus = menus;


    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        HotelDetailListBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.hotel_detail_list, parent, false);

        MyViewHolder vh = new MyViewHolder(binding); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Menu dataModel = menus.get(position);
        holder.bind(dataModel);
        for (Menu menu : context.cart) {
            if (menu.menuId == dataModel.menuId) {
                dataModel.isAddedToCart = true;

            }

        }
        holder.binding.btnAddToCard.setBackground(context.getDrawable(dataModel.isAddedToCart ? R.drawable.addedtocart : R.drawable.addtocart_border));


        holder.binding.btnAddToCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!dataModel.isAddedToCart) {
                    boolean isAdded = false;
                    for (Menu menu : context.cart) {
                        if (menu.menuId == dataModel.menuId) {
                            isAdded = true;
                        }

                    }
                    if (!isAdded) {
                        dataModel.isAddedToCart = true;
                        context.cart.add(dataModel);
                        notifyDataSetChanged();
                    }

                }
            }
        });


    }


    @Override
    public int getItemCount() {
        return menus.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        HotelDetailListBinding binding;

        public MyViewHolder(HotelDetailListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }

        public void bind(Object obj) {
            binding.setVariable(BR.menu, obj);
            binding.executePendingBindings();
        }
    }
}