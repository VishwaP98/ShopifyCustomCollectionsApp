package com.example.vishwa.customcollectionsanddetailsshopifystore.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.vishwa.customcollectionsanddetailsshopifystore.R;
import com.example.vishwa.customcollectionsanddetailsshopifystore.model.FetchCollectionProducts;
import com.example.vishwa.customcollectionsanddetailsshopifystore.model.FetchProductsInfo;
import com.example.vishwa.customcollectionsanddetailsshopifystore.model.GenerateCombinedProductInfo;
import com.example.vishwa.customcollectionsanddetailsshopifystore.model.database.ReadCommand;
import com.example.vishwa.customcollectionsanddetailsshopifystore.model.database.models.CollectionProduct;
import com.example.vishwa.customcollectionsanddetailsshopifystore.model.database.models.CombinedProductInfo;
import com.example.vishwa.customcollectionsanddetailsshopifystore.model.database.models.CustomCollection;
import com.example.vishwa.customcollectionsanddetailsshopifystore.model.database.models.ProductInfo;
import com.example.vishwa.customcollectionsanddetailsshopifystore.view.adapter.ProductsAdapter;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class CollectionDetailsActivity extends AppCompatActivity implements FetchCollectionProductsInterface,
                                                                            FetchProductsInfoInterface,
                                                                        CombinedProductInfoInterface,
                                                                        ProductInfoQueryListener {

    private RecyclerView recyclerView;
    private long collectionId;
    private List<CollectionProduct> collectionProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collections_list);

        recyclerView = findViewById(R.id.products_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        // extract information from intent

        collectionId = (long) getIntent().getExtras().get("collectionId");
        String title = (String) getIntent().getExtras().get("title");

        Log.i("Hello", "Collection id : " + collectionId + " - " + title + " selected");


        // Need to extract the list of products in this particular collection

        FetchCollectionProducts fetchCollectionProducts = new FetchCollectionProducts(this,
                                                        collectionId, title, this);

        fetchCollectionProducts.fetchProducts();

    }

    @Override
    public void onCollectionProductsFetched() {

        Log.i("Hello", "Here in products fetched");

        RealmResults<CollectionProduct> result = ReadCommand.queryReadLong(CollectionProduct.class,
                                                                    "collectionId", collectionId);

        collectionProducts = new ArrayList<>();

        for (CollectionProduct product : result) {
            Log.i("Hello", product.getId() + " " + product.getCollectionId() + " " + product.getProductId());

            collectionProducts.add(product);
        }


        // Beyond this point, we need to get more information on each and every product that we got
        // from the collection

        FetchProductsInfo fetchProductsInfo = new FetchProductsInfo(this, collectionProducts, this);

        fetchProductsInfo.fetchProductsInfo();
    }

    @Override
    public void onProductsFetched() {

        // At this point, the products have been fetched
        // great time to make CombinedProductInfo - > including the collection info as well as other info

        Log.i("Hello", collectionId + " is the collectionId");

        RealmResults<CustomCollection> queryResult = ReadCommand.queryReadLong(CustomCollection.class,
                                                        "id", collectionId);

        Log.i("Hello", queryResult.size() + " is the size of the queryResult");

        CustomCollection customCollection = queryResult.get(0); // since id is a primary key, there should not be duplicates

        Log.i("Hello", "collection title: " + customCollection.getTitle() + " " +
                                                "imageUrl:" + customCollection.getImageUrl());


        Long[] productIds = new Long[collectionProducts.size()];

        int index = 0;
        for(CollectionProduct product : collectionProducts) {
            productIds[index] = product.getProductId();
            index++;
        }

        RealmResults<ProductInfo> realmResults = Realm.getDefaultInstance().where(ProductInfo.class)
                                                            .in("productId", productIds)
                                                            .findAllAsync();


        List<ProductInfo> productInfos = new ArrayList<>();

        for(ProductInfo productInfo : realmResults){
            Log.i("Hello", "Product id is" + productInfo.getProductId());
            productInfos.add(productInfo);
        }

        GenerateCombinedProductInfo generateCombinedProductInfo = new GenerateCombinedProductInfo(this,
                                                                       customCollection, this, productInfos);

        generateCombinedProductInfo.createCombinedProductInfo();

    }

    @Override
    public void onProductInfosProcessed() {

        ReadCommand.queryReadLongAsync(CombinedProductInfo.class,"collectionId", collectionId, this);

    }

    @Override
    public void onChange(RealmResults<CombinedProductInfo> productInfos) {
        // Now we can set the recycler view

        // here we are done reading and so set the adapter

        ProductsAdapter productsAdapter = new ProductsAdapter(this, productInfos);

        Log.i("Hello", " Size of results is " + productsAdapter.getItemCount());

        recyclerView.setAdapter(productsAdapter);

    }
}
