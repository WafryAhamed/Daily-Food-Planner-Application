package com.example.dishdiary.datasources.network;

import com.example.dishdiary.model.Area;

import java.util.List;

public interface AreaNetworkCallback {
    void onAreaSuccessResult(List<Area> areas);
    void onAreaFailureResult(String errorMsg);
}
