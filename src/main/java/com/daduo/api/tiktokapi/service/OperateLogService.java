package com.daduo.api.tiktokapi.service;

import com.daduo.api.tiktokapi.entity.OperateLog;
import com.daduo.api.tiktokapi.model.OperateLogs;
import com.daduo.api.tiktokapi.repository.OperateLogRepository;
import com.daduo.api.tiktokapi.translator.OperateLogsTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class OperateLogService {

    @Autowired
    private OperateLogRepository repository;

    @Autowired
    private OperateLogsTranslator translator;

    public OperateLogs getAllOperateLogs(Pageable page) {
        Page<OperateLog> logs = repository.findAll(page);
        return translator.toOperateLogs(logs);
    }
}
