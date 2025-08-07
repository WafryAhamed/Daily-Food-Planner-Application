package com.example.dishdiary.features.home.view;

import com.example.dishdiary.model.Meal;

import java.util.List;

public interface HomeView {

    public void showData(List<Meal> meals);
    public void showErrMsg(String error);
}
