package com.GuTester.repository;

import com.GuTester.model.OS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OSRepository extends JpaRepository<OS, Long> {
    @Query(value = "select * from os", nativeQuery = true)
    List<OS> getAllNames();
}
