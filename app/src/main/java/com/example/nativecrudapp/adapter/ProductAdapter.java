package com.example.nativecrudapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.nativecrudapp.R;
import com.example.nativecrudapp.model.Product;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    List<Product> productList;
    OnItemClickListener listener;

    public ProductAdapter(List<Product> productList) {
        this.productList = productList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = productList.get(position);

        holder.txtTitle.setText(product.getTitle());
        holder.txtPrice.setText("Rp " + product.getPrice());
        holder.itemView.setOnClickListener(v -> {
            listener.onItemClick(product);
        });

        Glide.with(holder.itemView.getContext())
                .load(product.getImage())
                .into(holder.imgProduct);

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgProduct;
        TextView txtTitle, txtPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgProduct = itemView.findViewById(R.id.imgProduct);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtPrice = itemView.findViewById(R.id.txtPrice);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Product product);
    }

    public ProductAdapter(List<Product> productList, OnItemClickListener listener) {
        this.productList = productList;
        this.listener = listener;
    }
}