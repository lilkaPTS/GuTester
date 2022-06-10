package com.GuTester.repository;

import com.GuTester.model.entity.Tester;
import com.GuTester.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TesterRepository extends JpaRepository<Tester, Long> {
    Tester findTesterByUser(User user);

    @Query(value = "select * from tester where 1=1", nativeQuery = true)
    List<Tester> findAllByOrder(User user);
}
