package com.kelompok2.selfservicesapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.kelompok2.selfservicesapp.model.Transactions;

public interface TransactionsRepository extends JpaRepository<Transactions, Integer> {
}
