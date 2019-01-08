package com.daduo.api.tiktokapi.model;

import lombok.Data;

@Data
public class PlatformLoginRequest {
    private AuthData authData;
    private String platform;
}
