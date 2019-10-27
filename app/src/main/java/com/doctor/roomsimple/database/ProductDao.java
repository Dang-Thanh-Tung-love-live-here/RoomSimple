package com.doctor.roomsimple.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.doctor.roomsimple.model.Product;

import java.util.List;

@Dao()
public interface ProductDao {

    @Query("select*from product_table order by name")
    LiveData<List<Product>> allProduct();

    @Delete()
    void deleteProduct(Product product);

    @Insert()
    void insertProduct(Product product);

    @Update()
    void updateProduct(Product product);

    @Query("Delete from product_table")
    void deleteAll();

}
