package com.daduo.api.tiktokapi.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AllPromotions {

    private PagingMeta meta = new PagingMeta();
    private List<PromotionInfoData> data = new ArrayList<>();
}
