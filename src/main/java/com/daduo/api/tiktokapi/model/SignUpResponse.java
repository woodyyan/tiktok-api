package com.daduo.api.tiktokapi.model;

import lombok.Data;
import org.joda.time.DateTime;

@Data
public class SignUpResponse {
    private Long id;
    private DateTime createdTime;
}
