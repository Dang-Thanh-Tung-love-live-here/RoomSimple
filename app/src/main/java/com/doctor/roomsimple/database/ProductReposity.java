package com.doctor.roomsimple.database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.doctor.roomsimple.model.Product;

import java.util.List;

public class ProductReposity {
    private LiveData<List<Product>> allProduct;
    private ProductDao productDao;

    public ProductReposity(Application application) {
        MyDatabase database = MyDatabase.getInstance(application.getApplicationContext());
        productDao = database.productDao();
        allProduct = productDao.allProduct();
    }

    public LiveData<List<Product>> getAllProduct() {
        return allProduct;
    }

    public void removeProduct(Product product) {
        new RemoveTask(productDao).execute(product);
    }

    public void insertProduct(Product product) {
        new InsertTask(productDao).execute(product);
    }

    public void updateProduct(Product product) {
        new UpdateTask(productDao).execute(product);

    }

    private class RemoveTask extends AsyncTask<Product, Void, Void> {
        private ProductDao productDao;

        private RemoveTask(ProductDao productDao) {
            this.productDao = productDao;
        }

        @Override
        protected Void doInBackground(Product... products) {
            productDao.deleteProduct(products[1]);
            return null;
        }
    }

    private class InsertTask extends AsyncTask<Product, Void, Void> {
        private ProductDao productDao;

        private InsertTask(ProductDao productDao) {
            this.productDao = productDao;
        }

        @Override
        protected Void doInBackground(Product... products) {
            productDao.insertProduct(products[1]);
            return null;
        }
    }

    private class UpdateTask extends AsyncTask<Product, Void, Void> {
        private ProductDao productDao;

        private UpdateTask(ProductDao productDao) {
            this.productDao = productDao;
        }

        @Override
        protected Void doInBackground(Product... products) {
            productDao.updateProduct(products[1]);
            return null;
        }
    }

}
