package com.daduo.api.tiktokapi.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OperateLogs {
    private PagingMeta meta = new PagingMeta();
    private List<OperateLogData> data = new ArrayList<>();
}
