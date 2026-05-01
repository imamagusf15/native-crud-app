package com.example.nativecrudapp;

import android.os.Bundle;
import android.widget.Button;


import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nativecrudapp.adapter.ProductAdapter;
import com.example.nativecrudapp.api.ApiClient;
import com.example.nativecrudapp.api.ApiService;
import com.example.nativecrudapp.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        getProducts();

        btnAdd = findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(v -> {
            android.content.Intent intent =
                    new android.content.Intent(MainActivity.this, AddEditActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getProducts();
    }

    private void getProducts() {
        ApiService apiService = ApiClient.getRetrofit().create(ApiService.class);

        apiService.getProducts().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ProductAdapter adapter =
                            new ProductAdapter(response.body(), product -> {
                                android.content.Intent intent =
                                        new android.content.Intent(MainActivity.this, DetailActivity.class);
                                intent.putExtra("product_id", product.getId());
                                intent.putExtra("product_title", product.getTitle());
                                intent.putExtra("product_price", product.getPrice());
                                intent.putExtra("product_image", product.getImage());
                                startActivity(intent);
                            });
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}