package com.kelompok2.selfservicesapp.repository;

import com.kelompok2.selfservicesapp.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository<Products, Long> {
}
