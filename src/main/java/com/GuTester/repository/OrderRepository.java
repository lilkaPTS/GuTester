package com.GuTester.repository;

import com.GuTester.model.entity.Developer;
import com.GuTester.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByDeveloper(Developer developer);
}
