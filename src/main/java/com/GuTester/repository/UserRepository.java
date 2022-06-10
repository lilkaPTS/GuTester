package com.GuTester.repository;


import com.GuTester.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("FROM User u WHERE u.email = :email AND u.status = 'ACTIVE'")
    User findByEmailWithStatusActive(@Param("email") String email);

    User findByEmail(String email);

    User findByName(String name);

    @Query("SELECT case when count(u)>0 then TRUE else FALSE end FROM User u "
            + "WHERE u.email = :email AND u.status = 'ACTIVE'")
    Boolean existUserByEmail(@Param("email") String email);

    @Query("FROM User u WHERE u.status = 'ACTIVE'")
    List<User> findAllUsers();
}
