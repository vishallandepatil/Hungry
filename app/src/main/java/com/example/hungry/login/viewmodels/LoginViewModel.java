package com.example.hungry.login.viewmodels;


import android.os.CountDownTimer;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.example.hungry.MainActivity2;
import com.example.hungry.R;
import com.example.hungry.app.PrefManager;
import com.example.hungry.login.api.City;
import com.example.hungry.login.model.CityModel;
import com.example.hungry.login.model.CityResult;
import com.example.hungry.login.model.Result;
import com.example.hungry.login.model.ResultVerifyOTP;
import com.example.hungry.login.model.User;
import com.example.hungry.login.repository.LoginRepository;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

public class LoginViewModel extends ViewModel {
    public static Integer REGISTRATION = 133333;
    public static Integer DASHBOARD = 1323333;
    public LoginViewModel() {
        timeText.setValue("");
        isloading.setValue(false);
        isEditOpt.setValue(true);
        isErrorShown.setValue(false);
        isRegistration.setValue(false);
        isOTPVisible.setValue(false);
        loginbtntext.setValue(R.string.send_otp);

    }

    public MutableLiveData<String> mobileNumber = new MutableLiveData<>();
    public MutableLiveData<String> otp = new MutableLiveData<>();
    public MutableLiveData<String> name = new MutableLiveData<>();

    public MutableLiveData<Result> resultMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<ResultVerifyOTP> resultVerifyOTPMutableLiveData = new MutableLiveData<>();

    public MutableLiveData<String> timeText = new MutableLiveData<>();
    public MutableLiveData<Boolean> isloading = new MutableLiveData<>();
    public MutableLiveData<Boolean> isEditOpt = new MutableLiveData<>();
    public MutableLiveData<Boolean> isOTPVisible = new MutableLiveData<>();

    public MutableLiveData<Boolean> isErrorShown = new MutableLiveData<>();
    public MutableLiveData<Integer> nextfragment = new MutableLiveData<>();
    public MutableLiveData<CityResult> cityResultMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<Boolean> isRegistration = new MutableLiveData<>();
    public MutableLiveData<Integer> loginbtntext = new MutableLiveData<>();
    Integer selectCityPosition = 0;
    Integer selectGenderPosition = 0;
    User user=null;

    public void onSelectCity(AdapterView<?> parent, View view, int pos, long id)
    {
        selectCityPosition=pos;
    }
    public void onSelectGender(AdapterView<?> parent, View view, int pos, long id)
    {
        selectGenderPosition=pos;
    }






    Result result = new Result();


    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (!isValidMobile(s.toString())) {
            result.setMessage("Enter Valid Mobile Number");
            isErrorShown.setValue(true);
        } else {
            result.setMessage(null);
            isErrorShown.setValue(false);
        }
        resultMutableLiveData.setValue(result);
    }
    public void onOTPChanged(CharSequence s, int start, int before, int count) {


            isErrorShown.setValue(false);

    }


    public void onClick(final View view) {

        if (isValidMobile(mobileNumber.getValue())) {

            LoginRepository loginRepository = new LoginRepository();
            if ((((Button)view).getText().toString() == view.getContext().getResources().getString(R.string.send_otp))) {
                isloading.setValue(true);
                loginbtntext.setValue(R.string.send_otp);

                loginRepository.sendOTP(mobileNumber.getValue()).observeForever(new Observer<Result>() {
                    @Override
                    public void onChanged(Result result) {
                        resultMutableLiveData.setValue(result);
                        if (result.status == 200) {
                            isOTPVisible.setValue(true);
                            loginbtntext.setValue(R.string.verify_otp);
                            showTime();
                        } else {
                            isErrorShown.setValue(false);
                        }
                        isloading.setValue(false);

                    }
                });
            }
            else if ((((Button)view).getText().toString() == view.getContext().getResources().getString(R.string.register))) {
                if(isValidateUser()) {
                    isloading.setValue(true);
                    loginRepository.registerUser(user).observeForever(new Observer<ResultVerifyOTP>() {
                        @Override
                        public void onChanged(ResultVerifyOTP resultVerifyOTP) {
                            resultVerifyOTPMutableLiveData.setValue(resultVerifyOTP);
                            if (resultVerifyOTP.status == 200) {

                                timeText.setValue(null);
                                if(resultVerifyOTP.result!=null && resultVerifyOTP.result.size()>=1){
                                    new PrefManager(view.getContext()).createLogin(resultVerifyOTP.result.get(0));
                                    nextfragment.setValue(DASHBOARD);
                                } else {
                                    isRegistration.setValue(true);
                                    isOTPVisible.setValue(false);
                                    timeText.setValue(null);
                                    loginbtntext.setValue(R.string.register);


                                }
                            } else {
                                isErrorShown.setValue(true);
                                result.setMessage(resultVerifyOTP.message);
                                resultMutableLiveData.setValue(result);
                            }
                            isloading.setValue(false);

                        }

                    });
                } else {
                    isErrorShown.setValue(true);
                    resultMutableLiveData.setValue(result);
                }
            }
            else {
                if(otp!=null && !otp.getValue().isEmpty() && otp.getValue().length()==4) {
                    isloading.setValue(true);
                    isEditOpt.setValue(false);
                    loginRepository.verifyOTP(mobileNumber.getValue(), otp.getValue()).observeForever(new Observer<ResultVerifyOTP>() {
                        @Override
                        public void onChanged(ResultVerifyOTP resultVerifyOTP) {
                            resultVerifyOTPMutableLiveData.setValue(resultVerifyOTP);
                            if (resultVerifyOTP.status == 200) {

                                countDownTimer.cancel();
                                timeText.setValue(null);
                                if(resultVerifyOTP.result!=null && resultVerifyOTP.result.size()>=1){
                                    new PrefManager(view.getContext()).createLogin(resultVerifyOTP.result.get(0));
                                    nextfragment.setValue(DASHBOARD);
                                } else {
                                    isRegistration.setValue(true);
                                    isOTPVisible.setValue(false);
                                    timeText.setValue(null);
                                    loginbtntext.setValue(R.string.register);


                                }


                            } else {
                                isErrorShown.setValue(true);
                                result = resultMutableLiveData.getValue();
                                result.setMessage(resultVerifyOTP.message);
                                resultMutableLiveData.setValue(result);



                            }
                            isloading.setValue(false);
                            isEditOpt.setValue(true);

                        }

                    });
                }
            }

            ;

        } else {

            result.setMessage("Enter Valid Mobile Number");
            resultMutableLiveData.setValue(result);

        }

    }

    private boolean isValidMobile(String phone) {
        if (phone == null) {
            return false;
        }
        return android.util.Patterns.PHONE.matcher(phone).matches();
    }
    CountDownTimer countDownTimer=  new CountDownTimer(60000, 1000) {

        public void onTick(long millisUntilFinished) {
            timeText.setValue("Resend OTP in " + millisUntilFinished / 1000);
        }

        public void onFinish() {
            timeText.setValue(null);
        }
    };
    public void showTime() {
        countDownTimer.start();
    }

    public void loadCities(){
        LoginRepository login =new LoginRepository();
        cityResultMutableLiveData=login.getCity("19");


    }
    private boolean isValidateUser(){
        boolean result=true;
        user = new User();
        if(mobileNumber.getValue()!=null){
            user.setCM_MOBILE(mobileNumber.getValue());
        }  else {
            result= false;
            this.result.setMessage("Enter Valid Mobile No.");

        }
        if(name.getValue()!=null&&!name.getValue().isEmpty()) {
            user.setCM_FIRST_NAME(name.getValue());
            user.setCM_LAST_NAME(name.getValue());
        } else {
            result= false;
            this.result.setMessage("Enter User Name");


        }
        if(selectCityPosition!=0) {
            CityModel city=cityResultMutableLiveData.getValue().getResult().get(selectCityPosition);
            user.setCITY_MASTER_ID(Integer.parseInt(city.getCitymaster_id()));
            user.setSTATE_MASTER_ID(Integer.parseInt(city.getStatemaster_id()));

        } else {
            result= false;
            this.result.setMessage("Select Your City");

        }
        if(selectGenderPosition!=0) {
            user.setCM_GENDER(selectGenderPosition==1?"M":"F");

        } else {
            result= false;
            this.result.setMessage("Select Your Gender");


        }
        resultMutableLiveData.setValue(this.result);
        return  result;
    }
}
