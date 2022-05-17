package com.example.weatherapi.data;

import com.google.gson.annotations.SerializedName;

public class Current {
    @SerializedName("condition")
    private Condition condition;

    @SerializedName("temp_c")
    private double temp_c;

    @SerializedName("humidity")
    private int humidity;

    @SerializedName("pressure_mb")
    private double pressure;

    @SerializedName("wind_kph")
    private double wind;

    @SerializedName("cloud")
    private int cloud;

    @SerializedName("is_day")
    private int isDay;

    public int isDay() {
        return isDay;
    }

    public void setDay(int day) {
        isDay = day;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public double getTemp_c() {
        return temp_c;
    }

    public void setTemp_c(double temp_c) {
        this.temp_c = temp_c;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public double getWind() {
        return wind;
    }

    public void setWind(double wind) {
        this.wind = wind;
    }

    public int getCloud() {
        return cloud;
    }

    public void setCloud(int cloud) {
        this.cloud = cloud;
    }
}
