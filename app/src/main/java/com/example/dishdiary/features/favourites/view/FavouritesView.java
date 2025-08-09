package com.example.dishdiary.features.favourites.view;

import com.example.dishdiary.model.Meal;

import java.util.List;

public interface FavouritesView {

    public void showData(List<Meal> meals);
    public void showErrMsg(String error);
}
