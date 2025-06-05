package com.kelompok2.selfservicesapp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kelompok2.selfservicesapp.dto.CheckoutRequest;
import com.kelompok2.selfservicesapp.model.Customers;
import com.kelompok2.selfservicesapp.model.Products;
import com.kelompok2.selfservicesapp.model.TransactionDetails;
import com.kelompok2.selfservicesapp.model.Transactions;
import com.kelompok2.selfservicesapp.repository.CustomerRepository;
import com.kelompok2.selfservicesapp.repository.ProductsRepository;
import com.kelompok2.selfservicesapp.repository.TransactionDetailsRepository;
import com.kelompok2.selfservicesapp.repository.TransactionsRepository;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionsRepository transactionsRepository;

    @Autowired
    private TransactionDetailsRepository transactionDetailsRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductsRepository productsRepository;

    @GetMapping
    public List<Transactions> getAllTransactions() {
        return transactionsRepository.findAll();
    }


    @PostMapping("/checkout")
    public Transactions checkout(@RequestBody CheckoutRequest request) {

        // Cari customer
        Optional<Customers> customerOpt = customerRepository.findById(request.getCustomerId());
        if (customerOpt.isEmpty()) {
            throw new RuntimeException("Customers not found");
        }
        Customers customer = customerOpt.get();

        Transactions transaction = new Transactions();
        transaction.setCustomer(customer);

        List<TransactionDetails> detailsList = new ArrayList<>();
        int totalAmount = 0;

        for (CheckoutRequest.Item item : request.getItems()) {
            Optional<Products> productOpt = productsRepository.findById(item.getProductId());
            if (productOpt.isEmpty()) {
                throw new RuntimeException("Product not found: " + item.getProductId());
            }
            Products product = productOpt.get();

            // Kurangi stock
            int updatedStock = product.getStock() - item.getQuantity();
            if (updatedStock < 0) {
                throw new RuntimeException("Insufficient stock for product: " + product.getProductName());
            }
            product.setStock(updatedStock);
            productsRepository.save(product);

            // Buat detail transaksi
            TransactionDetails detail = new TransactionDetails();
            detail.setTransaction(transaction);
            detail.setProduct(product);
            detail.setQuantity(item.getQuantity());
            int subtotal = product.getPrice() * item.getQuantity();
            detail.setSubtotal(subtotal);

            detailsList.add(detail);

            totalAmount += subtotal;
        }

        transaction.setTotalAmount(totalAmount);
        transaction.setDetails(detailsList);

        // Save transaksi + details karena cascade ALL
        Transactions savedTransaction = transactionsRepository.save(transaction);

        return savedTransaction;
    }

}
