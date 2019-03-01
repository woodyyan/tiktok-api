package com.daduo.api.tiktokapi.repository;

import com.daduo.api.tiktokapi.entity.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Long> {
    List<Promotion> findAllByPromotionUserId(Long userId);

    Integer countByChildUserId(Long childUserId);
}
