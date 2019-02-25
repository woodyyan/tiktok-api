package com.daduo.api.tiktokapi.repository;

import com.daduo.api.tiktokapi.entity.CreditOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditOrderRepository extends JpaRepository<CreditOrder, Long> {
}
