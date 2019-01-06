package com.daduo.api.tiktokapi.model;

import lombok.Data;

@Data
public class SignUpRequest {
    private String code;
    private Long number;
    private String password;
}
