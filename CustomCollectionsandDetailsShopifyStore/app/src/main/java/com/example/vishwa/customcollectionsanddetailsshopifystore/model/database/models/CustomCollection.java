package com.example.vishwa.customcollectionsanddetailsshopifystore.model.database.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Vishwa Patel
 */
public class CustomCollection extends RealmObject {

    @PrimaryKey
    @SerializedName("id")
    @Expose
    private long id;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("image")
    @Expose
    private Image image;


    public CustomCollection() {

    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return image.getImageUrl();
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

}
