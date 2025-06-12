package com.kelompok2.selfservicesapp.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kelompok2.selfservicesapp.model.Products;
import com.kelompok2.selfservicesapp.payload.ApiResponse;
import com.kelompok2.selfservicesapp.repository.ProductsRepository;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:3000") // Pindahkan annotation ke level class
public class ProductsController {

    @Autowired
    private ProductsRepository productsRepository;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Products>>> getAllProducts() {
        try {
            List<Products> products = productsRepository.findAll();
            return ResponseEntity.ok(
                new ApiResponse<>(200, "Success", products)
            );
        } catch (Exception e) {
            return ResponseEntity.status(500).body(
                new ApiResponse<>(500, "Error retrieving products: " + e.getMessage(), null)
            );
        }
    }

    @GetMapping("/filter")
    public ResponseEntity<ApiResponse<List<Products>>> getProductsByCategory(
            @RequestParam(required = false) String category) {
        try {
            List<Products> products;
            if (category == null || category.isEmpty()) {
                products = productsRepository.findAll();
            } else {
                products = productsRepository.findByProductCategoryIgnoreCase(category);
            }
            return ResponseEntity.ok(
                new ApiResponse<>(200, "Success", products)
            );
        } catch (Exception e) {
            return ResponseEntity.status(500).body(
                new ApiResponse<>(500, "Error filtering products: " + e.getMessage(), null)
            );
        }
    }

    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<ApiResponse<Products>> createProduct(
            @RequestParam("productImage") MultipartFile file,
            @RequestParam("productName") String productName,
            @RequestParam("productCategory") String productCategory,
            @RequestParam("price") int price,
            @RequestParam("stock") int stock) {
        
        try {
            // Validasi file
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body(
                    new ApiResponse<>(400, "Product image is required", null)
                );
            }

            // Simpan file
            String uploadDir = System.getProperty("user.dir") + "/uploads/";
            File directory = new File(uploadDir);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            File saveFile = new File(uploadDir + fileName);
            file.transferTo(saveFile);

            // Buat produk
            Products product = new Products();
            product.setProductImage(fileName);
            product.setProductName(productName);
            product.setProductCategory(productCategory);
            product.setPrice(price);
            product.setStock(stock);

            Products savedProduct = productsRepository.save(product);

            return ResponseEntity.ok(
                new ApiResponse<>(201, "Product created successfully", savedProduct)
            );
        } catch (IOException e) {
            return ResponseEntity.status(500).body(
                new ApiResponse<>(500, "Error saving file: " + e.getMessage(), null)
            );
        } catch (Exception e) {
            return ResponseEntity.status(500).body(
                new ApiResponse<>(500, "Error creating product: " + e.getMessage(), null)
            );
        }
    }
}