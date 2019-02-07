package com.daduo.api.tiktokapi.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Accounts {
    private List<AccountData> data = new ArrayList<>();
    private PagingMeta meta;
}
