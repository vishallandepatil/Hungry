package com.hungry.customer.promocode.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hungry.customer.BR;
import com.hungry.customer.R;
import com.hungry.customer.databinding.PromocodeRowBinding;
import com.hungry.customer.promocode.model.PromoCode;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;


public class PromoCodeAdapter extends RecyclerView.Adapter<PromoCodeAdapter.MyViewHolder> {
    ArrayList<PromoCode> promoCodes;
    Context context;
    public MutableLiveData<PromoCode> selectedpromocode = new  MutableLiveData();

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

        final PromoCode promoCode = promoCodes.get(position);
        holder.bind(promoCode);
//        TextView code =holder.binding.getRoot().findViewById(R.id.code);
//        code.setText(promoCode.code);
        holder.binding.btnAddToCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedpromocode.setValue(promoCode);
            }
        });

    }
    @BindingAdapter("bindServerDate")
    public static void bindServerDate(@NonNull TextView textView, String date) {
       final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        try {
            Date dateobj = dateFormat.parse(date);
            String datestr = DateFormat.getDateInstance().format(dateobj);

            textView.setText(datestr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        /*Parse string data and set it in another format for your textView*/

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private PromocodeRowBinding binding;
        public MyViewHolder(PromocodeRowBinding binding) {
            super(binding.getRoot());
            this.binding=binding;

        }
        public void bind(Object obj) {
            binding.setVariable(BR.promocode, obj);
            binding.executePendingBindings();
        }
    }
}
