package com.hungry.customer.profile.fragment.viewmodel;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.hungry.customer.R;
import com.hungry.customer.app.PrefManager;
import com.hungry.customer.login.model.CityResult;
import com.hungry.customer.login.model.ResultVerifyOTP;
import com.hungry.customer.login.model.User;
import com.hungry.customer.login.repository.LoginRepository;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

public class ProfileViewModel  extends ViewModel {
    public MutableLiveData<Boolean> isloading = new MutableLiveData<>();

    public MutableLiveData<CityResult> cityResultMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<User> userMutableLiveData = new MutableLiveData<>();
    Integer selectCityPosition = 0;
     Integer selectedCityiD = 0;
    public void loadCities(){
        LoginRepository login =new LoginRepository();
        cityResultMutableLiveData=login.getCity("19");


    }
    public void onSelectCity(AdapterView<?> parent, View view, int pos, long id)
    {
        if(pos>0) {
            selectCityPosition = pos;

            selectedCityiD = Integer.parseInt(cityResultMutableLiveData.getValue().getResult().get(pos).getCitymaster_id());
        }

    }

    public void onClick(final View view){

        if(selectCityPosition>0 && userMutableLiveData.getValue()!=null && userMutableLiveData.getValue().getCITY_MASTER_ID()!=selectedCityiD)
        {
            isloading.setValue(true);
            User user = userMutableLiveData.getValue();
            user.setCITY_MASTER_ID(selectedCityiD);
            LoginRepository loginRepository = new LoginRepository();
            loginRepository.updateUser(user).observeForever(new Observer<ResultVerifyOTP>() {
                @Override
                public void onChanged(ResultVerifyOTP resultVerifyOTP) {

                    if (resultVerifyOTP.status == 200) {
                        userMutableLiveData.setValue(resultVerifyOTP.result.get(0));
                        new PrefManager(view.getContext()).createLogin(userMutableLiveData.getValue());
                        Toast.makeText(view.getContext(),resultVerifyOTP.message,Toast.LENGTH_LONG).show();


                    } else {

                        Toast.makeText(view.getContext(),resultVerifyOTP.message,Toast.LENGTH_LONG).show();
                    }
                    isloading.setValue(false);

                }
            });

        }

    }
}
