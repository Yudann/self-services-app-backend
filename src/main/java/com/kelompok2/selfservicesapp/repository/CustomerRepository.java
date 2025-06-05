package com.kelompok2.selfservicesapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kelompok2.selfservicesapp.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
