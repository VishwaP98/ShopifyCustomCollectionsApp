package com.example.vishwa.customcollectionsanddetailsshopifystore.model.database;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.vishwa.customcollectionsanddetailsshopifystore.view.RealmListener;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmObject;

/**
 * Created by Vishwa Patel
 */
public class WriteCommand {

    public static <T extends RealmObject> void writeListData(final Context context, final List<T> list) {
        // asynchronous
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.insert(list);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Log.i("Hello", "Success storing data");
                Toast.makeText(context, "Data successfully stored in local database", Toast.LENGTH_SHORT);
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Log.i("Hello", "Failure storing data " + error.getMessage());
                // Transaction failed and was automatically canceled.
                Toast.makeText(context, "Problems while storing in local database", Toast.LENGTH_SHORT);
            }
        });
        realm.close();
    }

    public static <T extends RealmObject> void writeListData(final Context context, final List<T> list, final RealmListener realmListener) {
        // asynchronous
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.insert(list);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Log.i("Hello", "Success storing data");
                Toast.makeText(context, "Data successfully stored in local database", Toast.LENGTH_SHORT);
                realmListener.onTaskDone();
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Log.i("Hello", "Failure storing data " + error.getMessage());
                // Transaction failed and was automatically canceled.
                Toast.makeText(context, "Problems while storing in local database", Toast.LENGTH_SHORT);
                realmListener.onTaskDone();
            }
        });
        realm.close();
    }

    public static <T extends RealmObject> void writeData(final Context context, final T object) {
        // asynchronous
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.insert(object);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Log.i("Hello", "Success storing data");
                Toast.makeText(context, "Data successfully stored in local database", Toast.LENGTH_SHORT);
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Log.i("Hello", "Failure in storing data " + error.getMessage());
                Toast.makeText(context, "Problems while storing in local database", Toast.LENGTH_SHORT);
            }
        });

        realm.close();
    }

}
