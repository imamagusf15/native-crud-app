package com.example.nativecrudapp.api;

import com.example.nativecrudapp.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiService {
    @GET("products")
    Call<List<Product>> getProducts();

    @POST("products")
    Call<Product> addProduct(@Body Product product);

    @PUT("products/{id}")
    Call<Product> updateProduct(@Path("id") int id, @Body Product product);

    @DELETE("products/{id}")
    Call<Product> deleteProduct(@Path("id") int id);
}
