package com.daduo.api.tiktokapi.model;

import lombok.Data;

@Data
public class  AuthenticationCodeResult {
    private boolean isSuccess;
    private String title;
    private String message;
}
