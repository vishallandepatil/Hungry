package com.hungry.customer.ordersummary.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.Observable;
import androidx.databinding.library.baseAdapters.BR;
import androidx.recyclerview.widget.RecyclerView;

import com.hungry.customer.R;
import com.hungry.customer.databinding.MyorderListItemBinding;
import com.hungry.customer.databinding.OrderSummaryListItemBinding;
import com.hungry.customer.hotel.CartListner;
import com.hungry.customer.hotel.model.Menu;

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
        if(menu.qtyOrder==0){
            menu.onAddQntity(null);
        }
        holder.binding.qnt.setText(menu.qtyOrder+"");
        holder.binding.remove.setOnClickListener(v -> {

            menus.remove(menu);
            notifyDataSetChanged();
            if (cartListner != null) {
                cartListner.onChange();
            }
            if (menus.size() == 0) {
                ((Activity) context).onBackPressed();
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
