package com.example.hungry.ordersummary.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.Observable;
import androidx.databinding.library.baseAdapters.BR;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hungry.R;
import com.example.hungry.databinding.MyorderListItemBinding;
import com.example.hungry.databinding.OrderSummaryListItemBinding;
import com.example.hungry.hotel.CartListner;
import com.example.hungry.hotel.model.Menu;

import java.util.ArrayList;

public class OrderSummaryAdapter extends RecyclerView.Adapter<OrderSummaryAdapter.MyViewHolder> {
    public ArrayList<Menu> menus = new ArrayList<>();
    Context context;

    public OrderSummaryAdapter(ArrayList<Menu> menus, Context context) {
        this.menus = menus;
        this.context = context;
    }

    public void setCartListner(CartListner cartListner) {
        this.cartListner = cartListner;
    }

    CartListner cartListner;

    @Override
    public OrderSummaryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        OrderSummaryListItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.order_summary_list_item, parent, false);
        return new OrderSummaryAdapter.MyViewHolder(binding);
    }

    @Override
    public int getItemCount() {
        return menus == null ? 0 : menus.size();
    }

    @Override
    public void onBindViewHolder(final OrderSummaryAdapter.MyViewHolder holder, final int position) {
        final Menu menu = menus.get(position);
        holder.setBinding(menu);
        holder.binding.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                menus.remove(menu);
                notifyDataSetChanged();
                if (cartListner != null) {
                    cartListner.onChange();
                }
                if (menus.size() == 0) {
                    ((Activity) context).onBackPressed();
                }

            }
        });
        menu.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                if (cartListner != null) {
                    cartListner.onChange();
                }
            }
        });

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        OrderSummaryListItemBinding binding;

        public MyViewHolder(OrderSummaryListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;


        }

        public void setBinding(Object obj) {
            binding.setVariable(BR.menu, obj);
            binding.executePendingBindings();
        }
    }
}
