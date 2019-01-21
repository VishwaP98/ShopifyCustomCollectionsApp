package com.example.vishwa.customcollectionsanddetailsshopifystore.model.entities;

import com.example.vishwa.customcollectionsanddetailsshopifystore.model.database.models.CustomCollection;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Vishwa Patel
 */
public class Collections {

    @SerializedName("custom_collections")
    @Expose
    private List<CustomCollection> customCollections;

    public List<CustomCollection> getCustomCollections() {
        return customCollections;
    }
}
