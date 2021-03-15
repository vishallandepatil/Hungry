package com.hungry.customer.hotel.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.signature.ObjectKey;
import com.hungry.customer.hotel.model.ImageModel;
import com.hungry.customer.R;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;


public class SlidingImage_Adapter extends PagerAdapter {


    private ArrayList<ImageModel> imageModelArrayList;
    private LayoutInflater inflater;
    private Context context;


    public SlidingImage_Adapter(Context context, ArrayList<ImageModel> imageModelArrayList) {
        this.context = context;
        this.imageModelArrayList = imageModelArrayList;
        try{
        inflater = LayoutInflater.from(context);}
        catch (Exception e){

        }
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        if (imageModelArrayList!=null)
            return imageModelArrayList.size();
        else
            return 0;
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        View imageLayout = inflater.inflate(R.layout.slidingimages_layout, view, false);

        assert imageLayout != null;
        final ImageView imageView = (ImageView) imageLayout
                .findViewById(R.id.image);
        final ImageView loader = (ImageView) imageLayout
                .findViewById(R.id.selectedgreeting);

        loader.setVisibility(View.VISIBLE);
        Glide.with(context).load(imageModelArrayList.get(position).getIMG_PATH()).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                loader.setVisibility(View.GONE);

                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                loader.setVisibility(View.GONE);

                return false;
            }
        }).dontAnimate().
                diskCacheStrategy(DiskCacheStrategy.ALL).
                signature(new ObjectKey(imageModelArrayList.get(position).getCREATED_AT())).
                error(R.drawable.comming_soon).thumbnail(0.5f).into(imageView);
//        Glide.with(context).load(imageModelArrayList.get(position).getIMG_PATH()).listener(new RequestListener<String, GlideDrawable>() {
//            @Override
//            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
//                return false;
//            }
//
//            @Override
//            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
//                loader.setVisibility(View.GONE);
//                return false;
//            }
//        }).dontAnimate().
//                diskCacheStrategy(DiskCacheStrategy.ALL).
//                signature(new StringSignature(imageModelArrayList.get(position).getCREATED_AT())).
//                error(R.drawable.comming_soon).thumbnail(0.5f).into(imageView);

        view.addView(imageLayout, 0);



        return imageLayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }


}