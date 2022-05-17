package com.example.weatherapi.data;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Location {
    @SerializedName("name")
    private String cityName;

    @SerializedName("localtime")
    private Date localtime;


    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Date getLocaltime() {
        return localtime;
    }

    public void setLocaltime(Date localtime) {
        this.localtime = localtime;
    }
}
