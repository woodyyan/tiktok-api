package com.daduo.api.tiktokapi.translator;

import com.daduo.api.tiktokapi.entity.Admin;
import com.daduo.api.tiktokapi.entity.Permission;
import com.daduo.api.tiktokapi.enums.PermissionType;
import com.daduo.api.tiktokapi.enums.RoleType;
import com.daduo.api.tiktokapi.model.*;
import org.joda.time.LocalDateTime;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AdminTranslator {
    public AdminResponse toResponse(Admin admin, Permission permission) {
        AdminResponse response = new AdminResponse();
        response.setData(toAdminData(admin, permission));
        return response;
    }

    public Admin toAdmin(AdminRequest request) {
        Admin admin = new Admin();
        admin.setPassword(request.getPassword());
        admin.setPhoneNumber(request.getPhoneNumber());
        admin.setNickname(request.getNickname());
        admin.setCreatedTime(LocalDateTime.now());
        admin.setLastModifiedTime(LocalDateTime.now());
        return admin;
    }

    public Admins toAdmins(Page<Admin> admins, List<Permission> permissions) {
        Admins response = new Admins();
        for (Admin admin : admins.getContent()) {
            Optional<Permission> permission = permissions.stream().filter(it -> it.getAdminId().equals(admin.getId())).findFirst();
            permission.ifPresent(permission1 -> response.getData().add(toAdminData(admin, permission1)));
        }
        PagingMeta meta = new PagingMeta();
        meta.setPageNumber(admins.getNumber());
        meta.setPageSize(admins.getSize());
        meta.setTotalElements(admins.getTotalElements());
        meta.setTotalPages(admins.getTotalPages());
        response.setMeta(meta);
        return response;
    }

    private AdminData toAdminData(Admin admin, Permission permission) {
        AdminData data = new AdminData();
        data.setPhoneNumber(admin.getPhoneNumber());
        data.setId(admin.getId());
        data.setNickname(admin.getNickname());
        data.setPassword(admin.getPassword());
        data.setRole(permission.getRole());
        data.setRole(permission.getRole());
        if (permission.isCanAutoTaskManage()) {
            data.permissions.add(PermissionType.AUTO_TASK_MANAGE);
        }
        if (permission.isCanCreditStoreManage()) {
            data.permissions.add(PermissionType.CREDIT_STORE_MANAGE);
        }
        if (permission.isCanMemberManage()) {
            data.permissions.add(PermissionType.MEMBER_MANAGE);
        }
        if (permission.isCanPromotionManage()) {
            data.permissions.add(PermissionType.PROMOTION_MANAGE);
        }
        if (permission.isCanTaskManage()) {
            data.permissions.add(PermissionType.TASK_MANAGE);
        }
        if (permission.isCanTaskOrderManage()) {
            data.permissions.add(PermissionType.TASK_ORDER_MANAGE);
        }
        data.setId(admin.getId());
        data.setCreatedTime(admin.getCreatedTime().toDateTime());
        data.setLastModifiedTime(admin.getLastModifiedTime().toDateTime());
        return data;
    }

    public Permission toPermission(Long adminId, List<PermissionType> permissions, RoleType role) {
        Permission permission = new Permission();
        permission.setAdminId(adminId);
        for (PermissionType type : permissions) {
            if (type.equals(PermissionType.AUTO_TASK_MANAGE)) {
                permission.setCanAutoTaskManage(true);
            }
            if (type.equals(PermissionType.CREDIT_STORE_MANAGE)) {
                permission.setCanCreditStoreManage(true);
            }
            if (type.equals(PermissionType.MEMBER_MANAGE)) {
                permission.setCanMemberManage(true);
            }
            if (type.equals(PermissionType.TASK_ORDER_MANAGE)) {
                permission.setCanTaskOrderManage(true);
            }
            if (type.equals(PermissionType.PROMOTION_MANAGE)) {
                permission.setCanPromotionManage(true);
            }
            if (type.equals(PermissionType.TASK_MANAGE)) {
                permission.setCanTaskManage(true);
            }
        }
        permission.setCreatedTime(LocalDateTime.now());
        permission.setLastModifiedTime(LocalDateTime.now());
        permission.setRole(role);
        return permission;
    }
}
