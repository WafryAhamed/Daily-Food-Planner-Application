package com.example.dishdiary.model;

import com.google.gson.annotations.SerializedName;

public class Area {

    @SerializedName("strArea")
    String strArea;

    public void setStrArea(String strArea) {
        this.strArea = strArea;
    }
    public String getStrArea() {
        return strArea;
    }

}