package com.daduo.api.tiktokapi.repository;

import com.daduo.api.tiktokapi.entity.OperateLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperateLogRepository extends JpaRepository<OperateLog, Long> {
}
