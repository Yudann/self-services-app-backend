package com.kelompok2.selfservicesapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kelompok2.selfservicesapp.model.Drink;

@Repository
public interface DrinkRepository extends JpaRepository<Drink, Long> {
    // Bisa ditambahin method custom juga kalau butuh
}
