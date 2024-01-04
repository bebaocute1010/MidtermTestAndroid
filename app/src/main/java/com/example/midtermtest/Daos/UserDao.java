package com.example.midtermtest.Daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.midtermtest.Models.User;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM users")
    List<User> getAll();

    @Query("SELECT * FROM users WHERE username=:username and password=:password LIMIT 1")
    User attemptCredential(String username, String password);

    @Query("SELECT * FROM users WHERE username=:username")
    User findByUsername(String username);

    @Insert
    void insert(User user);
}
