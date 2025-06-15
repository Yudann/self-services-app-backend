package com.kelompok2.selfservicesapp.controller;

import com.kelompok2.selfservicesapp.model.TransactionDetails;
import com.kelompok2.selfservicesapp.repository.TransactionDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/transaction-details")
public class TransactionDetailsController {

    @Autowired
    private TransactionDetailsRepository transactionDetailsRepository;

    // Get all transaction details
    @GetMapping
    public List<TransactionDetails> getAllTransactionDetails() {
        return transactionDetailsRepository.findAll();
    }

    // Get transaction details by ID
    @GetMapping("/{id}")
    public ResponseEntity<TransactionDetails> getTransactionDetailsById(@PathVariable Long id) {
        Optional<TransactionDetails> transactionDetails = transactionDetailsRepository.findById(id);
        return transactionDetails.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get transaction details by transaction ID
    @GetMapping("/transaction/{transactionId}")
    public List<TransactionDetails> getDetailsByTransactionId(@PathVariable Long transactionId) {
        return transactionDetailsRepository.findByTransactionId(transactionId);
    }

    // Create new transaction details
    @PostMapping
    public TransactionDetails createTransactionDetails(@RequestBody TransactionDetails transactionDetails) {
        return transactionDetailsRepository.save(transactionDetails);
    }

    // Update transaction details
    @PutMapping("/{id}")
    public ResponseEntity<TransactionDetails> updateTransactionDetails(
            @PathVariable Long id, @RequestBody TransactionDetails transactionDetailsDetails) {
        Optional<TransactionDetails> transactionDetailsOptional = transactionDetailsRepository.findById(id);
        
        if (transactionDetailsOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        TransactionDetails transactionDetails = transactionDetailsOptional.get();
        transactionDetails.setProduct(transactionDetailsDetails.getProduct());
        transactionDetails.setQuantity(transactionDetailsDetails.getQuantity());
        transactionDetails.setSubtotal(transactionDetailsDetails.getSubtotal());
        
        TransactionDetails updatedTransactionDetails = transactionDetailsRepository.save(transactionDetails);
        return ResponseEntity.ok(updatedTransactionDetails);
    }

    // Delete transaction details
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransactionDetails(@PathVariable Long id) {
        if (transactionDetailsRepository.existsById(id)) {
            transactionDetailsRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}