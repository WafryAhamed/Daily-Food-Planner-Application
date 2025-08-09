package com.example.dishdiary.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;


public class AreaResponse {

    @SerializedName("meals")
    List<Area> areas;


    public void setAreas(List<Area> areas) {
        this.areas = areas;
    }
    public List<Area> getAreas() {
        return areas;
    }

}