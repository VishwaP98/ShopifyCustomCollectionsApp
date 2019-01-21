package com.example.vishwa.customcollectionsanddetailsshopifystore.model.database.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by Vishwa Patel
 */
public class Image extends RealmObject {

    @SerializedName("src")
    @Expose
    private String imageUrl;

    @SerializedName("width")
    @Expose
    private Double width;

    @SerializedName("height")
    @Expose
    private Double height;

    public Image() {

    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Double getHeight() {
        return height;
    }

    public Double getWidth() {
        return width;
    }

}
