package com.example.hungry.login.repository;

import com.example.hungry.login.api.OTP;
import com.example.hungry.login.model.Result;
import com.example.hungry.login.model.ResultVerifyOTP;
import com.example.hungry.retrofitsetting.RetrofitClientInstance;

import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRepository {
    public MutableLiveData<Result> sendOTP(String mobile)
    {
         OTP otp = RetrofitClientInstance.getRetrofitInstance().create(OTP.class);
         final MutableLiveData<Result> resultMutableLiveData = new MutableLiveData<>();
        Call<Result> call = otp.sendOTP(RetrofitClientInstance.API_KEY,mobile,"A");
        final Result result = new Result();
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                Result data = response.body();
                resultMutableLiveData.setValue(data);


            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {

                if(t.getLocalizedMessage().equalsIgnoreCase("Unable to resolve host \"hungryindia.co.in\": No address associated with hostname"))
                { result.setMessage("Please Check Enternet Connection");

                } else {
                    result.setMessage(t.getLocalizedMessage());
                }
                resultMutableLiveData.setValue(result);


            }
        });
        return  resultMutableLiveData;
    }

    public MutableLiveData<ResultVerifyOTP> verifyOTP(String mobile,String otp){
        OTP otp2 = RetrofitClientInstance.getRetrofitInstance().create(OTP.class);
        final MutableLiveData<ResultVerifyOTP> resultMutableLiveData = new MutableLiveData<>();
        Call<ResultVerifyOTP> call = otp2.verifyOTP(RetrofitClientInstance.API_KEY,mobile,otp);
        final ResultVerifyOTP result = new ResultVerifyOTP();

        call.enqueue(new Callback<ResultVerifyOTP>() {
            @Override
            public void onResponse(Call<ResultVerifyOTP> call, Response<ResultVerifyOTP> response) {
                ResultVerifyOTP data = response.body();
                resultMutableLiveData.setValue(data);

            }

            @Override
            public void onFailure(Call<ResultVerifyOTP> call, Throwable t) {
                if(t.getLocalizedMessage().equalsIgnoreCase("Unable to resolve host \"hungryindia.co.in\": No address associated with hostname"))
                { result.setMessage("Please Check Enternet Connection");

                } else {
                    result.setMessage(t.getLocalizedMessage());
                }
                resultMutableLiveData.setValue(result);


            }
        });

       return resultMutableLiveData;
    }
}
