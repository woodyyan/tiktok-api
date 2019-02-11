package com.daduo.api.tiktokapi.repository;

import com.daduo.api.tiktokapi.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
    Permission findOneByAdminId(Long adminId);
}
