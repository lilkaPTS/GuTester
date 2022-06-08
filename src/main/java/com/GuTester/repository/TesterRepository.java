package com.GuTester.repository;

import com.GuTester.model.Tester;
import com.GuTester.model.User;
import org.aspectj.weaver.ast.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TesterRepository extends JpaRepository<Tester, Long> {
    Tester findTesterByUser(User user);
}
