package com.eduvation.pecontest.Network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String BASE_URL="https://Chanjongp.pythonanywhere.com";

    public static RetrofitAPI getApiService(){
        return getInstance().create(RetrofitAPI.class);
    }
    private static Retrofit getInstance(){
        Gson gson=new GsonBuilder().setLenient().create();
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public static String getBaseUrl() {
        return BASE_URL;
    }
}
