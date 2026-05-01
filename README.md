# Product CRUD App Android Native

Aplikasi CRUD Android Native menggunakan Java dan Android Studio.

## Deskripsi Project
Aplikasi ini dibuat untuk memenuhi technical test berupa implementasi CRUD (Create, Read, Update, Delete) pada Android Native dengan integrasi REST API.

Aplikasi mengambil data produk dari public API dan menampilkan data menggunakan RecyclerView.

## Fitur
- Menampilkan daftar produk dari API
- Detail produk
- Tambah produk
- Update produk
- Hapus produk

## Teknologi yang Digunakan
- Java
- Android Studio
- Retrofit
- RecyclerView
- Glide
- Material Design

## API
Menggunakan public API:

https://fakestoreapi.com/

Endpoint:
- GET /products
- POST /products
- PUT /products/{id}
- DELETE /products/{id}

## Komponen UI
- RecyclerView
- ImageView
- TextView
- EditText
- Button
- LinearLayout
- RelativeLayout
- Space

## Struktur Project
```bash
app/src/main/java/com/example/nativecrudapp/
├── adapter
│   └── ProductAdapter.java
├── api
│   ├── ApiClient.java
│   └── ApiService.java
├── model
│   └── Product.java
├── MainActivity.java
├── DetailActivity.java
└── AddEditActivity.java
```
