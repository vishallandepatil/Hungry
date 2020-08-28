package com.example.hungry.myorder.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hungry.R;
import com.example.hungry.databinding.FragmentTrackOrderBinding;
import com.example.hungry.myorder.model.Order;
import com.example.hungry.myorder.model.OrderResult;
import com.example.hungry.myorder.viewModel.TrackOrderViewModel;
import com.example.hungry.ordersummary.viewmodel.OrderSummaryViewModel;
import com.google.gson.Gson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TrackOrderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TrackOrderFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private Order orderResult;

    public TrackOrderFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment TrackOrderFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TrackOrderFragment newInstance(String param1) {
        TrackOrderFragment fragment = new TrackOrderFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            String mParam1 = getArguments().getString(ARG_PARAM1);
            Gson gson = new Gson();
            orderResult = gson.fromJson(mParam1, Order.class);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

       FragmentTrackOrderBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_track_order, container, false);
       TrackOrderViewModel trackOrderViewModel= ViewModelProviders.of(this).get(TrackOrderViewModel.class);
        binding.setTrackOrderViewModel(trackOrderViewModel);
        binding.setLifecycleOwner(this);
        trackOrderViewModel.order.setValue(orderResult);



        SimpleDateFormat outputdatformat = new SimpleDateFormat("hh:mm: a");
        SimpleDateFormat inputdsf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = null;
        String statustimemessage = null;
        String detaileddescription = null;
        int color = R.color.colorPrimaryDark;
        if (orderResult.status.equalsIgnoreCase("OPEN")){
            date =orderResult.expectedDeliveryITme;
            trackOrderViewModel.openProgress.setValue(50);
            statustimemessage="Estimated Arrival";
            detaileddescription="confirming with hotel";
        }
        else if(orderResult.status.equalsIgnoreCase("ACCEPTED")) {
            date =orderResult.expectedDeliveryITme;
            statustimemessage="Estimated Arrival";
            detaileddescription="Accepted By hotel";
            trackOrderViewModel.openProgress.setValue(100);
            trackOrderViewModel.acceptedProgress.setValue(50);
        }
        else if(orderResult.status.equalsIgnoreCase("READY")) {
            date =orderResult.expectedDeliveryITme;
            statustimemessage="Estimated Arrival";
            detaileddescription="Ready To Dispatch";

            trackOrderViewModel.openProgress.setValue(100);
            trackOrderViewModel.acceptedProgress.setValue(100);
            trackOrderViewModel.readyProgress.setValue(50);

        }
        else if(orderResult.status.equalsIgnoreCase("DISPATCHED")) {
            date =orderResult.expectedDeliveryITme;
            detaileddescription="Delivery Boy is Heading to you";
            trackOrderViewModel.openProgress.setValue(100);
            trackOrderViewModel.acceptedProgress.setValue(100);
            trackOrderViewModel.readyProgress.setValue(100);
            trackOrderViewModel.dispatchedProgress.setValue(50);
        }
        else if(orderResult.status.equalsIgnoreCase("DELIVERED")) {
            date =orderResult.diliveryDateTime;
            color=R.color.DarkGreen;
            statustimemessage="Dilivered";
            outputdatformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            detaileddescription="Dilivered Succesfully";
            trackOrderViewModel.openProgress.setValue(100);
            trackOrderViewModel.acceptedProgress.setValue(100);
            trackOrderViewModel.readyProgress.setValue(100);
            trackOrderViewModel.dispatchedProgress.setValue(100);
            trackOrderViewModel.diliveredProgress.setValue(100);
        }
        else if(orderResult.status.equalsIgnoreCase("CANCELED")) {
            date =null;
            color=R.color.red;
            statustimemessage="Order Canceled";
            detaileddescription="Order was Canceled ";
        }
        else if(orderResult.status.equalsIgnoreCase("REJECTED")) {
            date =null;
            color=R.color.red;
            statustimemessage="Rejected";
            detaileddescription="Extreamly Sorry Hotel are not ready to accept order";
        }
        try {
            binding.statustimemessage.setTextColor(getResources().getColor(color));
            binding.detaileddescription.setTextColor(getResources().getColor(color));
            binding.statustimemessage.setText(statustimemessage);
            binding.detaileddescription.setText(detaileddescription);
            if(date !=null) {
                Date datestr = inputdsf.parse(date);
                binding.estimateddate.setText(outputdatformat.format(datestr));
            } else {
                binding.estimateddate.setText("");
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }


        return binding.getRoot();
    }
}