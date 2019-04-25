package com.daduo.api.tiktokapi.repository;

import com.daduo.api.tiktokapi.entity.OperateLog;
import org.joda.time.LocalDateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperateLogRepository extends JpaRepository<OperateLog, Long> {
//    @Query("select o from OperateLog where adminName like CONCAT('%',:adminName,'%') order by ?#{#pageable}")
    Page<OperateLog> findAllByAdminName(String adminName, Pageable page);

    Page<OperateLog> findByCreatedTimeBetween(LocalDateTime startTime, LocalDateTime endTime, Pageable page);
}
