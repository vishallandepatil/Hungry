package com.hungry.customer.hotel.fragment;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.hungry.customer.HomePage;
import com.hungry.customer.hotel.model.Category;
import com.hungry.customer.ordersummary.OrderSummary;
import com.hungry.customer.R;
import com.hungry.customer.databinding.FragmentHotelDetailBinding;
import com.hungry.customer.hotel.CartListner;
import com.hungry.customer.hotel.model.Menu;
import com.hungry.customer.hotel.model.MenuResult;
import com.hungry.customer.hotel.viewmodels.HotelDetailViewModel;
import com.hungry.customer.hotel.adapter.MenuAdapter;
import com.hungry.customer.hotel.model.HotelModel;
import com.hungry.customer.util.Curruncy;
import com.hungry.customer.util.GridSpacingItemDecoration;
import com.hungry.customer.util.OnItemClickListner;

import static com.hungry.customer.hotel.adapter.MenuAdapter.VIEW_FILTER;
import static com.hungry.customer.hotel.adapter.MenuAdapter.VIEW_SEARCH;
import static com.hungry.customer.hotel.adapter.MenuAdapter.VIEW_SEARCH_TITEL;


public class HotelDetail extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";
    HotelModel hotelModel;
    int selectFilter = -1;
    Menu menu;
    String title;
    String category=null;
    HotelDetailViewModel hotelDetailViewModel;
    FragmentHotelDetailBinding binding;
    MenuAdapter hotel_deteail_adapter;
    public HotelDetail() {
        // Required empty public constructor
    }
    public static HotelDetail getInstance(HotelModel linesModel,Menu menu,String title){

        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM1, linesModel);
        args.putParcelable(ARG_PARAM2, menu);
        args.putString(ARG_PARAM3, title);
        HotelDetail fragment =new HotelDetail();
        fragment.setArguments(args);
        return fragment;

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            try {
                hotelModel = (HotelModel) getArguments().getParcelable(ARG_PARAM1);
            }catch (Exception e){

            }
            menu = (Menu) getArguments().getParcelable(ARG_PARAM2);
            title =  getArguments().getString(ARG_PARAM3);
        }

    }

    @Override
    public void onResume() {
        super.onResume();

            try {
                String size = ((HomePage) getActivity()).cart.size()+"";
                binding.ibcartCount.setText(size+"");
                binding.itemcount.setText("Items: "+((HomePage) getActivity()).cart.size());
                double total=0;
                for(Menu menu : ((HomePage) getActivity()).cart){
                    total+= menu.amount;
                }
                binding.total.setText("Total: "+ Curruncy.getCurruncy(total+""));
            } catch (Exception e){

            }



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_hotel_detail, container, false);
        hotelDetailViewModel = ViewModelProviders.of(this).get(HotelDetailViewModel.class);
        hotelDetailViewModel.setMutaibleHotelModel(hotelModel);
        binding.setLifecycleOwner(this);
        hotelDetailViewModel.isVeg.setValue(false);
        binding.setHotelDetails(hotelDetailViewModel);
        hotelDetailViewModel.loadMenus(null,category);
        ((AppCompatActivity)getActivity()).setSupportActionBar(binding.toolbar);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);

        binding.simpleSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {


                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                selectFilter =-1;
                if(newText!=null && newText.length()>2){
                    hotelDetailViewModel.loadMenus(newText,category);
                } else {
                    hotelDetailViewModel.loadMenus(null,category);

                }
                return false;
            }
        });

        GridLayoutManager.SpanSizeLookup onSpanSizeLookup = new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return (hotel_deteail_adapter.getItemViewType(position) == VIEW_SEARCH ||hotel_deteail_adapter.getItemViewType(position) == VIEW_SEARCH_TITEL||hotel_deteail_adapter.getItemViewType(position) == VIEW_FILTER) ? 2 : 1;
            }
        };
        gridLayoutManager.setSpanSizeLookup(onSpanSizeLookup);
        binding.rvLine.setLayoutManager(gridLayoutManager);
        binding.rvLine.addItemDecoration(new GridSpacingItemDecoration(2,  /*getResources().getDimensionPixelSize(R.dimen.grid_margin)*/20,false,menu));

        // set LayoutManager to RecyclerView
        hotelDetailViewModel.menuResultMutableLiveData.observeForever(new Observer<MenuResult>() {
            @Override
            public void onChanged(MenuResult menuResult) {

               if(selectFilter>=0) {
                   if(menuResult.categories!=null && menuResult.categories.size()>0) {
                       menuResult.categories.get(selectFilter).isSelected = true;
                   }
               }

                hotel_deteail_adapter = new MenuAdapter((HomePage) getActivity(), menuResult.result,false,menu,title,menuResult.categories);
                binding.setMenuAdapter(hotel_deteail_adapter);
                hotel_deteail_adapter.setListner(new CartListner() {
                    @Override
                    public void onChange() {
                        String size = ((HomePage) getActivity()).cart.size()+"";
                        binding.ibcartCount.setText(size+"");
                        binding.itemcount.setText("Items: "+((HomePage) getActivity()).cart.size());
                        double total=0;
                        for(Menu menu : ((HomePage) getActivity()).cart){
                            total+= menu.amount;
                        }
                        binding.total.setText("Total: "+ Curruncy.getCurruncy(total+""));
                    }
                });

                hotel_deteail_adapter.setOnFilterClicklistner(new OnItemClickListner() {
                    @Override
                    public void onItemClick(int position) {
                        //Toast.makeText(getContext(),""+menuResult.categories.get(position).Name,Toast.LENGTH_LONG).show();
                        category = menuResult.categories.get(position).Name;
                        selectFilter = position;
                        hotelDetailViewModel.loadMenus(null,category);

                    }
                });
            }
        });
        String size = ((HomePage) getActivity()).cart.size()+"";
        binding.ibcartCount.setText(size+"");
        binding.ibcartCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((HomePage) getActivity()).cart.size() > 0) {
                    OrderSummary fragment = new OrderSummary();
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.frame_container, fragment).addToBackStack(null)
                            .commit();
                }
            }
        });
        binding.ibcartCount1.setText(size+"");
        binding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((HomePage) getActivity()).cart.size() > 0) {
                    OrderSummary fragment = new OrderSummary();
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.frame_container, fragment).addToBackStack(null)
                            .commit();
                }
            }
        });
        return  binding.getRoot();
    }




}
