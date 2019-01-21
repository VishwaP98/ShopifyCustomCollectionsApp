package com.example.vishwa.customcollectionsanddetailsshopifystore.model.entities;

import com.example.vishwa.customcollectionsanddetailsshopifystore.model.database.models.CollectionProduct;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Vishwa Patel
 */
public class CollectionProducts {

    @SerializedName("collects")
    List<CollectionProduct> collectionProducts;

    public CollectionProducts() {

    }

    public List<CollectionProduct> getCollectionProducts() {
        return collectionProducts;
    }
}
