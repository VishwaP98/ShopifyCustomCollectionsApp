package com.example.vishwa.customcollectionsanddetailsshopifystore.model.database.models;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Vishwa Patel
 */
public class CollectionProduct extends RealmObject {

    @SerializedName("id")
    private long id;

    @SerializedName("collection_id")
    private long collectionId;

    @SerializedName("product_id")
    @PrimaryKey
    private long productId;

    public CollectionProduct() {

    }

    public long getCollectionId() {
        return collectionId;
    }

    public long getId() {
        return id;
    }

    public long getProductId() {
        return productId;
    }
}
