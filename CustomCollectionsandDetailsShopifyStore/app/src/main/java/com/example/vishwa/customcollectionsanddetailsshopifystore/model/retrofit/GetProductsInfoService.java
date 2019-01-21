package com.example.vishwa.customcollectionsanddetailsshopifystore.model.retrofit;

import com.example.vishwa.customcollectionsanddetailsshopifystore.model.entities.ProductInfoList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by Vishwa Patel
 */
public interface GetProductsInfoService {

    @GET
    Call<ProductInfoList> getProductsInfoService(@Url String url);
}
