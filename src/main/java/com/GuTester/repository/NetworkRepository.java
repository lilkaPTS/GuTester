package com.GuTester.repository;

import com.GuTester.model.Network;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NetworkRepository extends JpaRepository<Network, Long> {
    @Query(value = "select name from network", nativeQuery = true)
    List<String> getAllNames();
}
