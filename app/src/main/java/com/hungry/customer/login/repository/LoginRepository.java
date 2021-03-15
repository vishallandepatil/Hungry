package com.hungry.customer.login.repository;

import com.hungry.customer.login.api.City;
import com.hungry.customer.login.api.OTP;
import com.hungry.customer.login.api.Registration;
import com.hungry.customer.login.model.CityResult;
import com.hungry.customer.login.model.Result;
import com.hungry.customer.login.model.ResultVerifyOTP;
import com.hungry.customer.login.model.Token;
import com.hungry.customer.login.model.User;
import com.hungry.customer.retrofitsetting.RetrofitClientInstance;

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

    public MutableLiveData<CityResult> getCity(String stateid){

        City city = RetrofitClientInstance.getRetrofitInstance().create(City.class);
        final MutableLiveData<CityResult> resultMutableLiveData = new MutableLiveData<>();
        Call<CityResult> call = city.getCity(RetrofitClientInstance.API_KEY,stateid);
        final CityResult result = new CityResult();

        call.enqueue(new Callback<CityResult>() {
            @Override
            public void onResponse(Call<CityResult> call, Response<CityResult> response) {
                CityResult data = response.body();
                resultMutableLiveData.setValue(data);

            }

            @Override
            public void onFailure(Call<CityResult> call, Throwable t) {
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

    public MutableLiveData<ResultVerifyOTP> registerUser(User user){

        Registration city = RetrofitClientInstance.getRetrofitInstance().create(Registration.class);
        final MutableLiveData<ResultVerifyOTP> resultMutableLiveData = new MutableLiveData<>();
        Call<ResultVerifyOTP> call = city.registerUser(RetrofitClientInstance.API_KEY,user.getCM_MOBILE(),user.getCM_FIRST_NAME(),user.getCM_LAST_NAME(),user.getCM_EMAIL(),user.getSTATE_MASTER_ID()+"",""+user.getCITY_MASTER_ID(),user.getCM_ADDRESS(),user.getCM_GENDER(),"A");
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


    public MutableLiveData<ResultVerifyOTP> updateUser(User user){

        Registration city = RetrofitClientInstance.getRetrofitInstance().create(Registration.class);
        final MutableLiveData<ResultVerifyOTP> resultMutableLiveData = new MutableLiveData<>();
        Call<ResultVerifyOTP> call = city.update(RetrofitClientInstance.API_KEY,user.getCM_MOBILE(),""+user.getCITY_MASTER_ID());
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


    public MutableLiveData<Token> registerToken(String clientid,String mobile,String token, String tokentype,String android_id,String deviceid){

        Registration city = RetrofitClientInstance.getRetrofitInstance().create(Registration.class);
        final MutableLiveData<Token> resultMutableLiveData = new MutableLiveData<>();
        Call<Token> call = city.token(RetrofitClientInstance.API_KEY,clientid,mobile,token,tokentype,android_id,deviceid);

        call.enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                Token token1=response.body();

            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {

                 t.printStackTrace();

            }
        });
        return resultMutableLiveData;

    }

}
