# 📦 Self Service App - Backend (Java Spring Boot)

Proyek backend untuk aplikasi pemesanan makanan *self-service* menggunakan **Spring Boot + MySQL**, cocok dipasangkan dengan frontend berbasis Next.js App Router.

---

## 🚀 Tech Stack

* Java 21
* Spring Boot 3.x
* Maven
* MySQL
* Postman (testing API)

---

## ✅ Langkah Setup Dari Awal

### 1. Install Java dan Maven

* **Java:** [https://jdk.java.net/21/](https://jdk.java.net/21/)
* **Maven:** [https://maven.apache.org/download.cgi](https://maven.apache.org/download.cgi)

Setelah install, pastikan command berikut berhasil:

```bash
java -version
mvn -v
```

---

### 2. Struktur Folder Project

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

### 3. File Penting

#### application.properties

```properties
spring.datasource.url=jdbc:mysql://localhost:3307/self_service_app
spring.datasource.username=root
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
server.port=8081
```

#### pom.xml (Dependencies Utama)

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
        <groupId>com.mysql</groupId>
        <artifactId>mysql-connector-j</artifactId>
    </dependency>
</dependencies>
```

---

## 🧩 Implementasi

### 1. Model: Food.java

```java
@Entity
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer price;
    // getter & setter
}
```

### 2. Repository: FoodRepository.java

```java
public interface FoodRepository extends JpaRepository<Food, Long> {}
```

### 3. Payload: ApiResponse.java

```java
public class ApiResponse<T> {
    private String status;
    private String message;
    private T data;

    public ApiResponse(String status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
    // getter & setter
}
```

### 4. Controller: FoodController.java

```java
@RestController
@RequestMapping("/api/food")
public class FoodController {

    @Autowired
    private FoodRepository foodRepository;

    @GetMapping
    public ApiResponse<List<Food>> getAllFood() {
        return new ApiResponse<>("success", "List of food", foodRepository.findAll());
    }

    @PostMapping
    public ApiResponse<Food> createFood(@RequestBody Food food) {
        return new ApiResponse<>("success", "Food created", foodRepository.save(food));
    }
}
```

### 5. Main Class: SelfServiceApp.java

```java
@SpringBootApplication
public class SelfServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(SelfServiceApp.class, args);
    }
}
```

---

## ▶️ Cara Menjalankan

### 1. Jalankan Database

Pastikan MySQL berjalan di port `3307`, dan database `self_service_app` sudah dibuat.

### 2. Run Spring Boot

```bash
mvn spring-boot:run
```

---

## 🧪 Cek Endpoint via Postman

### GET

```http
GET http://localhost:8081/api/food
```

### POST

```http
POST http://localhost:8081/api/food
Content-Type: application/json

{
  "name": "Nasi Goreng",
  "price": 15000
}
```

---

## ✨ Catatan Tambahan

* Jika pakai XAMPP, pastikan port MySQL benar (misal: 3307)
* Port Spring Boot bisa disesuaikan via `application.properties`

---

Happy coding 🎉

---

> dibuat oleh Yudan, untuk dokumentasi pribadi 
