package com.doctor.roomsimple.database;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.doctor.roomsimple.model.Product;

import java.util.List;

public class ProductViewModel extends AndroidViewModel {
    private ProductReposity reposity;
    private LiveData<List<Product>> allProduct;
    public ProductViewModel(@NonNull Application application) {
        super(application);
        reposity=new ProductReposity(application);
        allProduct=reposity.getAllProduct();
    }
    public void insertProduct(Product product){
        reposity.insertProduct(product);
    }

    public void updateProduct(Product product){
        reposity.updateProduct(product);
    }
    public void removeProduct(Product product){
        reposity.removeProduct(product);
    }
    public LiveData<List<Product>> getAllProduct(){
        return allProduct;
    }
}
