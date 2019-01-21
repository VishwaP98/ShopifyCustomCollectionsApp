package com.example.vishwa.customcollectionsanddetailsshopifystore.model.retrofit;

import com.example.vishwa.customcollectionsanddetailsshopifystore.model.entities.Collections;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Vishwa Patel
 */
public interface GetCollectionsService {

    @GET("/admin/custom_collections.json?page=1&access_token=c32313df0d0ef512ca64d5b336a0d7c6")
    Call<Collections> getCollectionsService();
}
