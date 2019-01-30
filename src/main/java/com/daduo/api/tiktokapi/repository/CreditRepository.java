package com.daduo.api.tiktokapi.repository;

import com.daduo.api.tiktokapi.entity.Credit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditRepository extends JpaRepository<Credit, Long> {
    Credit findByUserId(Long userId);
}
