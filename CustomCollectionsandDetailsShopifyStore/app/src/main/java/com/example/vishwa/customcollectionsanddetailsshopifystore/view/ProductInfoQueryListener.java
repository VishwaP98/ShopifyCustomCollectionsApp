package com.example.vishwa.customcollectionsanddetailsshopifystore.view;

import com.example.vishwa.customcollectionsanddetailsshopifystore.model.database.models.CombinedProductInfo;

import io.realm.RealmChangeListener;
import io.realm.RealmResults;

/**
 * Created by Vishwa Patel
 */
public interface ProductInfoQueryListener extends RealmChangeListener<RealmResults<CombinedProductInfo>> {

}
