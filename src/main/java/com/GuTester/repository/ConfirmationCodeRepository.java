package com.GuTester.repository;

import com.GuTester.model.entity.ConfirmationCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface ConfirmationCodeRepository extends JpaRepository<ConfirmationCode, Long> {
    ConfirmationCode getConfirmationCodeByEmail(String email);

    @Query("SELECT case when count(cc)>0 then TRUE else FALSE end FROM ConfirmationCode cc "
            + "WHERE cc.email = :email")
    Boolean existByEmail(@Param("email") String email);

    @Modifying
    @Transactional
    @Query(value = "UPDATE confirmation_codes SET code = :code WHERE email = :email", nativeQuery = true)
    void updateCode(@Param("email") String email, @Param("code") String code);
}
