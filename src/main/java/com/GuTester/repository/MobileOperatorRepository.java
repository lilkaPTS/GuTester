package com.GuTester.repository;

import com.GuTester.model.entity.MobileOperator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MobileOperatorRepository extends JpaRepository<MobileOperator, Long> {
    @Query(value = "select name from mobile_operator", nativeQuery = true)
    List<String> getAllNames();

    MobileOperator getMobileOperatorByName(String name);
}
