package com.kelompok2.selfservicesapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kelompok2.selfservicesapp.model.Products;

public interface ProductsRepository extends JpaRepository<Products, Long> {
    List<Products> findByProductCategoryIgnoreCase(String productCategory);
}