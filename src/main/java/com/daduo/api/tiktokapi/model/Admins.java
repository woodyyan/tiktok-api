package com.daduo.api.tiktokapi.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Admins {
    private List<AdminData> data = new ArrayList<>();
}
