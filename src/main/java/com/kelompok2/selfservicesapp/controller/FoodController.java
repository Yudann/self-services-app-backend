package com.kelompok2.selfservicesapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kelompok2.selfservicesapp.model.Food;
import com.kelompok2.selfservicesapp.repository.FoodRepository;

@RestController
@RequestMapping("/api/food")
public class FoodController {

    @Autowired
    private FoodRepository foodRepository;

    @GetMapping
    public List<Food> getAllFood() {
        return foodRepository.findAll();
    }

    @PostMapping
    public Food createFood(@RequestBody Food food) {
        return foodRepository.save(food);
    }

    
}
