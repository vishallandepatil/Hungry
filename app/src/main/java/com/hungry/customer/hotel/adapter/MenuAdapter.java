package com.hungry.customer.hotel.adapter;

import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.library.baseAdapters.BR;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hungry.customer.HomePage;
import com.hungry.customer.R;
import com.hungry.customer.databinding.FilterMenuItemsBinding;
import com.hungry.customer.databinding.HotelDetailListBinding;
import com.hungry.customer.databinding.SearchResultTmenItemBinding;
import com.hungry.customer.databinding.TitleBinding;
import com.hungry.customer.hotel.CartListner;
import com.hungry.customer.hotel.model.Category;
import com.hungry.customer.hotel.model.Menu;
import com.hungry.customer.util.OnItemClickListner;

import java.util.ArrayList;

import static androidx.recyclerview.widget.LinearLayoutManager.*;

public class MenuAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    final public static int VIEW_SEARCH=0000;
    final public static int VIEW_SEARCH_TITEL=2222;
    final int VIEW_MENU=1111;
    final public static int VIEW_FILTER=3333;
    ArrayList<Menu> menus = new ArrayList<>();
    ArrayList<Category> categories;
    Menu menu;
    String title;
    private boolean isTrendingMenu;
    HomePage context;


    public void setOnItemClicklistner(OnItemClickListner onItemClicklistner) {
        OnItemClicklistner = onItemClicklistner;
    }
    public void setOnFilterClicklistner(OnItemClickListner onFilterClickListner) {
       this. onFilterClickListner = onFilterClickListner;
    }

    OnItemClickListner OnItemClicklistner,onFilterClickListner;

    public void setListner(CartListner listner) {
        this.listner = listner;
    }

    CartListner listner;
    public MenuAdapter(HomePage context, ArrayList<Menu> menus,boolean isTrendingMenu,Menu menu,String title,ArrayList<Category> categories) {
        this.context = context;
        this.title = title;
        this.isTrendingMenu = isTrendingMenu;
        this.categories = categories;
        if(menus!=null) {
            this.menus.addAll(menus);
        }
        if(menu!=null){

            this.menus.add(0,menu);
            this.menus.add(0,null);
            this.menus.add(2,null);
        } else {
            if(!isTrendingMenu) {
                this.menus.add(0, null);
            }
        }

        this.menu = menu;


    }

    @Override
    public int getItemViewType(int position) {

        if(menu!=null){
            if(position == 0) {
                return VIEW_SEARCH_TITEL;
            } else  if( position == 1){
                return VIEW_SEARCH;
            } else if(position ==2){
                return VIEW_FILTER;
            }
        }
        if(position==0){
            if(!isTrendingMenu) {
                return VIEW_FILTER;
            }
        }
        return VIEW_MENU;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==VIEW_SEARCH){
            SearchResultTmenItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.search_result_tmen_item, parent, false);
            SearchViewViewHolder vh = new SearchViewViewHolder(binding); // pass the view to View Holder
            return vh;
        }
        else if(viewType==VIEW_SEARCH_TITEL){
            TitleBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.title, parent, false);
            TitleViewHolder vh2 = new TitleViewHolder(binding); // pass the view to View Holder
            return vh2;
        }
        else if(viewType==VIEW_FILTER){

            FilterMenuItemsBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.filter_menu_items, parent, false);
            FilterViewHolder vh = new FilterViewHolder(binding); // pass the view to View Holder
            return vh;
        }

        else {
            HotelDetailListBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.hotel_detail_list, parent, false);
            MyViewHolder vh = new MyViewHolder(binding); // pass the view to View Holder
            return vh;
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder2, int position) {

        if(holder2 instanceof  MyViewHolder) {

            MyViewHolder holder= (MyViewHolder) holder2;
            final Menu dataModel = menus.get(position);

            holder.bind(dataModel);
            for (Menu menu : context.cart) {
                if (menu.menuId == dataModel.menuId) {
                    dataModel.isAddedToCart = true;

                }

            }
            holder.binding.btnAddToCard.setBackground(context.getDrawable(dataModel.isAddedToCart ? R.drawable.addedtocart : R.drawable.addtocart_border));


            holder.binding.btnAddToCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!dataModel.isAddedToCart) {
                        boolean isAdded = false;
                        for (Menu menu : context.cart) {
                            if (menu.menuId == dataModel.menuId) {
                                isAdded = true;
                            }

                        }
                        if (!isAdded) {
                            dataModel.isAddedToCart = true;
                            dataModel.total = dataModel.amount;
                            context.cart.add(dataModel);
                            notifyDataSetChanged();
                            if (listner != null) {
                                listner.onChange();
                            }
                        }

                    }
                }
            });

            if (isTrendingMenu) {
                holder.binding.btnAddToCard.setVisibility(View.GONE);
                holder.binding.getRoot().getLayoutParams().width = 500;
                holder.binding.getRoot().getLayoutParams().height = 600;

                holder.binding.getRoot().setOnClickListener(view -> OnItemClicklistner.onItemClick(position));
            } else {
                holder.binding.btnAddToCard.setVisibility(View.VISIBLE);
            }
            holder.binding.orizenalamount.setPaintFlags(holder.binding.orizenalamount.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        }

        if(holder2 instanceof  SearchViewViewHolder) {

            SearchViewViewHolder holder= (SearchViewViewHolder) holder2;
            final Menu dataModel = menus.get(position);
            dataModel.isAddedToCart = false;
            holder.bind(dataModel);
            for (Menu menu : context.cart) {
                if (menu.menuId == dataModel.menuId) {
                    dataModel.isAddedToCart = true;

                }

            }
            holder.binding.btnAddToCard.setBackground(context.getDrawable(dataModel.isAddedToCart ? R.drawable.addedtocart : R.drawable.addtocart_border));


            holder.binding.btnAddToCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!dataModel.isAddedToCart) {
                        boolean isAdded = false;
                        for (Menu menu : context.cart) {
                            if (menu.menuId == dataModel.menuId) {
                                isAdded = true;
                            }

                        }
                        if (!isAdded) {
                            dataModel.isAddedToCart = true;
                            dataModel.total = dataModel.amount;
                            context.cart.add(dataModel);
                            notifyDataSetChanged();
                            if (listner != null) {
                                listner.onChange();
                            }
                        }

                    }
                }
            });

            if (isTrendingMenu) {
                holder.binding.btnAddToCard.setVisibility(View.GONE);
                holder.binding.getRoot().getLayoutParams().width = 500;
                holder.binding.getRoot().getLayoutParams().height = 650;

                holder.binding.getRoot().setOnClickListener(view -> OnItemClicklistner.onItemClick(position));
            } else {
                holder.binding.btnAddToCard.setVisibility(View.VISIBLE);
            }
            holder.binding.orizenalamount.setPaintFlags(holder.binding.orizenalamount.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        }
        if(holder2 instanceof  TitleViewHolder){

            TitleViewHolder titleViewHolder = (TitleViewHolder) holder2;
            titleViewHolder.binding.title.setText(title);
            if(position==2){
                titleViewHolder.binding.title.setText("Other Menus");
            }
        }

        if(holder2 instanceof FilterViewHolder){
            FilterViewHolder filterViewHolder = (FilterViewHolder) holder2;
            FilterAdapter filterAdapter = new FilterAdapter(context,categories);
            filterViewHolder.binding.category.setLayoutManager(new LinearLayoutManager(context, HORIZONTAL,false));
            filterViewHolder.binding.category.setAdapter(filterAdapter);
            filterAdapter.setOnItemClicklistner(position2 -> {
                if(onFilterClickListner!=null){
                    onFilterClickListner.onItemClick(position2);

                }
            });

        }
    }


    @Override
    public int getItemCount() {


                return menus.size();


    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        HotelDetailListBinding binding;

        public MyViewHolder(HotelDetailListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }

        public void bind(Object obj) {
            binding.setVariable(BR.menu, obj);
            binding.executePendingBindings();
        }
    }
    public class FilterViewHolder extends RecyclerView.ViewHolder {

        FilterMenuItemsBinding binding;

        public FilterViewHolder(FilterMenuItemsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }

        public void bind(Object obj) {
            binding.setVariable(BR.menu, obj);
            binding.executePendingBindings();
        }
    }
    public class SearchViewViewHolder extends RecyclerView.ViewHolder {

        SearchResultTmenItemBinding binding;

        public SearchViewViewHolder(SearchResultTmenItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }

        public void bind(Object obj) {
            binding.setVariable(BR.menu, obj);
            binding.executePendingBindings();
        }
    }
    public class TitleViewHolder extends RecyclerView.ViewHolder {

        TitleBinding binding;

        public TitleViewHolder(TitleBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }

        public void bind(Object obj) {
            binding.setVariable(BR.title, obj);
            binding.executePendingBindings();
        }
    }

}