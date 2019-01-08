package com.daduo.api.tiktokapi.model;

import lombok.Data;
import org.joda.time.DateTime;

@Data
public class LoginResponse {
    private String username;
    private Long id;
    private DateTime createdTime;
    private DateTime lastModifiedTime;
    private Long phoneNumber;
    private AuthData authData;
}
