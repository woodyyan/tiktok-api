package com.daduo.api.tiktokapi.service;

import com.daduo.api.tiktokapi.entity.Admin;
import com.daduo.api.tiktokapi.entity.Permission;
import com.daduo.api.tiktokapi.exception.ErrorException;
import com.daduo.api.tiktokapi.model.*;
import com.daduo.api.tiktokapi.model.error.Error;
import com.daduo.api.tiktokapi.model.error.ErrorBuilder;
import com.daduo.api.tiktokapi.repository.AdminRepository;
import com.daduo.api.tiktokapi.repository.PermissionRepository;
import com.daduo.api.tiktokapi.translator.AdminTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
public class AdminService {
    @Autowired
    private AdminRepository repository;

    @Autowired
    private AdminTranslator translator;

    @Autowired
    private PermissionRepository permissionRepository;

    public AdminResponse login(AdminLoginRequest loginRequest) {
        Admin admin = repository.findByPhoneNumber(loginRequest.getPhoneNumber());
        if (admin != null) {
            if (admin.getPassword().equals(loginRequest.getPassword())) {
                Permission permission = permissionRepository.findOneByAdminId(admin.getId());
                if (permission != null) {
                    return translator.toResponse(admin, permission);
                }
            } else {
                Error error = new Error();
                error.setStatus(String.valueOf(BAD_REQUEST.value()));
                error.setTitle("密码错误");
                error.setDetails("密码错误");
                throw new ErrorException(HttpStatus.BAD_REQUEST, error);
            }
        }
        throw ErrorBuilder.buildNotFoundErrorException("用户找不到");
    }

    public AdminResponse addAdminUser(AdminRequest request) {
        Admin admin = translator.toAdmin(request);
        Admin savedAdmin = repository.save(admin);
        Permission permission = translator.toPermission(admin.getId(), request.getPermissions(), request.getRole());
        Permission savedPermission = permissionRepository.save(permission);
        return translator.toResponse(savedAdmin, savedPermission);
    }

    public void resetPassword(ResetAdminPasswordRequest request) {
        Admin admin = repository.findByPhoneNumber(request.getPhoneNumber());
        if (admin != null) {
            //TODO 验证 code验证码
            if (validateCode(request.getCode())) {
                admin.setPassword(request.getPassword());
                repository.saveAndFlush(admin);
            } else {
                Error error = ErrorBuilder.buildInvalidParameterError("验证码错误");
                throw new ErrorException(HttpStatus.BAD_REQUEST, error);
            }
        } else {
            throw ErrorBuilder.buildNotFoundErrorException("用户不存在");
        }
    }

    public Admins getAllAdmins(Pageable page) {
        Page<Admin> admins = repository.findAll(page);
        List<Permission> permissions = permissionRepository.findAll();
        return translator.toAdmins(admins, permissions);
    }

    private boolean validateCode(Integer code) {
        return false;
    }

    public void modifyPassword(ModifyAdminPasswordRequest request) {
        Optional<Admin> adminOptional = repository.findById(request.getAdminId());
        if (adminOptional.isPresent()) {
            Admin admin = adminOptional.get();
            admin.setPassword(request.getPassword());
            repository.saveAndFlush(admin);
        } else {
            throw ErrorBuilder.buildNotFoundErrorException("用户不存在");
        }
    }
}
