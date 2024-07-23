package com.project.SpringCafeUI.repository;

import com.project.SpringCafeUI.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query("SELECT o FROM Order o WHERE "
            + "(:year IS NULL OR FUNCTION('YEAR', o.date) = :year) AND "
            + "(:month IS NULL OR FUNCTION('MONTH', o.date) = :month) AND "
            + "(:day IS NULL OR FUNCTION('DAY', o.date) = :day)")
    List<Order> findByDate(@Param("day") Integer day, @Param("month") Integer month, @Param("year") Integer year);
}
