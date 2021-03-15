package com.hungry.customer.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.hungry.customer.HomePage;
import com.hungry.customer.R;
import com.hungry.customer.databinding.FragmentLoginBinding;
import com.hungry.customer.login.adapter.CustomAdapter;
import com.hungry.customer.login.model.CityModel;
import com.hungry.customer.login.model.CityResult;
import com.hungry.customer.login.viewmodels.LoginViewModel;

import java.util.regex.Pattern;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import static com.hungry.customer.login.viewmodels.LoginViewModel.DASHBOARD;


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

    //HGFHGFHG

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


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
                    try {
                        CustomAdapter adaptercity = new CustomAdapter(getActivity(), R.layout.spinner_item, cityResult.result);
                        binding.city.setAdapter(adaptercity);
                    } catch (Exception e){

                    }

                }
            }
        });

        return binding.getRoot();
    }


    private void addLinkToTextView() {
        Pattern pattern = Pattern.compile("privacy policy");
        binding.tvPrivacyPolicyLink.setText("This number will not used for any kind of promotional activity, it will kept confidential. For more please refer to our privacy policy");
        Linkify.addLinks(binding.tvPrivacyPolicyLink, pattern, "http://hungryindia.co.in/index.php/admin/privacypolicy");
    }

    private void setupToolbar() {

    }















}
