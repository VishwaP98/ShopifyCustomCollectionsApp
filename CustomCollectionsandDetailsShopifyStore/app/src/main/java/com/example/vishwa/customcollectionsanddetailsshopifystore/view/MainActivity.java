package com.example.vishwa.customcollectionsanddetailsshopifystore.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.vishwa.customcollectionsanddetailsshopifystore.R;
import com.example.vishwa.customcollectionsanddetailsshopifystore.model.database.ReadCommand;
import com.example.vishwa.customcollectionsanddetailsshopifystore.model.entities.Collections;
import com.example.vishwa.customcollectionsanddetailsshopifystore.model.database.models.CustomCollection;
import com.example.vishwa.customcollectionsanddetailsshopifystore.model.retrofit.GetCollectionsService;
import com.example.vishwa.customcollectionsanddetailsshopifystore.model.retrofit.RetrofitClient;
import com.example.vishwa.customcollectionsanddetailsshopifystore.view.adapter.CollectionAdapter;
import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;

import io.realm.RealmResults;
import retrofit2.Call;

public class MainActivity extends AppCompatActivity implements CallBackInterface, CustomCollectionListener {

    public static final String collectionBaseUrl = "admin/collects.json?collection_id=";
    public static final String productsInfoUrl = "admin/products.json?ids=";
    public static final String accessString = "&page=1&access_token=c32313df0d0ef512ca64d5b336a0d7c6";
    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i("Hello", "Start here");

        GetCollectionsService service = RetrofitClient.getRetrofitClient().create(GetCollectionsService.class);
        Call<Collections> collections = service.getCollectionsService();


        FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(this);

        flexboxLayoutManager.setJustifyContent(JustifyContent.CENTER);

        flexboxLayoutManager.setAlignItems(AlignItems.CENTER);

        recyclerView = findViewById(R.id.RecyclerView);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        recyclerView.setLayoutManager(flexboxLayoutManager);
        recyclerView.setVisibility(View.INVISIBLE);

        collections.enqueue(new CollectionsCallBack(this, this));

    }

    @Override
    public void doneUpdatingCollections() {
        // read here - > updated collections from the database
        Log.i("Hello", "Done updating collections");

        RealmResults<CustomCollection> result = ReadCommand.defaultread(CustomCollection.class);

        CollectionAdapter adapter = new CollectionAdapter(this, result, this);
        Log.i("Hello", " Size of results is " + adapter.getItemCount());

        progressBar.setVisibility(View.INVISIBLE);
        recyclerView.setVisibility(View.VISIBLE);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        TextView textView = v.findViewById(R.id.title);

        String title = textView.getText().toString();

        RealmResults<CustomCollection> collectionIdResult = ReadCommand.queryReadString(CustomCollection.class,
                "title", title);

        long collectionId = collectionIdResult.get(0).getId();


        // Move to the CollectionDetailsActivity from here
        Intent intent = new Intent(this, CollectionDetailsActivity.class);
        intent.putExtra("collectionId", collectionId);
        intent.putExtra("title", title);
        startActivity(intent);
    }

    @Override
    public void onDestroy() {
        recyclerView.setVisibility(View.INVISIBLE);
        super.onDestroy();
    }
}