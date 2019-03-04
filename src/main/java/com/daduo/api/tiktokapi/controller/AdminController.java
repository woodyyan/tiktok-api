package com.daduo.api.tiktokapi.controller;

import com.daduo.api.tiktokapi.model.*;
import com.daduo.api.tiktokapi.service.AdminService;
import com.daduo.api.tiktokapi.service.OperateLogService;
import com.daduo.api.tiktokapi.validator.AdminValidator;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/admin")
@RestController
@Slf4j
@Api(tags = "管理员接口", description = "管理员管理")
public class AdminController {
    @Autowired
    private AdminService service;

    @Autowired
    private AdminValidator validator;

    @Autowired
    private HttpServletRequest servletRequest;

    @Autowired
    private OperateLogService operateLogService;

    @PostMapping("/login")
    @ApiOperation(value = "登陆接口")
    public AdminResponse login(@RequestBody @ApiParam(value = "登陆请求Json") AdminLoginRequest loginRequest) {
        log.info("[START] Admin login with request: {}", loginRequest);
        operateLogService.addOperateLog("添加管登陆", servletRequest.getHeader("admin"), servletRequest.getRemoteAddr());
        AdminResponse response = service.login(loginRequest);
        log.info("[END] Admin login with response: {}", response);
        return response;
    }

    @PostMapping
    @ApiOperation(value = "添加管理员接口", notes = "权限管理：\nTASK_MANAGE是刷单管理, \nMEMBER_MANAGE是会员管理, \nTASK_ORDER_MANAGE是刷粉管理, \nCREDIT_STORE_MANAGE是积分商城管理, \nPROMOTION_MANAGE是推广管理, \nAUTO_TASK_MANAGE是自动刷单管理")
    @ResponseStatus(value = HttpStatus.CREATED)
    public AdminResponse addAdminUser(@RequestBody @ApiParam("管理员请求") AdminRequest request) {
        log.info("[START] Add admin user with request: {}", request);
        operateLogService.addOperateLog("添加管理员", servletRequest.getHeader("admin"), servletRequest.getRemoteAddr());
        validator.validateExists(request.getPhoneNumber());
        AdminResponse response = service.addAdminUser(request);
        log.info("[END] Add admin user with response: {}", response);
        return response;
    }

    @DeleteMapping("/{adminId}")
    @ApiOperation(value = "删除管理员接口")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteAdminUser(@PathVariable @ApiParam("管理员ID") Long adminId) {
        log.info("[START] Delete admin user with id: {}", adminId);
        operateLogService.addOperateLog("删除管理员接口", servletRequest.getHeader("admin"), servletRequest.getRemoteAddr());
        service.deleteAdminUser(adminId);
        log.info("[END] Delete admin user.");
    }

    @PostMapping("/resetPassword")
    @ApiOperation(value = "重置密码接口")
    public void resetPassword(@RequestBody @ApiParam("重置密码请求") ResetAdminPasswordRequest request) {
        log.info("[START] Reset admin password with request: {}", request);
        operateLogService.addOperateLog("重置密码", servletRequest.getHeader("admin"), servletRequest.getRemoteAddr());
        service.resetPassword(request);
        log.info("[END] Reset admin password.");
    }

    @PostMapping("/modifyPassword")
    @ApiOperation(value = "修改密码接口")
    public void modifyPassword(@RequestBody @ApiParam("重置密码请求") ModifyAdminPasswordRequest request) {
        log.info("[START] Modify admin password with request: {}", request);
        operateLogService.addOperateLog("修改密码", servletRequest.getHeader("admin"), servletRequest.getRemoteAddr());
        service.modifyPassword(request);
        log.info("[END] Modify admin password.");
    }

    @GetMapping
    @ApiOperation(value = "获取所有管理员")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "请求第几页",
                    defaultValue = "0", dataType = "integer", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "一页的总数",
                    defaultValue = "20", dataType = "integer", paramType = "query")
    })
    public Admins getAllAdmins(@PageableDefault(value = 0, size = 20, sort = "createdTime", direction = Sort.Direction.DESC)
                               @ApiParam(value = "分页")
                                       Pageable page) {
        log.info("[START] Get all admins");
        Admins admins = service.getAllAdmins(page);
        log.info("[START] Get all admins with response: {}", admins);
        return admins;
    }
}
