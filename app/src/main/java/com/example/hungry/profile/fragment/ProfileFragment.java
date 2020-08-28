package com.example.hungry.profile.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.hungry.R;
import com.example.hungry.app.PrefManager;
import com.example.hungry.databinding.FragmentLoginBinding;
import com.example.hungry.databinding.FragmentProfileBinding;
import com.example.hungry.login.adapter.CustomAdapter;
import com.example.hungry.login.api.City;
import com.example.hungry.login.model.CityModel;
import com.example.hungry.login.model.CityResult;
import com.example.hungry.login.model.User;
import com.example.hungry.login.viewmodels.LoginViewModel;
import com.example.hungry.profile.fragment.viewmodel.ProfileViewModel;


public class ProfileFragment extends Fragment {

    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
        final FragmentProfileBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_profile, container, false);

        ProfileViewModel profileViewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);
       ((AppCompatActivity)getActivity()).setSupportActionBar(binding.toolbar);
        binding.setProfileViewModel(profileViewModel);
        binding.setLifecycleOwner(this);
        final User user = new PrefManager(getContext()).getUserDetails();
        profileViewModel.userMutableLiveData.setValue(user);
        setHasOptionsMenu(true);
        binding.name.setEnabled(false);
        binding.mobie.setEnabled(false);

        profileViewModel.loadCities();
        profileViewModel.cityResultMutableLiveData.observeForever(new Observer<CityResult>() {
            @Override
            public void onChanged(CityResult cityResult) {
                if(cityResult.status==200){
                    CityModel city = new CityModel();
                    city.setCity_name("Select City");
                    cityResult.result.add(0,city);
                    CustomAdapter adaptercity=new CustomAdapter(getActivity(),R.layout.spinner_item,cityResult.result);
                    binding.city.setAdapter(adaptercity);
                    int i = 0;
                    for(CityModel city1 :cityResult.result){

                        if(i>0 && user.getCITY_MASTER_ID() == Integer.parseInt(city1.getCitymaster_id())){
                            binding.city.setSelection(i);
                            break;
                        }
                        i++;

                    }


                }
            }
        });
        return  binding.getRoot();
    }

}
