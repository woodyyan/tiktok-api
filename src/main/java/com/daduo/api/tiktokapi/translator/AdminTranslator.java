package com.daduo.api.tiktokapi.translator;

import com.daduo.api.tiktokapi.entity.Admin;
import com.daduo.api.tiktokapi.model.AdminLoginResponse;
import org.springframework.stereotype.Component;

@Component
public class AdminTranslator {
    public AdminLoginResponse toResponse(Admin admin) {
        AdminLoginResponse response = new AdminLoginResponse();
        response.setPhoneNumber(admin.getPhoneNumber());
        response.setId(admin.getId());
        response.setUsername(admin.getUsername());
        response.setRole(admin.getRole());
        return response;
    }
}
