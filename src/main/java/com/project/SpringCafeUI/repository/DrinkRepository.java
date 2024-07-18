package com.project.SpringCafeUI.repository;

import com.project.SpringCafeUI.entity.Category;
import com.project.SpringCafeUI.entity.Drink;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DrinkRepository extends JpaRepository<Drink, Integer> {
    List<Drink> findByNameContaining(String name);
    List<Drink> findByCategory(Category category);
    List<Drink> findByStatus(boolean status);
    List<Drink> findByName(String name);
}
