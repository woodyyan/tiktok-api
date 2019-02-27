package com.daduo.api.tiktokapi.repository;

import com.daduo.api.tiktokapi.entity.ExchangeOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExchangeOrderRepository extends JpaRepository<ExchangeOrder, Long> {
    List<ExchangeOrder> findAllByUserId(Long userId);
}
