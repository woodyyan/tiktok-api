package com.daduo.api.tiktokapi.translator;

import com.daduo.api.tiktokapi.entity.Admin;
import com.daduo.api.tiktokapi.model.AdminData;
import com.daduo.api.tiktokapi.model.AdminRequest;
import com.daduo.api.tiktokapi.model.AdminResponse;
import com.daduo.api.tiktokapi.model.Admins;
import org.joda.time.LocalDateTime;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdminTranslator {
    public AdminResponse toResponse(Admin admin) {
        AdminResponse response = new AdminResponse();
        response.setData(toAdminData(admin));
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

    public Admins toAdmins(List<Admin> admins) {
        Admins response = new Admins();
        for (Admin admin : admins) {
            response.getData().add(toAdminData(admin));
        }
        return response;
    }

    private AdminData toAdminData(Admin admin) {
        AdminData data = new AdminData();
        data.setPhoneNumber(admin.getPhoneNumber());
        data.setId(admin.getId());
        data.setUsername(admin.getUsername());
        data.setRole(admin.getRole());
        data.setId(admin.getId());
        data.setCreatedTime(admin.getCreatedTime().toDateTime());
        data.setLastModifiedTime(admin.getLastModifiedTime().toDateTime());
        return data;
    }
}
