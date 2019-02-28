package com.daduo.api.tiktokapi.repository;

import com.daduo.api.tiktokapi.entity.TaskOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskOrderRepository extends JpaRepository<TaskOrder, Long> {
    Page<TaskOrder> findAllByUserId(Long userId, Pageable page);

    List<TaskOrder> findAllByTaskId(Long taskId);
}
