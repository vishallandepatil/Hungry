package com.example.hungry.myorder.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hungry.R;
import com.example.hungry.databinding.MyorderListItemBinding;

import com.example.hungry.myorder.model.Order;
import com.example.hungry.myorder.model.OrderItem;

import java.util.ArrayList;

public class MyOrderAdapter extends RecyclerView.Adapter<MyOrderAdapter.MyViewHolder> {
    ArrayList<Order> orderListModels;
    Context context;

    public MyOrderAdapter(ArrayList<Order> orderlist, Context context) {
        this.orderListModels = orderlist;
        this.context = context;
    }

    @Override
    public MyOrderAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        MyorderListItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.myorder_list_item, parent, false);
        MyViewHolder vh = new MyViewHolder(binding);
        return  vh;
    }

    @Override
    public int getItemCount() {
        return orderListModels == null ? 0 : orderListModels.size();
    }

    @Override
    public void onBindViewHolder(final MyOrderAdapter.MyViewHolder holder, final int position) {

        Order order = orderListModels.get(position);
        holder.bind(order);
        LayoutInflater inflater = LayoutInflater.from(context);
        holder.binding.itemlist.removeAllViews();
        for (OrderItem item: order.items) {
            View imageLayout = inflater.inflate(R.layout.itemlist, (ViewGroup) holder.itemView.getRootView(), false);
            holder.binding.itemlist.addView(imageLayout,0);
        }


    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        private MyorderListItemBinding binding;
        public MyViewHolder(MyorderListItemBinding binding) {
            super(binding.getRoot());
            this.binding=binding;

        }
        public void bind(Object obj) {
            binding.setVariable(com.example.hungry.BR.order, obj);
            binding.executePendingBindings();
        }
    }
}
