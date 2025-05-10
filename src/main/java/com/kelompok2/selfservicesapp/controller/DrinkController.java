package com.kelompok2.selfservicesapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kelompok2.selfservicesapp.model.Drink;
import com.kelompok2.selfservicesapp.repository.DrinkRepository;

@RestController
@RequestMapping("/api/drink")
public class DrinkController {

    @Autowired
    private DrinkRepository drinkRepository;

    // GET: ambil semua drink
    @GetMapping
    public List<Drink> getAllDrink() {
        return drinkRepository.findAll();

    }

    @PostMapping
    public Drink createDrink(@RequestBody Drink drink) {
        return drinkRepository.save(drink);
    }
}
