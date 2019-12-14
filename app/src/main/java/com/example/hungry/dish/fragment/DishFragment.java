package com.example.hungry.dish.fragment;


import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.hungry.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;


public class DishFragment extends Fragment {
// test comment
    public void  test(){}
    public DishFragment() {
        // Required empty public constructor
    }

    public static DishFragment newInstance(String param1, String param2) {
        DishFragment fragment = new DishFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View rootview = inflater.inflate(R.layout.fragment_gifts, container, false);
        Toolbar toolbar = (Toolbar) rootview.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        //setting the title
        setHasOptionsMenu(true);
        return  rootview;
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.hotel_action_menu, menu);
    }
}
