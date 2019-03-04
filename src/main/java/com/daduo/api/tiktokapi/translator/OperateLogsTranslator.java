package com.daduo.api.tiktokapi.translator;

import com.daduo.api.tiktokapi.entity.OperateLog;
import com.daduo.api.tiktokapi.model.OperateLogData;
import com.daduo.api.tiktokapi.model.OperateLogs;
import com.daduo.api.tiktokapi.model.PagingMeta;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class OperateLogsTranslator {
    public OperateLogs toOperateLogs(Page<OperateLog> logs) {
        OperateLogs operateLogs = new OperateLogs();

        for (OperateLog log : logs) {
            OperateLogData logData = new OperateLogData();
            logData.setAdminName(log.getAdminName());
            logData.setIP(log.getIP());
            logData.setCreatedTime(log.getCreatedTime().toDateTime());
            logData.setOperation(log.getOperation());
            operateLogs.getData().add(logData);
        }

        PagingMeta meta = new PagingMeta();
        meta.setPageNumber(logs.getNumber());
        meta.setPageSize(logs.getSize());
        meta.setTotalElements(logs.getTotalElements());
        meta.setTotalPages(logs.getTotalPages());
        operateLogs.setMeta(meta);
        return operateLogs;
    }
}
