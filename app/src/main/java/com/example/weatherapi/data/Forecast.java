package com.example.weatherapi.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Forecast {
    @SerializedName("forecastday")
    private List<Forecastday> forecastdays;

    public List<Forecastday> getForecastdays() {
        return forecastdays;
    }

    public void setForecastdays(List<Forecastday> forecastdays) {
        this.forecastdays = forecastdays;
    }
}
