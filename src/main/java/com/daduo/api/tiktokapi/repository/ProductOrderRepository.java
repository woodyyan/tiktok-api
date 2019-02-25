package com.daduo.api.tiktokapi.repository;

import com.daduo.api.tiktokapi.entity.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductOrderRepository extends JpaRepository<ProductOrder, Long> {
    List<ProductOrder> findAllByProductId(Long id);

    List<ProductOrder> findAllByUserId(Long userId);
}
