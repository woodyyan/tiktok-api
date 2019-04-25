package com.daduo.api.tiktokapi.controller;

import com.daduo.api.tiktokapi.model.OperateLogs;
import com.daduo.api.tiktokapi.service.OperateLogService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RequestMapping("/operate/log")
@RestController
@Slf4j
@Api(tags = "操作日志接口", description = "操作日志")
public class OperateLogController {

    @Autowired
    private OperateLogService service;

    @GetMapping
    @ApiOperation(value = "获取所有操作日志（后台）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "请求第几页",
                    defaultValue = "0", dataType = "integer", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "一页的总数",
                    defaultValue = "20", dataType = "integer", paramType = "query")
    })
    public OperateLogs getAllOperateLogs(@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                                         @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
                                         @PageableDefault(value = 0, size = 20, sort = "createdTime", direction = Sort.Direction.DESC)
                                         @ApiParam(value = "分页")
                                                 Pageable page) {
        log.info("[START] Get all operate logs with page: {}", page);
        OperateLogs logs = service.getAllOperateLogs(startDate, endDate, page);
        log.info("[END] Get all operate logs with logs: {}", logs);
        return logs;
    }

    @GetMapping("/{adminName}")
    @ApiOperation(value = "根据管理员名字获取操作日志（后台）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "请求第几页",
                    defaultValue = "0", dataType = "integer", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "一页的总数",
                    defaultValue = "20", dataType = "integer", paramType = "query")
    })
    public OperateLogs getOperateLogByAdminName(@PathVariable @ApiParam("管理员名字") String adminName,
                                                @PageableDefault(value = 0, size = 20, sort = "createdTime", direction = Sort.Direction.DESC)
                                                @ApiParam(value = "分页") Pageable page) {
        log.info("[START] Get operate log by admin name {}.", adminName);
        OperateLogs logs = service.getOperateLogsByAdminName(adminName, page);
        log.info("[END] Get operate log by admin name with {}.", logs);
        return logs;
    }
}
