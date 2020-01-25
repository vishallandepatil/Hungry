package com.example.hungry.retrofitsetting;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {
    public static String API_KEY="kjashdkahdkhaksjdhweshkhskjdhkj";
    private static Retrofit retrofit;
    private static final String BASE_URL = "http://hungryindia.co.in/";
    // private static final String BASE_URL = "https://jsonplaceholder.typicode.com";

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
