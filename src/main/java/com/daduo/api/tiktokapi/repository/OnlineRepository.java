package com.daduo.api.tiktokapi.repository;

import com.daduo.api.tiktokapi.entity.AccountOnline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OnlineRepository extends JpaRepository<AccountOnline, Long> {
    List<AccountOnline> findAllByUserId(Long userId);
}
