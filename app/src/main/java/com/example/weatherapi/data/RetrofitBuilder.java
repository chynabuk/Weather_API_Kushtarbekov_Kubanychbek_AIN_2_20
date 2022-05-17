package com.example.weatherapi.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {
    public RetrofitBuilder() {
    }

    private static WeatherHolderApi instance;

    public static WeatherHolderApi getInstance() {
        if (instance == null){
            instance = init();
        }
        return instance;
    }

    public static WeatherHolderApi init() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm")
                .create();

        return new Retrofit
                .Builder()
                .baseUrl("http://api.weatherapi.com")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(WeatherHolderApi.class);
    }
}
