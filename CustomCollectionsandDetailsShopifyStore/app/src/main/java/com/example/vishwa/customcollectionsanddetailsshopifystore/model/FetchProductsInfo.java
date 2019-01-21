package com.example.vishwa.customcollectionsanddetailsshopifystore.model;

import android.content.Context;
import android.util.Log;

import com.example.vishwa.customcollectionsanddetailsshopifystore.model.database.WriteCommand;
import com.example.vishwa.customcollectionsanddetailsshopifystore.model.database.models.CollectionProduct;
import com.example.vishwa.customcollectionsanddetailsshopifystore.model.database.models.ProductInfo;
import com.example.vishwa.customcollectionsanddetailsshopifystore.model.entities.ProductInfoList;
import com.example.vishwa.customcollectionsanddetailsshopifystore.model.retrofit.GetProductsInfoService;
import com.example.vishwa.customcollectionsanddetailsshopifystore.model.retrofit.RetrofitClient;
import com.example.vishwa.customcollectionsanddetailsshopifystore.view.FetchProductsInfoInterface;
import com.example.vishwa.customcollectionsanddetailsshopifystore.view.RealmListener;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.vishwa.customcollectionsanddetailsshopifystore.view.MainActivity.accessString;
import static com.example.vishwa.customcollectionsanddetailsshopifystore.view.MainActivity.productsInfoUrl;

/**
 * Created by Vishwa Patel
 */
public class FetchProductsInfo implements RealmListener {

    private Context context;
    private List<CollectionProduct> collectionProducts;
    private FetchProductsInfoInterface infoInterface;

    public FetchProductsInfo(Context context, List<CollectionProduct> collectionProducts,
                             FetchProductsInfoInterface infoInterface) {
        this.context = context;
        this.collectionProducts = collectionProducts;
        this.infoInterface = infoInterface;
    }

    public void fetchProductsInfo() {

        // compute the product info from the collection products given
        Log.i("Hello", "The size of collectionProducts is " + collectionProducts.size());
        String updatedProductsInfoUrl = computeUrlForCollectionProducts(collectionProducts);

        GetProductsInfoService infoService = RetrofitClient.getRetrofitClient().create(GetProductsInfoService.class);

        Log.i("Hello ", updatedProductsInfoUrl + accessString);
        Call<ProductInfoList> productInfoCall = infoService.getProductsInfoService(updatedProductsInfoUrl + accessString);

        productInfoCall.enqueue(new Callback<ProductInfoList>() {

            @Override
            public void onResponse(Call<ProductInfoList> call, Response<ProductInfoList> response) {

                ProductInfoList infos = response.body();

                for(ProductInfo productInfo : infos.getProductInfos()) {
                    productInfo.computeInventoryQuantity();
                }

                WriteCommand.writeListData(context, infos.getProductInfos(), FetchProductsInfo.this);
            }

            @Override
            public void onFailure(Call<ProductInfoList> call, Throwable t) {

                infoInterface.onProductsFetched();
            }
        });

    }

    public String computeUrlForCollectionProducts(List<CollectionProduct> collectionProducts) {

        String updatedProductsInfoUrl = productsInfoUrl;

        for (CollectionProduct product : collectionProducts) {
            Log.i("Hello", " Product ---> " + product.getId() + " " + product.getCollectionId() + " " + product.getProductId());

            updatedProductsInfoUrl += String.valueOf(product.getProductId()) + ",";
        }

        updatedProductsInfoUrl = updatedProductsInfoUrl.substring(0, updatedProductsInfoUrl.length() - 1);

        return updatedProductsInfoUrl;
    }


    @Override
    public void onTaskDone() {

        infoInterface.onProductsFetched(); // writing is done now
    }
}
