package com.example.vishwa.customcollectionsanddetailsshopifystore.model.database.models;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Vishwa Patel
 */
public class CombinedProductInfo extends RealmObject {

    @PrimaryKey
    private long productId;

    private long collectionId;

    private String title; // collection title

    private String collectionImageUrl; // collection image url

    private String productTitle; // product title

    private long productInventory; // product inventory

    public CombinedProductInfo() {

    }

    public void setProductId(long productId) {this.productId = productId;}

    public void setCollectionId(long collectionId) {
        this.collectionId = collectionId;
    }

    public void setCollectionTitle(String title) {
        this.title = title;
    }

    public void setProductInventory(long productInventory) {
        this.productInventory = productInventory;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public void setCollectionImageUrl(String url) {
        this.collectionImageUrl = url;
    }

    public String getCollectionImageUrl() {
        return collectionImageUrl;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public long getProductInventory() {
        return productInventory;
    }

    public long getCollectionId() {
        return collectionId;
    }

    public String getCollectionTitle() {
        return title;
    }

    public Long getProductId() {
        return productId;
    }
}
