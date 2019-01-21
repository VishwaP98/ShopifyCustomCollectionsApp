package com.example.vishwa.customcollectionsanddetailsshopifystore.model.database.models;

import android.util.Log;

import com.example.vishwa.customcollectionsanddetailsshopifystore.model.entities.Variant;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Vishwa Patel
 */
public class ProductInfo extends RealmObject {

    @SerializedName("id")
    @PrimaryKey
    private long productId;

    @SerializedName("title")
    private String title;

    @SerializedName("variants")
    @Ignore
    private List<Variant> variants;

    private long inventoryQuantity = -1L;

    public ProductInfo() {

    }

    public void computeInventoryQuantity() {

        long quantityCount = 0;

        for(Variant variant : variants) {
            quantityCount += variant.getInventoryQuantity();
        }

        this.inventoryQuantity = quantityCount;
    }

    public String getTitle() {
        return title;
    }

    public List<Variant> getVariants() {
        return variants;
    }

    public long getProductId() {
        return productId;
    }

    public long getInventoryQuantity() {

        if(this.inventoryQuantity == -1){
            Log.i("Hello", String.valueOf(inventoryQuantity));
            computeInventoryQuantity();
        }

        return this.inventoryQuantity;
    }

}
