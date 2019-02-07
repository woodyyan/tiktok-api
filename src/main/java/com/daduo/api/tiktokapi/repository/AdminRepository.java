package com.daduo.api.tiktokapi.repository;

import com.daduo.api.tiktokapi.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin findByPhoneNumber(Long phoneNumber);
}
