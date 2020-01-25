package com.example.hungry.login.viewmodels;


import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;

import com.example.hungry.R;
import com.example.hungry.login.model.Result;
import com.example.hungry.login.model.ResultVerifyOTP;
import com.example.hungry.login.repository.LoginRepository;

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

    }

    public MutableLiveData<String> mobileNumber = new MutableLiveData<>();
    public MutableLiveData<String> otp = new MutableLiveData<>();
    public MutableLiveData<Result> resultMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<ResultVerifyOTP> resultVerifyOTPMutableLiveData = new MutableLiveData<>();

    public MutableLiveData<String> timeText = new MutableLiveData<>();
    public MutableLiveData<Boolean> isloading = new MutableLiveData<>();
    public MutableLiveData<Boolean> isEditOpt = new MutableLiveData<>();
    public MutableLiveData<Boolean> isErrorShown = new MutableLiveData<>();
    public MutableLiveData<Integer> nextfragment = new MutableLiveData<>();




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
                loginRepository.sendOTP(mobileNumber.getValue()).observeForever(new Observer<Result>() {
                    @Override
                    public void onChanged(Result result) {
                        resultMutableLiveData.setValue(result);
                        if (result.status == 200) {
                            showTime();
                        } else {
                            isErrorShown.setValue(false);
                        }
                        isloading.setValue(false);

                    }
                });
            } else {
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
                                    nextfragment.setValue(DASHBOARD);
                                } else {

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
}
