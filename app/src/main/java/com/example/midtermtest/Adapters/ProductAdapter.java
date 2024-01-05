package com.example.midtermtest.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.midtermtest.MainActivity;
import com.example.midtermtest.Models.Product;
import com.example.midtermtest.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private MainActivity mainActivity;
    private List<Product> productList;

    public ProductAdapter(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void setData(List<Product> products) {
        productList = products;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        if (product != null) {
            try {
                Picasso.get().load(product.getImage()).into(holder.image);
                holder.labelName.setText("Tên: " + product.getName());
                holder.labelDescription.setText("Mô tả: " + product.getDescription());
                holder.labelPrice.setText("Giá: " + MainActivity.formatDecimal(product.getPrice()));
                holder.layoutItemProduct.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mainActivity.addProductToOrder(product);
                    }
                });
            } catch (Exception e) {
                Log.e("ProductViewHolder", e.getMessage());
            }
        }
    }

    @Override
    public int getItemCount() {
        return productList != null ? productList.size() : 0;
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {

        private ImageView image;
        private TextView labelName;
        private TextView labelDescription;
        private TextView labelPrice;
        private CardView layoutItemProduct;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image_product);
            labelName = itemView.findViewById(R.id.label_name);
            labelDescription = itemView.findViewById(R.id.label_description);
            labelPrice = itemView.findViewById(R.id.label_price);
            layoutItemProduct = itemView.findViewById(R.id.layout_item_product);
        }
    }
}
