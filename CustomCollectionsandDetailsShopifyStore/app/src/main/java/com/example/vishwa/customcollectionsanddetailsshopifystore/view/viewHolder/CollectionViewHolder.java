package com.example.vishwa.customcollectionsanddetailsshopifystore.view.viewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.vishwa.customcollectionsanddetailsshopifystore.R;

/**
 * Created by Vishwa Patel
 */
public class CollectionViewHolder extends RecyclerView.ViewHolder {

    private TextView title;

    public CollectionViewHolder(@NonNull View itemView) {
        super(itemView);

        title = itemView.findViewById(R.id.title);
    }

    public TextView getTitleTextView() {
        return title;
    }

}
