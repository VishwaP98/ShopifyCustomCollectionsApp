package com.example.vishwa.customcollectionsanddetailsshopifystore.view.viewHolder;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vishwa.customcollectionsanddetailsshopifystore.R;

/**
 * Created by Vishwa Patel
 */
public class ProductViewHolder extends ViewHolder {

    private ImageView imageView;
    private TextView collectionTitle;
    private TextView productTitle;
    private TextView productInventory;

    public ProductViewHolder(View itemView) {
        super(itemView);

        imageView = itemView.findViewById(R.id.collectionImage);
        collectionTitle = itemView.findViewById(R.id.collectionTitle);
        productTitle = itemView.findViewById(R.id.productTitle);
        productInventory = itemView.findViewById(R.id.productInventory);
    }

    public ImageView getImageView() {
        return imageView;
    }

    public TextView getCollectionTitleTextView() {
        return collectionTitle;
    }

    public TextView getProductInventoryTextView() {
        return productInventory;
    }

    public TextView getProductTitleTextView() {
        return productTitle;
    }

}
