package com.daduo.api.tiktokapi.repository;

import com.daduo.api.tiktokapi.entity.Product;
import com.daduo.api.tiktokapi.enums.ProductStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Page<Product> findAllByStatus(ProductStatus status, Pageable page);

    Product findTop1ByOrderByLastModifiedTimeDesc();

    @Query("SELECT p FROM Product p where name like CONCAT('%',:keyword,'%')")
    List<Product> findByNameLike(@Param("keyword") String keyword);
}
