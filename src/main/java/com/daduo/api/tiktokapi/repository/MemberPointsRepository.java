package com.daduo.api.tiktokapi.repository;

import com.daduo.api.tiktokapi.entity.MemberPoints;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberPointsRepository extends JpaRepository<MemberPoints, Long> {
    MemberPoints findByUserId(Long userId);
}
