package com.daduo.api.tiktokapi.repository;

import com.daduo.api.tiktokapi.entity.ReferenceValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ValueReferenceRepository extends JpaRepository<ReferenceValue, Long> {
}
