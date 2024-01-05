package com.example.midtermtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.midtermtest.Adapters.ProductAdapter;
import com.example.midtermtest.Adapters.ProductOrderAdapter;
import com.example.midtermtest.Database.AppDatabase;
import com.example.midtermtest.Models.Order;
import com.example.midtermtest.Models.Product;
import com.example.midtermtest.Models.ProductOrder;
import com.example.midtermtest.Models.User;
import com.google.gson.Gson;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private static final int DEFAULT_USER_ID = -1;
    private User userLoggedIn;
    private List<Product> productList;
    private ProductAdapter productAdapter;
    private ProductOrderAdapter productOrderAdapter;
    private RecyclerView recyclerViewProductList;
    private RecyclerView recyclerViewProductOrderList;
    private TextView labelName;
    private TextView labelTotalExpense;
    private Button btnCreateOrder;
    private List<Product> productsOrder = new ArrayList<>();
    private int total_expenses = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialization();

        getUserLoggedIn();
        getListProduct();
        productAdapter = new ProductAdapter(this);
        productAdapter.setData(productList);
        productOrderAdapter = new ProductOrderAdapter(this);

        recyclerViewProductList.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerViewProductList.setAdapter(productAdapter);

        recyclerViewProductOrderList.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewProductOrderList.setAdapter(productOrderAdapter);

        btnCreateOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createOrder(productOrderAdapter.getProductOrderList());
            }
        });
    }

    public void createOrder(List<ProductOrder> productOrderList) {
        String products = new Gson().toJson(productOrderList);
        Order order = new Order(userLoggedIn.getId(), products, total_expenses);
        AppDatabase.getInstance(getContext()).orderDao().insert(order);
        productOrderAdapter.clearProductOrder();
        total_expenses = 0;
        setTextLabelTotalExpense();
        Toast.makeText(getContext(), "Tạo đơn hàng thành công", Toast.LENGTH_SHORT).show();
    }

    public static String formatDecimal(int number) {
        NumberFormat format = DecimalFormat.getCurrencyInstance(new Locale("vi", "VN"));
        String formattedAmount = format.format(number);
        return formattedAmount;
    }

    public void addProductToOrder(Product product) {
        productOrderAdapter.addProduct(product);
        total_expenses += product.getPrice();
        setTextLabelTotalExpense();
    }

    public void reduceProductOrder(Product product) {
        total_expenses -= product.getPrice();
        setTextLabelTotalExpense();
    }

    public void setTextLabelTotalExpense() {
        labelTotalExpense.setText("Tổng tiền: " + formatDecimal(total_expenses));
    }

    private void getListProduct() {
        productList = AppDatabase.getInstance(getContext()).productDao().getAll();
    }

    private void getUserLoggedIn() {
        Intent intent = getIntent();
        Integer userId = intent.getIntExtra("user_id", DEFAULT_USER_ID);
        if (userId != DEFAULT_USER_ID) {
            userLoggedIn = AppDatabase.getInstance(getContext()).userDao().find(userId);
        }
        labelName.setText(userLoggedIn != null ? "Nhân viên: " + userLoggedIn.getName() : "NULL");
    }

    private void initialization() {
        labelName = findViewById(R.id.label_name);
        labelTotalExpense = findViewById(R.id.label_total_expense);
        btnCreateOrder = findViewById(R.id.btn_create_order);
        recyclerViewProductList = findViewById(R.id.recyclerview_list_product);
        recyclerViewProductOrderList = findViewById(R.id.recyclerview_list_product_order);
    }

    private Context getContext() {
        return MainActivity.this.getApplicationContext();
    }
}