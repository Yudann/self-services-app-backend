package com.kelompok2.selfservicesapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kelompok2.selfservicesapp.model.Food;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {
    // Kamu bisa tambahin query custom di sini kalau butuh nanti
}
