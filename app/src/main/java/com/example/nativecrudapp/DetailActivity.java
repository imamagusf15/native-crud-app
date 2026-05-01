package com.example.nativecrudapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.nativecrudapp.api.ApiClient;
import com.example.nativecrudapp.api.ApiService;
import com.example.nativecrudapp.model.Product;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {
    Button btnDelete, btnEdit;
    int productId;
    TextView txtTitle, txtPrice;
    ImageView imgDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        btnDelete = findViewById(R.id.btnDelete);
        btnEdit = findViewById(R.id.btnEdit);
        txtTitle = findViewById(R.id.txtTitle);
        txtPrice = findViewById(R.id.txtPrice);
        imgDetail = findViewById(R.id.imgDetail);

        productId = getIntent().getIntExtra("product_id", 0);

        String title = getIntent().getStringExtra("product_title");
        double price = getIntent().getDoubleExtra("product_price", 0);
        String image = getIntent().getStringExtra("product_image");

        txtTitle.setText(title);
        txtPrice.setText("Rp " + price);

        Glide.with(this)
                .load(image)
                .into(imgDetail);

        btnEdit.setOnClickListener(v -> editProduct(title, price));
        btnDelete.setOnClickListener(v -> deleteProduct());
    }

    private void editProduct(String title, double price) {

        android.content.Intent intent =
                new android.content.Intent(DetailActivity.this, AddEditActivity.class);

        intent.putExtra("is_edit", true);
        intent.putExtra("product_id", productId);
        intent.putExtra("product_title", title);
        intent.putExtra("product_price", price);

        startActivity(intent);

    }

    private void deleteProduct() {
        ApiService apiService = ApiClient.getRetrofit().create(ApiService.class);

        apiService.deleteProduct(productId).enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                Toast.makeText(DetailActivity.this,
                        "Product deleted", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                Toast.makeText(DetailActivity.this,
                        "Delete failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
