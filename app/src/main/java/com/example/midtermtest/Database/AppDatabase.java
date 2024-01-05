package com.example.midtermtest.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.midtermtest.Daos.OrderDao;
import com.example.midtermtest.Daos.ProductDao;
import com.example.midtermtest.Daos.UserDao;
import com.example.midtermtest.Models.Order;
import com.example.midtermtest.Models.Product;
import com.example.midtermtest.Models.User;

@Database(entities = {User.class, Product.class, Order.class}, version = 4)
public abstract class AppDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "midterm_test.db";

    private static AppDatabase instance;

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    AppDatabase.class,
                    DATABASE_NAME
            ).allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }

        return instance;
    }
    public abstract UserDao userDao();
    public abstract ProductDao productDao();
    public abstract OrderDao orderDao();
}
