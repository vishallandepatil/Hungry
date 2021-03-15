package com.hungry.customer.hotel.adapter;
        import android.graphics.Paint;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;

        import androidx.databinding.DataBindingUtil;
        import androidx.databinding.library.baseAdapters.BR;
        import androidx.recyclerview.widget.RecyclerView;

        import com.hungry.customer.HomePage;
        import com.hungry.customer.R;
        import com.hungry.customer.databinding.FilterCategoryItemBinding;
        import com.hungry.customer.databinding.FilterMenuItemsBinding;
        import com.hungry.customer.databinding.HotelDetailListBinding;
        import com.hungry.customer.databinding.SearchResultTmenItemBinding;
        import com.hungry.customer.databinding.TitleBinding;
        import com.hungry.customer.hotel.CartListner;
        import com.hungry.customer.hotel.model.Category;
        import com.hungry.customer.hotel.model.Menu;
        import com.hungry.customer.util.OnItemClickListner;

        import java.util.ArrayList;

public class FilterAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {



    ArrayList<Category> categories;
    HomePage context;


    public void setOnItemClicklistner(OnItemClickListner onItemClicklistner) {
        this.OnItemClicklistner = onItemClicklistner;
    }

    OnItemClickListner OnItemClicklistner;


    public FilterAdapter(HomePage context, ArrayList<Category> categories) {
        this.context = context;
        this.categories = categories;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            FilterCategoryItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.filter_category_item, parent, false);
            MyViewHolder vh = new MyViewHolder(binding); // pass the view to View Holder
            return vh;

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder2, int position) {

        if(holder2 instanceof  MyViewHolder) {
            MyViewHolder myViewHolder = (MyViewHolder) holder2;
            myViewHolder.binding.setMenu(categories.get(position));
            myViewHolder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(OnItemClicklistner!=null) {
                        OnItemClicklistner.onItemClick(position);
                    }
                }
            });



                myViewHolder.binding.getRoot().setBackground(context.getDrawable(categories.get(position).isSelected?R.drawable.selected_filter_bacground:R.drawable.border_filter));


        }

    }


    @Override
    public int getItemCount() {


        return categories!=null?categories.size():0;


    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        FilterCategoryItemBinding binding;

        public MyViewHolder(FilterCategoryItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }

        public void bind(Object obj) {
            binding.setVariable(BR.menu, obj);
            binding.executePendingBindings();
        }
    }


}