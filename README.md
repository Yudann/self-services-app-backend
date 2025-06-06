Oke mantap! Jadi kamu udah fix endpoint-nya jadi:

* GET ke `/api/products`
* POST ke `/api/product`
  Dan field-field di tabel `products` udah lebih lengkap, jadi kita perlu sesuaikan juga bagian README-nya.

Berikut versi revisi-nya yang udah lebih sesuai:

---

# 📦 Self Service App - Backend (Java Spring Boot)

Backend untuk aplikasi self-service order makanan, pakai **Spring Boot + MySQL**. Cocok dipasangkan dengan frontend Next.js App Router.

---

## 🚀 Tech yang Dipakai

* Java 21
* Spring Boot 3.x
* Maven
* MySQL

---

## ✅ Setup Cepat & Gampang

### 1. Install Java & Maven

* Java 21: [https://jdk.java.net/21/](https://jdk.java.net/21/)
* Maven: [https://maven.apache.org/download.cgi](https://maven.apache.org/download.cgi)
* Cek instalasi:

  ```bash
  java -version
  mvn -v
  ```

---

### 2. Buat Database MySQL

* Buat database bernama `selforderapp`
* Buat tabel `products` dengan struktur seperti berikut:

```sql
CREATE TABLE products (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  product_image VARCHAR(255),
  product_name VARCHAR(255),
  product_category VARCHAR(255),
  price INT,
  stock INT
);
```

---

### 3. Struktur Project

```
src/
└── main/
    ├── java/
    │   └── com/kelompok2/selfservicesapp/
    │       ├── model/
    │       ├── repository/
    │       ├── controller/
    │       ├── payload/
    │       └── SelfServiceApp.java
    └── resources/
        └── application.properties
```

---

### 4. Konfigurasi `application.properties`

```properties
spring.datasource.url=jdbc:mysql://localhost:3307/selforderapp
spring.datasource.username=root
spring.datasource.password=  # isi sesuai password MySQL kamu
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
server.port=8081
```

---

### 5. Jalankan Backend

```bash
mvn spring-boot:run
```

---

### 6. Testing API (via Postman)

#### ✅ GET semua produk:

```
GET http://localhost:8081/api/products
```

#### ✅ POST tambah produk (pakai **form-data**, bukan JSON!):

```
POST http://localhost:8081/api/product
```

* Di Postman:

  * Pilih tab **Body** → **form-data**
  * Isi field berikut:

| Key               | Value                  | Type |
| ----------------- | ---------------------- | ---- |
| `productImage`    | *(upload file gambar)* | File |
| `productName`     | `Cafelatte`           | Text |
| `productCategory` | `drink`                | Text |
| `price`           | `28000`                | Text |
| `stock`           | `100`                   | Text |

| stock             | 10               |

> Note: `product_image` harus berupa **file upload**

---

## ✨ Catatan

* Kalau pakai XAMPP, pastikan MySQL jalan di port `3307` atau sesuaikan dengan server
* Endpoint Spring Boot bisa kamu ubah port-nya di `application.properties`
* Struktur tabel lainnya seperti `customers`, `transactions` akan dibuat di tahap selanjutnya

---

Happy coding! 🎉

> dibuat oleh Kelompok 2

---
