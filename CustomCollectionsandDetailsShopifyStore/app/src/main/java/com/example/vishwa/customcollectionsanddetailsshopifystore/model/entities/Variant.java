package com.example.vishwa.customcollectionsanddetailsshopifystore.model.entities;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Vishwa Patel
 */
public class Variant {

    @SerializedName("id")
    @PrimaryKey
    private long id;

    @SerializedName("inventory_quantity")
    private long inventoryQuantity;

    public Variant() {

    }

    public long getId() {
        return id;
    }

    public long getInventoryQuantity() {
        return inventoryQuantity;
    }
}
