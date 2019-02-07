package com.daduo.api.tiktokapi.translator;

import com.daduo.api.tiktokapi.entity.Admin;
import com.daduo.api.tiktokapi.model.AdminRequest;
import com.daduo.api.tiktokapi.model.AdminResponse;
import org.joda.time.LocalDateTime;
import org.springframework.stereotype.Component;

@Component
public class AdminTranslator {
    public AdminResponse toResponse(Admin admin) {
        AdminResponse response = new AdminResponse();
        response.setPhoneNumber(admin.getPhoneNumber());
        response.setId(admin.getId());
        response.setUsername(admin.getUsername());
        response.setRole(admin.getRole());
        response.setId(admin.getId());
        response.setCreatedTime(admin.getCreatedTime().toDateTime());
        response.setLastModifiedTime(admin.getLastModifiedTime().toDateTime());
        return response;
    }

    public Admin toAdmin(AdminRequest request) {
        Admin admin = new Admin();
        admin.setPassword(request.getPassword());
        admin.setPhoneNumber(request.getPhoneNumber());
        admin.setRole(request.getRole());
        admin.setUsername(request.getUsername());
        admin.setCreatedTime(LocalDateTime.now());
        admin.setLastModifiedTime(LocalDateTime.now());
        return admin;
    }
}
