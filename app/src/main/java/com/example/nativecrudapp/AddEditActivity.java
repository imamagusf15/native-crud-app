package com.example.nativecrudapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nativecrudapp.api.ApiClient;
import com.example.nativecrudapp.api.ApiService;
import com.example.nativecrudapp.model.Product;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddEditActivity extends AppCompatActivity {
    EditText edtTitle, edtPrice;
    Button btnSave;

    boolean isEdit = false;
    int productId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit);

        edtTitle = findViewById(R.id.edtTitle);
        edtPrice = findViewById(R.id.edtPrice);
        btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(v -> saveProduct());

        isEdit = getIntent().getBooleanExtra("is_edit", false);

        if (isEdit) {
            productId = getIntent().getIntExtra("product_id", 0);

            String title = getIntent().getStringExtra("product_title");
            double price = getIntent().getDoubleExtra("product_price", 0);

            edtTitle.setText(title);
            edtPrice.setText(String.valueOf(price));
        }

        btnSave.setOnClickListener(v -> {
            if (isEdit) {
                updateProduct();
            } else {
                saveProduct();
            }
        });
    }

    private void updateProduct() {
        String title = edtTitle.getText().toString();
        double price = Double.parseDouble(edtPrice.getText().toString());

        Product product = new Product(title, price);

        ApiService apiService = ApiClient.getRetrofit().create(ApiService.class);

        apiService.updateProduct(productId, product).enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                Toast.makeText(AddEditActivity.this,
                        "Product Updated", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                Toast.makeText(AddEditActivity.this,
                        "Update Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void saveProduct() {
        String title = edtTitle.getText().toString();
        double price = Double.parseDouble(edtPrice.getText().toString());

        Product product = new Product(title, price);

        ApiService apiService = ApiClient.getRetrofit().create(ApiService.class);

        apiService.addProduct(product).enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                Toast.makeText(AddEditActivity.this,
                        "Product Added", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                Toast.makeText(AddEditActivity.this,
                        "Failed", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
