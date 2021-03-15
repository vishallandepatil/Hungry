package com.hungry.customer.hotel.adapter;

import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hungry.customer.HomePage;
import com.hungry.customer.R;
import com.hungry.customer.databinding.HotelDetailListBinding;
import com.hungry.customer.databinding.SearchResultTmenItemBinding;
import com.hungry.customer.hotel.CartListner;
import com.hungry.customer.hotel.model.Menu;
import com.hungry.customer.util.OnItemClickListner;

import java.util.ArrayList;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.library.baseAdapters.BR;
import androidx.recyclerview.widget.RecyclerView;

public class SearchMenuAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    ArrayList<Menu> menus;
    HomePage context;


    public void setOnItemClicklistner(OnItemClickListner onItemClicklistner) {
        OnItemClicklistner = onItemClicklistner;
    }

    OnItemClickListner OnItemClicklistner;

    public void setListner(CartListner listner) {
        this.listner = listner;
    }

    CartListner listner;
    public SearchMenuAdapter(HomePage context, ArrayList<Menu> menus) {
        this.context = context;
        this.menus = menus;

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        SearchResultTmenItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.search_result_tmen_item, parent, false);
            MyViewHolder vh = new MyViewHolder(binding);
            return vh;

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder2, int position) {

        if(holder2 instanceof  MyViewHolder) {

            MyViewHolder holder= (MyViewHolder) holder2;
            final Menu dataModel = menus.get(position);
            holder.binding.hotelname.setVisibility(View.VISIBLE);
            holder.binding.btnAddToCard.setVisibility(View.GONE);

            holder.binding.orizenalamount.setPaintFlags(holder.binding.orizenalamount.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.bind(dataModel);
            holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(OnItemClicklistner!=null){
                    OnItemClicklistner.onItemClick(position);
                    }
                }
            });

        }


    }


    @Override
    public int getItemCount() {
            return menus!=null?menus.size():0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        SearchResultTmenItemBinding binding;

        public MyViewHolder(SearchResultTmenItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }

        public void bind(Object obj) {
            binding.setVariable(BR.menu, obj);
            binding.executePendingBindings();
        }
    }


}