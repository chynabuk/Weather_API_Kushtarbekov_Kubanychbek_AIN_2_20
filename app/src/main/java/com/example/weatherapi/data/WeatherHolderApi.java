package com.example.weatherapi.data;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherHolderApi {
    @GET("/v1/forecast.json?key=05dbb88ed6bf44bfad4185023221305&aqi=no")
    Call<Weather> getWeather(@Query("q") String city);
}
