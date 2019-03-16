package com.daduo.api.tiktokapi.model;

import lombok.Data;
import org.joda.time.LocalDateTime;

@Data
public class VerifyCode {
    private LocalDateTime dateTime;
    private Long phoneNumber;
    private Integer code;

    public VerifyCode(LocalDateTime now, Long phone, Integer code) {
        this.code = code;
        this.dateTime = now;
        this.phoneNumber = phone;
    }
}
