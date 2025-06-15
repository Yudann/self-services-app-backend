package com.kelompok2.selfservicesapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kelompok2.selfservicesapp.model.TransactionDetails;

public interface TransactionDetailsRepository extends JpaRepository<TransactionDetails, Long> {
    List<TransactionDetails> findByTransactionId(Long transactionId);
}