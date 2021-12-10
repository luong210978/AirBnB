package com.example.airbnb.View.RoomDetail;

import com.example.airbnb.Model.Room;

public interface RoomDetailView {
    void hideLoading();
    void showLoading();
    void onErrorLoading(String message);
    void setRoom();
    void setRates();
}
