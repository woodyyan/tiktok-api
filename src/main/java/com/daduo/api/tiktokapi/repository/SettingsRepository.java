package com.daduo.api.tiktokapi.repository;

import com.daduo.api.tiktokapi.entity.ServiceContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SettingsRepository extends JpaRepository<ServiceContactEntity, Long> {
}
