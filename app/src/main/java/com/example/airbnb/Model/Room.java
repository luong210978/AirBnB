package com.example.airbnb.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Room implements Serializable {

    public static final String CONVENIENCE_WIFI = "wifi miễn phí";
    public static final String CONVENIENCE_KITCHEN = "Bếp";
    public static final String CONVENIENCE_PARKING = "Đỗ xe miễn phí";
    public static final String CONVENIENCE_REFRIGERATOR = "Tủ lạnh";
    public static final String CONVENIENCE_AIR_CONDITIONER = "Điều hòa";


    @SerializedName("_id")
    @Expose
    private String _id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("images")
    @Expose
    private ArrayList<String> images;
    @SerializedName("layout")
    @Expose
    private String layout;
    @SerializedName("conveniences")
    @Expose
    private List<String> conveniences;
    @SerializedName("rules")
    @Expose
    private List<String> rules;
    @SerializedName("price")
    @Expose
    private int price;
    @SerializedName("rates")
    @Expose
    private List<Rate> rates;

    public Room(String _id, String name, String location, String description, ArrayList<String> images, String layout, List<String> conveniences) {
        this._id = _id;
        this.name = name;
        this.location = location;
        this.description = description;
        this.images = images;
        this.layout = layout;
        this.conveniences = conveniences;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<String> getImages() {
        return images;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }

    public String getLayout() {
        return layout;
    }

    public void setLayout(String layout) {
        this.layout = layout;
    }

    public List<String> getConveniences() {
        return conveniences;
    }

    public void setConveniences(List<String> conveniences) {
        this.conveniences = conveniences;
    }

    public List<String> getRules() { return rules; }

    public void setRules(List<String> rules) { this.rules = rules; }

    public int getPrice() { return price; }

    public void setPrice(int price) { this.price = price; }

    public List<Rate> getRates() {
        return rates;
    }

    public void setRates(List<Rate> rates) {
        this.rates = rates;
    }
}
