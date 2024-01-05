package com.example.midtermtest.Models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Timestamp;
import java.util.Date;

@Entity(tableName = "orders")
public class Order {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private int user_id;
    private String products;
    private int total_expense;

    public Order(int user_id, String products, int total_expense) {
        this.user_id = user_id;
        this.products = products;
        this.total_expense = total_expense;
        this.created_at = new Timestamp(new Date().getTime()).toString();;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getProducts() {
        return products;
    }

    public void setProducts(String products) {
        this.products = products;
    }

    public int getTotal_expense() {
        return total_expense;
    }

    public void setTotal_expense(int total_expense) {
        this.total_expense = total_expense;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    private String created_at;
}
