package com.doctor.roomsimple;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;


import com.doctor.roomsimple.database.ProductViewModel;
import com.doctor.roomsimple.model.Product;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    List<Product> products;
    @BindView(R.id.lv_product)
    ListView lvProduct;
    ArrayAdapter<Product> productAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ProductViewModel productViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);
        products=new ArrayList<>();

        productViewModel.getAllProduct().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> listProduct) {
                products = listProduct;
                productAdapter=new ArrayAdapter<Product>(MainActivity.this,android.R.layout.simple_list_item_1,products);
                lvProduct.setAdapter(productAdapter);
            }
        });


    }
}
