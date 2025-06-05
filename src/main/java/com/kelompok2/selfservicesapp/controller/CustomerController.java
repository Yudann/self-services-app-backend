package com.kelompok2.selfservicesapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kelompok2.selfservicesapp.model.Customers;
import com.kelompok2.selfservicesapp.repository.CustomerRepository;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping
    public List<Customers> getAllCustomers() {
        return customerRepository.findAll();
    }

    @PostMapping
    public Customers createCustomer(@RequestBody Customers customer) {
        return customerRepository.save(customer);
    }

    @GetMapping("/{id}")
    public Customers getCustomerById(@PathVariable Integer id) {
        return customerRepository.findById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Integer id) {
        customerRepository.deleteById(id);
    }
}
