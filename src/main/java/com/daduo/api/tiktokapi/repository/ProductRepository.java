package com.daduo.api.tiktokapi.repository;

import com.daduo.api.tiktokapi.entity.Product;
import com.daduo.api.tiktokapi.enums.ProductStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Page<Product> findAllByStatus(ProductStatus status, Pageable page);

    Product findTop1ByOrderByLastModifiedTimeDesc();
}
