package com.example.hungry.payments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hungry.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PaymentOptions#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PaymentOptions extends Fragment {


    public static PaymentOptions newInstance() {
        PaymentOptions fragment = new PaymentOptions();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_payment_options, container, false);
    }
}