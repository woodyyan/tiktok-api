package com.daduo.api.tiktokapi.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Tasks {
    private List<TaskData> data = new ArrayList<>();
    private PagingMeta meta = new PagingMeta();
}
