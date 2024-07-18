package com.project.SpringCafeUI.repository;

import com.project.SpringCafeUI.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Integer> {
    List<Card> findByNumber(int number);
}
