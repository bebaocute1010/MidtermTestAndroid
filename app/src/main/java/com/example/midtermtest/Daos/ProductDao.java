package com.example.midtermtest.Daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.midtermtest.Models.Product;

import java.util.List;

@Dao
public interface ProductDao {
    @Query("SELECT * FROM products")
    List<Product> getAll();

    @Insert
    void insert(Product product);

    @Query("SELECT * FROM products WHERE name=:name AND image=:image AND price=:price")
    Product isExists(String name, String image, Integer price);
}
