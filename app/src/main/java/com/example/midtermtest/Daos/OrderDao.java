package com.example.midtermtest.Daos;

import androidx.room.Dao;
import androidx.room.Insert;

import com.example.midtermtest.Models.Order;

@Dao
public interface OrderDao {
    @Insert
    void insert(Order order);
}
