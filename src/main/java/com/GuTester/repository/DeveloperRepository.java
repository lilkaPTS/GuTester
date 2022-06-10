package com.GuTester.repository;

import com.GuTester.model.entity.Developer;
import com.GuTester.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeveloperRepository extends JpaRepository<Developer, Long> {
    Developer findDeveloperByUser(User user);
}
