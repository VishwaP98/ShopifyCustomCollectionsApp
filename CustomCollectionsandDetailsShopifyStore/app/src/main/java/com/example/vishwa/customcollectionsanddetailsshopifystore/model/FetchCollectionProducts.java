package com.example.vishwa.customcollectionsanddetailsshopifystore.model;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.vishwa.customcollectionsanddetailsshopifystore.model.database.WriteCommand;
import com.example.vishwa.customcollectionsanddetailsshopifystore.model.entities.CollectionProducts;
import com.example.vishwa.customcollectionsanddetailsshopifystore.model.retrofit.GetCollectionProductsService;
import com.example.vishwa.customcollectionsanddetailsshopifystore.model.retrofit.RetrofitClient;
import com.example.vishwa.customcollectionsanddetailsshopifystore.view.FetchCollectionProductsInterface;
import com.example.vishwa.customcollectionsanddetailsshopifystore.view.MainActivity;
import com.example.vishwa.customcollectionsanddetailsshopifystore.view.RealmListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.vishwa.customcollectionsanddetailsshopifystore.view.MainActivity.accessString;

/**
 * Created by Vishwa Patel
 */
public class FetchCollectionProducts implements RealmListener {

    private Context context;
    private long collectionId;
    private String title;
    private FetchCollectionProductsInterface productsInterface;

    public FetchCollectionProducts(Context context, long collectionId, String title,
                                   FetchCollectionProductsInterface productsInterface) {
        this.context = context;
        this.collectionId = collectionId;
        this.title = title;
        this.productsInterface = productsInterface;
    }

    public void fetchProducts() {

        String updateCollectionsBaseUrl = MainActivity.collectionBaseUrl;

        GetCollectionProductsService productsService = RetrofitClient.getRetrofitClient().create(GetCollectionProductsService.class);

        updateCollectionsBaseUrl += (String.valueOf(collectionId));

        Log.i("Hello", updateCollectionsBaseUrl + accessString);

        final Call<CollectionProducts> productsCall = productsService.getCollectionProductsService(updateCollectionsBaseUrl + accessString);

        productsCall.enqueue(new Callback<CollectionProducts>() {
            @Override
            public void onResponse(Call<CollectionProducts> call, Response<CollectionProducts> response) {
                CollectionProducts collectionProducts = response.body();

                WriteCommand.writeListData(context, collectionProducts.getCollectionProducts(),FetchCollectionProducts.this);
            }

            @Override
            public void onFailure(Call<CollectionProducts> call, Throwable t) {
                productsInterface.onCollectionProductsFetched();
            }
        });
    }

    @Override
    public void onTaskDone() {
        productsInterface.onCollectionProductsFetched();
    }
}
