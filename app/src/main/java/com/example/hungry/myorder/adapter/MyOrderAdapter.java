package com.example.hungry.myorder.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hungry.HomePage;
import com.example.hungry.R;
import com.example.hungry.databinding.MyorderListItemBinding;

import com.example.hungry.myorder.fragment.TrackOrderFragment;
import com.example.hungry.myorder.model.Order;
import com.example.hungry.myorder.model.OrderItem;
import com.example.hungry.myorder.model.OrderResult;
import com.example.hungry.myorder.repository.OrderRepository;
import com.google.gson.Gson;

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

        final Order order = orderListModels.get(position);
        holder.bind(order);
        LayoutInflater inflater = LayoutInflater.from(context);
        holder.binding.itemlist.removeAllViews();
        for (OrderItem item: order.items) {
            View imageLayout = inflater.inflate(R.layout.itemlist, (ViewGroup) holder.itemView.getRootView(), false);
            TextView menuname  = (TextView)imageLayout.findViewById(R.id.menuname);
            TextView price = (TextView)imageLayout.findViewById(R.id.price);

            holder.binding.itemlist.addView(imageLayout,0);

            menuname.setText(item.getName());
            price.setText("Rs. "+item.getTotalPrice());
        }
        holder.binding.rateorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gson gson = new Gson();
                String json = gson.toJson(order);
                showCustomDialog(String.valueOf(order.id),position);


            }
        });
        holder.binding.trackorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gson gson = new Gson();
                String json = gson.toJson(order);


                ((HomePage)context).getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame_container, TrackOrderFragment.newInstance(json)).addToBackStack(null)
                        .commit();
            }
        });


    }
    private void showCustomDialog(final String orderid, final int position) {
        //before inflating the custom alert dialog layout, we will get the current activity viewgroup
        ViewGroup viewGroup = ((Activity)context).findViewById(android.R.id.content);

        //then we will inflate the custom alert dialog xml that we created
        final View dialogView = LayoutInflater.from(context).inflate(R.layout.rate_order, viewGroup, false);


        //Now we need an AlertDialog.Builder object
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);

        //finally creating the alert dialog and displaying it
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

        final RatingBar ratingBar = (RatingBar)dialogView.findViewById(R.id.ratingBar);
        Button rateorder = dialogView.findViewById(R.id.rateorder);
        final EditText feedback = dialogView.findViewById(R.id.feedback);
        rateorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!feedback.getText().toString().isEmpty()){
                    ;
                    OrderRepository orderRepository = new OrderRepository();
                    orderRepository.UpdateOrder(orderid, String.valueOf(ratingBar.getRating()),feedback.getText().toString()).observeForever(new Observer<OrderResult>() {
                        @Override
                        public void onChanged(OrderResult orderResult) {

                            alertDialog.hide();
                            if(orderResult.status==200){
                                orderListModels.set(position, orderResult.result.get(0));
                                notifyDataSetChanged();
                            }

                        }
                    });


                }
                else {
                    feedback.setError("Your Feed Back is valuable for us");
                }

            }
        });
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
            }
        });

        ratingBar.setNumStars(5);
        ratingBar.setMax(5);
        ratingBar.setStepSize((float) 0.1);
        ratingBar.setRating(4);
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
