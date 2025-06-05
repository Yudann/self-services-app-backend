package com.kelompok2.selfservicesapp.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kelompok2.selfservicesapp.model.Products;
import com.kelompok2.selfservicesapp.repository.ProductsRepository;

@RestController
@RequestMapping("/api/products")
public class ProductsController {

    @Autowired
    private ProductsRepository productsRepository;

    @GetMapping
    public List<Products> getAllProducts() {
        return productsRepository.findAll();
    }

    @PostMapping(consumes = {"multipart/form-data"})
public Products createProduct(
        @RequestParam("productImage") MultipartFile file,
        @RequestParam("productName") String productName,
        @RequestParam("productCategory") String productCategory,
        @RequestParam("price") int price,
        @RequestParam("stock") int stock
) throws IOException {

    // Simpan file ke folder lokal (misal: /uploads)
    String uploadDir = System.getProperty("user.dir") + "/uploads/";
    File directory = new File(uploadDir);
    if (!directory.exists()) {
        directory.mkdirs();
    }

    String fileName = file.getOriginalFilename();
    File saveFile = new File(uploadDir + fileName);
    file.transferTo(saveFile);

    // Buat objek Products
    Products product = new Products();
    product.setProductImage(fileName);
    product.setProductName(productName);
    product.setProductCategory(productCategory);
    product.setPrice(price);
    product.setStock(stock);

    return productsRepository.save(product);
}

}
