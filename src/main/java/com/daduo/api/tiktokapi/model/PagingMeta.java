package com.daduo.api.tiktokapi.model;

import lombok.Data;

@Data
public class PagingMeta {
    private long totalPages;
    private long totalElements;
    private long pageNumber;
    private long pageSize;
}
