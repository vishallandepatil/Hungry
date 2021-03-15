package com.hungry.customer.util;

import android.graphics.Rect;
import android.view.View;

import com.hungry.customer.hotel.model.Menu;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Administrator on 15.01.2018.
 */

public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration{
    private int spanCount;
    Menu menu;
    private int spacing;
    private boolean includeEdge;

    public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge, Menu menu) {
        this.spanCount = spanCount;
        this.menu = menu;
        this.spacing = spacing;
        this.includeEdge = includeEdge;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);
        int column = position % spanCount;
        if(menu!=null) {
            if(position==1){

                outRect.left = spacing;
                outRect.right = spacing;
                outRect.top = spacing;



            } else
            {
                if(position%2==0){
                    outRect.left = spacing;
                    outRect.right = spacing;
                    outRect.top = spacing;

                } else{
                    outRect.top = spacing;
                    outRect.left = spacing;

                }
            }

        } else {


            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) {
                    outRect.top = spacing;
                }
                outRect.bottom = spacing;
            } else {
//                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
//                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
//                if (position >= spanCount) {
//                    outRect.top = spacing;
//                }

                if(position%2==0){
                    outRect.left = spacing;
                    outRect.top = spacing;
                    outRect.right = spacing;
                } else{
                    outRect.top = spacing;
                    //outRect.right = spacing;
                    outRect.left = spacing/2;

                }

            }
        }
    }
}
