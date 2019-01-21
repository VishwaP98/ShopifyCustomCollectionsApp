package com.example.vishwa.customcollectionsanddetailsshopifystore.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.vishwa.customcollectionsanddetailsshopifystore.R;
import com.example.vishwa.customcollectionsanddetailsshopifystore.model.database.models.CombinedProductInfo;
import com.example.vishwa.customcollectionsanddetailsshopifystore.view.viewHolder.ProductViewHolder;

import java.util.List;

/**
 * Created by Vishwa Patel
 */
public class ProductsAdapter extends RecyclerView.Adapter<ProductViewHolder> {

    private Context context;
    private List<CombinedProductInfo> combinedProductInfos;
    private LayoutInflater inflater;

    public ProductsAdapter(Context context, List<CombinedProductInfo> combinedProductInfoList) {
        this.context = context;
        this.combinedProductInfos = combinedProductInfoList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.products_item, parent, false);

        ProductViewHolder productViewHolder = new ProductViewHolder(view);

        return productViewHolder;
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {


        holder.getProductTitleTextView().setText(context.getString(R.string.productTag) + " " +
                combinedProductInfos.get(position).getProductTitle());
        holder.getProductTitleTextView().setTextSize(23);
        holder.getCollectionTitleTextView().setText(context.getString(R.string.collectionTag) + " " +
                combinedProductInfos.get(position).getCollectionTitle());
        holder.getCollectionTitleTextView().setTextSize(20);
        holder.getProductInventoryTextView().setText(context.getString(R.string.inventoryTag) + " " +
                String.valueOf(combinedProductInfos.get(position).getProductInventory()));
        holder.getProductInventoryTextView().setTextSize(18);

        String imageUrl = combinedProductInfos.get(position).getCollectionImageUrl();

        Glide.with(context).load(imageUrl).
                apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)).into(holder.getImageView());

    }

    @Override
    public int getItemCount() {
        if(combinedProductInfos == null) {
            return 0;
        }

        return combinedProductInfos.size();
    }

}
