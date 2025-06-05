package com.kelompok2.selfservicesapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.kelompok2.selfservicesapp.model.TransactionDetails;

public interface TransactionDetailsRepository extends JpaRepository<TransactionDetails, Integer> {
}
