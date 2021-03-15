package com.hungry.customer.retrofitsetting;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {
    public static String API_KEY="kjashdkahdkhaksjdhweshkhskjdhkj";
    private static Retrofit retrofit;
    private static final String BASE_URL = "http://hungryindia.co.in/";
    // private static final String BASE_URL = "https://jsonplaceholder.typicode.com";
    


    public static Retrofit getRetrofitInstance() {
        
        if (retrofit == null) {
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                    .callTimeout(2, TimeUnit.MINUTES)
                    .connectTimeout(2, TimeUnit.MINUTES)
                    .readTimeout(2, TimeUnit.MINUTES)
                    .writeTimeout(3, TimeUnit.MINUTES);
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL).client(httpClient.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return retrofit;
    }
}
