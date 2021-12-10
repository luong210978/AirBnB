package com.example.airbnb.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Rate implements Serializable {
    @SerializedName("_id")
    @Expose
    private String _id;
    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("comment")
    @Expose
    private String comment;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("point")
    @Expose
    private float point;
    @SerializedName("precision")
    @Expose
    private int precision;
    @SerializedName("convenience")
    @Expose
    private int convenience;
    @SerializedName("located")
    @Expose
    private int located;
    @SerializedName("price")
    @Expose
    private int price;
    @SerializedName("attitude")
    @Expose
    private int attitude;

    public Rate(User user, String comment, int precision, int convenience, int located, int price, int attitude, String date) {
        this.user = user;
        this.comment = comment;
        this.precision = precision;
        this.convenience = convenience;
        this.located = located;
        this.price = price;
        this.attitude = attitude;
        this.date = date;

        this.point = (precision + convenience + located + price + attitude) / 5;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public float getPoint() {
        return point;
    }

    public void setPoint(float point) {
        this.point = point;
    }

    public int getPrecision() {
        return precision;
    }

    public void setPrecision(int precision) {
        this.precision = precision;
    }

    public int getConvenience() {
        return convenience;
    }

    public void setConvenience(int convenience) {
        this.convenience = convenience;
    }

    public int getLocated() {
        return located;
    }

    public void setLocated(int located) {
        this.located = located;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAttitude() {
        return attitude;
    }

    public void setAttitude(int attitude) {
        this.attitude = attitude;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
