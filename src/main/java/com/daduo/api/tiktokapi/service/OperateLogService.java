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

import java.util.Date;
import java.util.Optional;

@Service
public class OperateLogService {

    @Autowired
    private OperateLogRepository repository;

    @Autowired
    private OperateLogsTranslator translator;

    @Autowired
    private AdminRepository adminRepository;

    public OperateLogs getAllOperateLogs(Date startDate, Date endDate, Pageable page) {
        Page<OperateLog> logs;
        if (startDate != null && endDate != null) {
            logs = repository.findByCreatedTimeBetween(new LocalDateTime(startDate.getTime()), new LocalDateTime(endDate.getTime()), page);
        } else {
            logs = repository.findAll(page);
        }
        return translator.toOperateLogs(logs);
    }

    public void addOperateLog(String operation, String admin, String ip) {
        OperateLog log = new OperateLog();
        if (admin != null) {
            try {
                Long id = Long.parseLong(admin, 10);
                Optional<Admin> adminEntity = adminRepository.findById(id);
                adminEntity.ifPresent(admin1 -> log.setAdminName(admin1.getNickname()));
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        log.setCreatedTime(LocalDateTime.now());
        log.setIP(ip);
        log.setOperation(operation);
        repository.save(log);
    }

    public OperateLogs getOperateLogsByAdminName(String adminName, Pageable page) {
        Page<OperateLog> logs = repository.findAllByAdminName(adminName, page);
        return translator.toOperateLogs(logs);
    }
}
