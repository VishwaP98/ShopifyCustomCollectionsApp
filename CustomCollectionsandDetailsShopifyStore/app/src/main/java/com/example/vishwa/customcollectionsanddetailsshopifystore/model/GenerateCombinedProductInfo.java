package com.example.vishwa.customcollectionsanddetailsshopifystore.model;

import android.content.Context;

import com.example.vishwa.customcollectionsanddetailsshopifystore.model.database.WriteCommand;
import com.example.vishwa.customcollectionsanddetailsshopifystore.model.database.models.CombinedProductInfo;
import com.example.vishwa.customcollectionsanddetailsshopifystore.model.database.models.CustomCollection;
import com.example.vishwa.customcollectionsanddetailsshopifystore.model.database.models.ProductInfo;
import com.example.vishwa.customcollectionsanddetailsshopifystore.view.CombinedProductInfoInterface;
import com.example.vishwa.customcollectionsanddetailsshopifystore.view.RealmListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vishwa Patel
 */
public class GenerateCombinedProductInfo implements RealmListener {

    private Context context;
    private CustomCollection collection;
    private CombinedProductInfoInterface infoInterface;
    private List<ProductInfo> productInfos;


    public GenerateCombinedProductInfo(Context context, CustomCollection collection,
                                            CombinedProductInfoInterface infoInterface,
                                            List<ProductInfo> productInfos) {

        this.context = context;
        this.collection = collection;
        this.infoInterface = infoInterface;
        this.productInfos = productInfos;
    }

    public void createCombinedProductInfo() {

        List<CombinedProductInfo> combinedProductInfoList = new ArrayList<>();

        for(ProductInfo productInfo : productInfos) {

            //Log.i("Hello ProductInfo --> ", productInfo.getTitle() + " " + productInfo.getInventoryQuantity());


            CombinedProductInfo combinedProductInfo = new CombinedProductInfo();
            combinedProductInfo.setProductId(productInfo.getProductId());
            combinedProductInfo.setCollectionId(collection.getId());
            combinedProductInfo.setCollectionTitle(collection.getTitle());
            combinedProductInfo.setCollectionImageUrl(collection.getImageUrl());
            combinedProductInfo.setProductInventory(productInfo.getInventoryQuantity());
            combinedProductInfo.setProductTitle(productInfo.getTitle());

            combinedProductInfoList.add(combinedProductInfo);
        }

        WriteCommand.writeListData(context, combinedProductInfoList, this); // write combinedProductInfo to database

    }

    @Override
    public void onTaskDone() {
        infoInterface.onProductInfosProcessed();
    }
}
