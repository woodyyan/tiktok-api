package com.daduo.api.tiktokapi.repository;

import com.daduo.api.tiktokapi.entity.CreditOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditOrderRepository extends JpaRepository<CreditOrder, Long> {
    List<CreditOrder> findAllByUserId(Long userId);
}
