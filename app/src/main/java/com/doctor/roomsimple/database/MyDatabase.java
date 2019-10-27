package com.doctor.roomsimple.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.doctor.roomsimple.model.Product;

@Database(entities = {Product.class},version = 1)
public abstract class MyDatabase extends RoomDatabase {
    private static volatile MyDatabase instance;
    public abstract ProductDao productDao();

    public static  MyDatabase getInstance(Context context){
        if (instance==null){
            synchronized(MyDatabase.class){
                if (instance==null){
                    instance= Room.databaseBuilder(context.getApplicationContext(),MyDatabase.class,"mydatabase")
                           .addCallback(callback)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }

        }
        return instance;
    }
    private static RoomDatabase.Callback callback=new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new BasicData(instance).execute();
        }
    };
    private static class BasicData extends AsyncTask<Void,Void,Void>{
        private ProductDao productDao;
        private BasicData(MyDatabase myDatabase){
            this.productDao=myDatabase.productDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            Product product1=new Product("product 1",100000);
            productDao.deleteProduct(product1);
            productDao.insertProduct(product1);
            Product product2=new Product("product 2",100000);
            productDao.deleteProduct(product2);
            productDao.insertProduct(product2);

            productDao.insertProduct(new Product("product 3",100000));

            productDao.insertProduct(new Product("product 4",100000));

            return null;
        }
    }
}
