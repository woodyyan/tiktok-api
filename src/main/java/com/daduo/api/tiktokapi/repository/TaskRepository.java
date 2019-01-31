package com.daduo.api.tiktokapi.repository;

import com.daduo.api.tiktokapi.entity.TaskEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
    Page<TaskEntity> findAllByOwnerId(Long ownerId, Pageable page);
}
