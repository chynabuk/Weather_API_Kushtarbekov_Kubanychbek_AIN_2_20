package com.example.weatherapi.data;

import com.google.gson.annotations.SerializedName;

public class Condition {
    @SerializedName("text")
    private String condition;

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
}
