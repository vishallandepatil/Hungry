package com.hungry.customer.hotel.adapter;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import com.bumptech.glide.signature.ObjectKey;
import com.hungry.customer.HomePage;
import com.hungry.customer.R;

import com.hungry.customer.hotel.fragment.HotelDetail;
import com.hungry.customer.hotel.model.HotelModel;
import com.hungry.customer.hotel.model.Menu;
import com.hungry.customer.util.GridSpacingItemDecoration;
import com.hungry.customer.util.HorizontalSpaceItemDecoration;
import com.hungry.customer.util.OnItemClickListner;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class HotelsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList<HotelModel> homelist;
    public ArrayList<Menu> trendings;
    AppCompatActivity context;
    private final int TENDING_VIEW=0;
    private final int HOTEL_VIEW=1;

    public HotelsAdapter(ArrayList<HotelModel> homelist,ArrayList<Menu> trendings, AppCompatActivity context) {
        this.homelist = homelist;
        this.trendings = trendings;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {

            if(position<=1 && trendings!=null && trendings.size()>0 ) {

                return  TENDING_VIEW;
            }

            return HOTEL_VIEW;



    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if(viewType == HOTEL_VIEW) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_list_item, parent, false);
            return new HotelsAdapter.MyViewHolder(itemView);
        } else {

            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.trending_menu_item_list, parent, false);
            return new HotelsAdapter.TendingMenuHolder(itemView);

        }
    }

    @Override
    public int getItemCount() {

        if(trendings!=null && trendings.size()>0){
            return homelist == null ? 0 : homelist.size()+2;
        }
        return homelist == null ? 0 : homelist.size();
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder myholder, final int position) {

        if(myholder instanceof HotelsAdapter.MyViewHolder) {
            HotelModel linesModel;
            if(trendings!=null && trendings.size()>0) {
                 linesModel = homelist.get((position-2));
            } else{
                 linesModel = homelist.get(position);
            }

            HotelsAdapter.MyViewHolder holder = (HotelsAdapter.MyViewHolder) myholder;
            holder.hotel_name.setText(linesModel.getName());
            holder.contry.setText(linesModel.getMealType());
            holder.address.setText(linesModel.getAddress());
            try {
                DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
                DateFormat dateFormat2 = new SimpleDateFormat("hh:mm aa");
                holder.time.setText(dateFormat2.format(dateFormat.parse(linesModel.getStarTtime())) + " To " + dateFormat2.format(dateFormat.parse(linesModel.getEndTime())));
                ;
                //)
            } catch (Exception e) {
                holder.time.setText("uu");
            }
            holder.ratingBar.setNumStars(5);
            holder.ratingBar.setRating(linesModel.getRatting());
            holder.discout.setVisibility(View.GONE);
            Glide.with(context)
                    .load(linesModel.getVegOnly().equalsIgnoreCase("Y") ? R.drawable.vegimg : R.drawable.nonvegimg)
                    .into(holder.ivVeg);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

//                Intent i = new Intent(context, Hotel_Detail_activity.class);
//                context.startActivity(i);
                    HotelDetail hotel = HotelDetail.getInstance(linesModel,null,null);
                    ((AppCompatActivity) context).getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.frame_container, hotel).addToBackStack(null)
                            .commit();

                }
            });


            holder.selectedgreeting.setVisibility(View.VISIBLE);
            Glide.with(context).load(linesModel.getImage()).listener(new RequestListener<Drawable>() {

                @Override
                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                    holder.selectedgreeting.setVisibility(View.GONE);
                    return false;
                }

                @Override
                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                    holder.selectedgreeting.setVisibility(View.GONE);
                    return false;
                }
            }).dontAnimate().
                    diskCacheStrategy(DiskCacheStrategy.ALL).
                    signature(new ObjectKey(linesModel.getImage())).
                    error(R.mipmap.hotellogo).thumbnail(0.5f).into(holder.food_img);

        } else  if(myholder instanceof HotelsAdapter.TendingMenuHolder){
            HotelsAdapter.TendingMenuHolder holder = (HotelsAdapter.TendingMenuHolder) myholder;

            if(position==1){
                holder.recyclerView.setVisibility(View.GONE);
                holder.message.setText("Hotels");
            } else{
                holder.recyclerView.setVisibility(View.VISIBLE);
                holder.message.setText("Trending View");
                MenuAdapter hotel_deteail_adapter = new MenuAdapter((HomePage) context, trendings,true,null,null,null);
                holder.recyclerView.addItemDecoration(new HorizontalSpaceItemDecoration(3));
                holder.recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
                holder.recyclerView.setAdapter(hotel_deteail_adapter);
                hotel_deteail_adapter.setOnItemClicklistner(new OnItemClickListner() {
                    @Override
                    public void onItemClick(int position2) {
                        Menu menu= trendings.get(position2);

                        for (HotelModel hotelModel:homelist
                        ) {


                            if (menu.hotelID.equalsIgnoreCase(hotelModel.getId()+"")){

                                HotelDetail hotel = HotelDetail.getInstance(hotelModel,menu,"Trending Menus");
                                ((AppCompatActivity) context).getSupportFragmentManager()
                                        .beginTransaction()
                                        .replace(R.id.frame_container, hotel).addToBackStack(null)
                                        .commit();
                            }
                        }

                    }
                });

            }



        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView hotel_name, contry, address, time,discout;
        private ImageView ivVeg, add;
        private  ImageView food_img;
        private  ImageView selectedgreeting;
        private RatingBar ratingBar;

        public MyViewHolder(View view) {
            super(view);
            hotel_name = (TextView) view.findViewById(R.id.home_name);
            contry = (TextView) view.findViewById(R.id.id_type);
            address = (TextView) view.findViewById(R.id.address);
            time = (TextView) view.findViewById(R.id.id_date_time);
            ratingBar = (RatingBar) view.findViewById(R.id.ratingBar);
            discout = (TextView) view.findViewById(R.id.discout);
            ivVeg = (ImageView) view.findViewById(R.id.ivVeg);
            food_img = (ImageView) view.findViewById(R.id.food_img);
            selectedgreeting = (ImageView) view.findViewById(R.id.selectedgreeting);



        }
    }
    public class TendingMenuHolder extends RecyclerView.ViewHolder {

        private RecyclerView recyclerView;
        private TextView message;


        public TendingMenuHolder(View view) {
            super(view);
            recyclerView = (RecyclerView) view.findViewById(R.id.menu);
            message = (TextView) view.findViewById(R.id.message);




        }
    }
}
