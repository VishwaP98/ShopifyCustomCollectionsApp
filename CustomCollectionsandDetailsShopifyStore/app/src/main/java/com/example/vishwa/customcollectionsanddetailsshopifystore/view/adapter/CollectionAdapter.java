package com.example.vishwa.customcollectionsanddetailsshopifystore.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vishwa.customcollectionsanddetailsshopifystore.R;
import com.example.vishwa.customcollectionsanddetailsshopifystore.model.database.models.CustomCollection;
import com.example.vishwa.customcollectionsanddetailsshopifystore.view.CustomCollectionListener;
import com.example.vishwa.customcollectionsanddetailsshopifystore.view.viewHolder.CollectionViewHolder;

import io.realm.RealmResults;

/**
 * Created by Vishwa Patel
 */
public class CollectionAdapter extends RecyclerView.Adapter<CollectionViewHolder> {

    private Context context;
    private RealmResults<CustomCollection> data;
    private LayoutInflater inflater;
    private CustomCollectionListener customCollectionListener;

    public CollectionAdapter(Context context, RealmResults<CustomCollection> data, CustomCollectionListener listener) {
        this.context = context;
        this.data = data;
        inflater = LayoutInflater.from(context);
        this.customCollectionListener = listener;
    }

    @NonNull
    @Override
    public CollectionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.collections_item, parent, false);

        view.setOnClickListener(customCollectionListener);
        CollectionViewHolder viewHolder = new CollectionViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CollectionViewHolder holder, int position) {

        Log.i("Hello", position + " " + holder);

        holder.getTitleTextView().setText(data.get(position).getTitle());
        holder.getTitleTextView().setTextSize(27);
        holder.getTitleTextView().setOnClickListener(customCollectionListener);
    }

    @Override
    public int getItemCount() {

        if(data == null) {
            return 0;
        }

        return data.size();
    }
}
