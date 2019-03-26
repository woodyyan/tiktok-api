package com.daduo.api.tiktokapi.repository;

import com.daduo.api.tiktokapi.entity.ScreenshotStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScreenshotRepository extends JpaRepository<ScreenshotStatus, Long> {
}
