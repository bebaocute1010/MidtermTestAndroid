package com.example.midtermtest.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.midtermtest.MainActivity;
import com.example.midtermtest.Models.Product;
import com.example.midtermtest.Models.ProductOrder;
import com.example.midtermtest.R;

import java.util.ArrayList;
import java.util.List;

public class ProductOrderAdapter extends RecyclerView.Adapter<ProductOrderAdapter.ProductOrderViewHolder> {
    private List<ProductOrder> productOrderList = new ArrayList<>();
    private MainActivity mainActivity;

    public ProductOrderAdapter(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @NonNull
    @Override
    public ProductOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_order, parent, false);
        return new ProductOrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductOrderViewHolder holder, int position) {
        ProductOrder product = productOrderList.get(position);
        if (product != null) {
            holder.labelName.setText(product.product.getName());
            holder.labelNumber.setText("x" + product.quantity);
            holder.labelPrice.setText(MainActivity.formatDecimal(product.product.getPrice()));
            holder.layoutItemProductOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    reduceProduct(product.product);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return productOrderList != null ? productOrderList.size() : 0;
    }

    public void clearProductOrder() {
        productOrderList.clear();
        notifyDataSetChanged();
    }

    public void addProduct(Product product) {
        boolean isExists = false;
        for (ProductOrder productOrder: productOrderList) {
            if (productOrder.product.equals(product)) {
                productOrder.quantity++;
                isExists = true;
                break;
            }
        }
        if (!isExists) {
            productOrderList.add(new ProductOrder(product, 1));
        }
        notifyDataSetChanged();
    }

    public void reduceProduct(Product product) {
        for (ProductOrder productOrder: productOrderList) {
            if (productOrder.product.equals(product)) {
                if (productOrder.quantity == 1) {
                    productOrderList.remove(productOrder);
                } else {
                    productOrder.quantity--;
                }
                this.mainActivity.reduceProductOrder(product);
                notifyDataSetChanged();
                break;
            }
        }
    }

    public List<ProductOrder> getProductOrderList() {
        return productOrderList;
    }

    public class ProductOrderViewHolder extends RecyclerView.ViewHolder {
        private TextView labelName;
        private TextView labelNumber;
        private TextView labelPrice;
        private CardView layoutItemProductOrder;
        public ProductOrderViewHolder(@NonNull View itemView) {
            super(itemView);
            labelName = itemView.findViewById(R.id.label_name);
            labelNumber = itemView.findViewById(R.id.label_number);
            labelPrice = itemView.findViewById(R.id.label_price);
            layoutItemProductOrder = itemView.findViewById(R.id.layout_item_product_order);
        }

    }
}
