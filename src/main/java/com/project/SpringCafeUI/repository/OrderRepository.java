package com.project.SpringCafeUI.repository;

import com.project.SpringCafeUI.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Order, Integer> {
//    @Modifying
//    @Query("DELETE FROM Order t WHERE t.id = ?1")
//    void delete(int id);
}
