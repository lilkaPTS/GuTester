package com.GuTester.repository;

import com.GuTester.model.Developer;
import com.GuTester.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeveloperRepository extends JpaRepository<Developer, Long> {
    Developer findDeveloperByUser(User user);
}
