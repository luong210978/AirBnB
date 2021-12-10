package com.example.airbnb.View.RoomDetail;

import com.example.airbnb.Model.Room;

public class RoomDetailPresenter {
    RoomDetailView view;

    public RoomDetailPresenter(RoomDetailView view)
    {
        this.view = view;
    }

    void setRoom()
    {
        view.showLoading();
        view.setRoom();
        view.hideLoading();
    }

    void setRates()
    {
        view.setRates();
    }
}
