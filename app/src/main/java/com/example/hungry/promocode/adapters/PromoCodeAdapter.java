package com.example.hungry.promocode.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.hungry.BR;
import com.example.hungry.R;
import com.example.hungry.databinding.PromocodeRowBinding;
import com.example.hungry.promocode.model.PromoCode;

import java.util.ArrayList;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;


public class PromoCodeAdapter extends RecyclerView.Adapter<PromoCodeAdapter.MyViewHolder> {
    ArrayList<PromoCode> promoCodes;
    Context context;

    public PromoCodeAdapter(ArrayList<PromoCode> orderlist, Context context) {
        this.promoCodes = orderlist;
        this.context = context;
    }

    @Override
    public PromoCodeAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        PromocodeRowBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.promocode_row, parent, false);
        PromoCodeAdapter.MyViewHolder vh = new PromoCodeAdapter.MyViewHolder(binding);
        return  vh;
    }

    @Override
    public int getItemCount() {
        return promoCodes == null ? 0 : promoCodes.size();
    }

    @Override
    public void onBindViewHolder(final PromoCodeAdapter.MyViewHolder holder, final int position) {

        PromoCode promoCode = promoCodes.get(position);
        holder.bind(promoCode);



    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        private PromocodeRowBinding binding;
        public MyViewHolder(PromocodeRowBinding binding) {
            super(binding.getRoot());
            this.binding=binding;

        }
        public void bind(Object obj) {
            binding.setVariable(BR.promocodemodel, obj);
            binding.executePendingBindings();
        }
    }
}
