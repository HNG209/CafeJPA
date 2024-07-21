package com.project.SpringCafeUI.service;

import com.project.SpringCafeUI.entity.Drink;
import com.project.SpringCafeUI.repository.DrinkRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DrinkService {
    @Autowired
    private DrinkRepository drinkRepository;

    @Transactional
    public Drink findDrinkById(int id){
        return drinkRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("cannot find drink with id:" + id));
    }
}
