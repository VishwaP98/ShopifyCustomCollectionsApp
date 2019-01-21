package com.example.vishwa.customcollectionsanddetailsshopifystore.view;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.vishwa.customcollectionsanddetailsshopifystore.model.database.WriteCommand;
import com.example.vishwa.customcollectionsanddetailsshopifystore.model.entities.Collections;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Vishwa Patel
 */
public class CollectionsCallBack implements Callback<Collections>, RealmListener {

    private Context context;
    private CallBackInterface callBackInterface;

    public CollectionsCallBack(Context context, CallBackInterface callbackInterface) {
        this.context = context;
        this.callBackInterface = callbackInterface;
    }

    @Override
    public void onResponse(Call<Collections> call, Response<Collections> response) {
        Log.i("Hello", "Done computing results");
        Collections collection = response.body();

        WriteCommand.writeListData(context, collection.getCustomCollections(), this);

        Log.i("Hello", "Done Saving information");
    }

    @Override
    public void onFailure(Call<Collections> call, Throwable t) {
        Log.i("Hello", "Loading data from local database " + t.getMessage());
        this.callBackInterface.doneUpdatingCollections();
    }

    @Override
    public void onTaskDone() {
        // At this point, we are done writing the data
        callBackInterface.doneUpdatingCollections();
    }
}
