package com.daduo.api.tiktokapi.model;

import lombok.Data;

@Data
public class AuthData {
    private String openId;
    private String accessToken;
    private Long expiresIn;
}
