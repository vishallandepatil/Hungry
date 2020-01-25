package com.example.hungry.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.util.Linkify;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;


import com.example.hungry.HomePage;
import com.example.hungry.R;
import com.example.hungry.databinding.FragmentLoginBinding;
import com.example.hungry.login.adapter.CustomAdapter;
import com.example.hungry.login.api.City;
import com.example.hungry.login.model.CityModel;
import com.example.hungry.login.model.CityResult;
import com.example.hungry.login.model.Result;
import com.example.hungry.login.viewmodels.LoginViewModel;
import com.example.hungry.retrofitsetting.RetrofitClientInstance;

import java.util.ArrayList;
import java.util.regex.Pattern;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.hungry.login.viewmodels.LoginViewModel.DASHBOARD;


public class LoginFragment extends Fragment {


    private LoginViewModel loginViewModel;
    private FragmentLoginBinding binding;




    public LoginFragment() {
        // Required empty public constructor
    }

    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setupToolbar();

        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_login, container, false);



        addLinkToTextView();
        binding.setLoginViewModel(loginViewModel);
        binding.setLifecycleOwner(this);
        loginViewModel.nextfragment.observeForever(new Observer<Integer>() {
            @Override
            public void onChanged(Integer newfragent) {
                if (newfragent==DASHBOARD) {
                    Intent intent = new Intent(getActivity(), HomePage.class);
                    startActivity(intent);
                    getActivity().finish();
                }  else {

                }

            }
        });
        loginViewModel.loadCities();
        loginViewModel.cityResultMutableLiveData.observeForever(new Observer<CityResult>() {
            @Override
            public void onChanged(CityResult cityResult) {
                if(cityResult.status==200){
                    CityModel city = new CityModel();
                    city.setCity_name("Select City");
                    cityResult.result.add(0,city);
                    CustomAdapter adaptercity=new CustomAdapter(getActivity(),R.layout.spinner_item,cityResult.result);
                    binding.city.setAdapter(adaptercity);
                }
            }
        });

        return binding.getRoot();
    }


    private void addLinkToTextView() {
        Pattern pattern = Pattern.compile("privacy policy");
        binding.tvPrivacyPolicyLink.setText("This number will not used for any kind of promotional activity, it will kept confidential. For more please refer to our privacy policy");
        Linkify.addLinks(binding.tvPrivacyPolicyLink, pattern, "http://www.google.ie/search?q=");
    }

    private void setupToolbar() {
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
       // ((MainActivity2) getActivity()).setDrawerLocked(true);
    }















}
