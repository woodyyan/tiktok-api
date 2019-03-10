package com.daduo.api.tiktokapi.service;

import com.daduo.api.tiktokapi.entity.Admin;
import com.daduo.api.tiktokapi.entity.OperateLog;
import com.daduo.api.tiktokapi.model.OperateLogs;
import com.daduo.api.tiktokapi.repository.AdminRepository;
import com.daduo.api.tiktokapi.repository.OperateLogRepository;
import com.daduo.api.tiktokapi.translator.OperateLogsTranslator;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OperateLogService {

    @Autowired
    private OperateLogRepository repository;

    @Autowired
    private OperateLogsTranslator translator;

    @Autowired
    private AdminRepository adminRepository;

    public OperateLogs getAllOperateLogs(Pageable page) {
        Page<OperateLog> logs = repository.findAll(page);
        return translator.toOperateLogs(logs);
    }

    public void addOperateLog(String operation, String admin, String ip) {
        OperateLog log = new OperateLog();
        if (admin != null) {
            Optional<Admin> adminEntity = adminRepository.findById(Long.valueOf(admin));
            adminEntity.ifPresent(admin1 -> log.setAdminName(admin1.getNickname()));
        }
        log.setCreatedTime(LocalDateTime.now());
        log.setIP(ip);
        log.setOperation(operation);
        repository.save(log);
    }
}
