package com.example.vishwa.customcollectionsanddetailsshopifystore.model.entities;

import com.example.vishwa.customcollectionsanddetailsshopifystore.model.database.models.ProductInfo;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Vishwa Patel
 */
public class ProductInfoList {

    @SerializedName("products")
    List<ProductInfo> productInfos;

    public ProductInfoList() {

    }

    public List<ProductInfo> getProductInfos() {
        return productInfos;
    }
}
