package com.daduo.api.tiktokapi.controller;

import com.daduo.api.tiktokapi.model.OperateLogs;
import com.daduo.api.tiktokapi.service.OperateLogService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public OperateLogs getAllOperateLogs(@PageableDefault(value = 0, size = 20, sort = "createdTime", direction = Sort.Direction.DESC)
                                         @ApiParam(value = "分页")
                                                 Pageable page) {
        log.info("[START] Get all operate logs with page: {}", page);
        OperateLogs logs = service.getAllOperateLogs(page);
        log.info("[END] Get all operate logs with logs: {}", logs);
        return logs;
    }
}
