package com.project.SpringCafeUI.repository;

import com.project.SpringCafeUI.entity.Order;
import com.project.SpringCafeUI.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
    List<OrderDetail> findByOrder(Order order);
}
