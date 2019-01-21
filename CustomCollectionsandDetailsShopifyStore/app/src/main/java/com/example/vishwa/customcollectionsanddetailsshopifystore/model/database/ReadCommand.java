package com.example.vishwa.customcollectionsanddetailsshopifystore.model.database;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * Created by Vishwa Patel
 */
public class ReadCommand {

    public static <T extends RealmObject> RealmResults<T> defaultread(Class<T> type) {

        RealmResults<T> result = Realm.getDefaultInstance().where(type).findAll();

        return result;
    }


    public static <T extends RealmObject> RealmResults<T> queryReadLong(Class<T> type, String fieldName, Long fieldValue) {

        return Realm.getDefaultInstance().where(type).equalTo(fieldName, fieldValue).findAll();
    }

    public static <T extends RealmObject> void queryReadLongAsync(Class<T> type, String fieldName, Long fieldValue
                                                                            ,RealmChangeListener<RealmResults<T>> listener) {

       Realm.getDefaultInstance().where(type).equalTo(fieldName, fieldValue).
                                                findAllAsync().addChangeListener(listener);
    }


    public static <T extends RealmObject, U> RealmResults<T> queryReadString(Class<T> type, String fieldName, String fieldValue) {

        RealmResults<T> result = Realm.getDefaultInstance().where(type).equalTo(fieldName, fieldValue).findAllAsync();

        return result;
    }


}
