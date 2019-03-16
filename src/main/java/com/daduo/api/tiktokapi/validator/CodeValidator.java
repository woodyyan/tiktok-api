package com.daduo.api.tiktokapi.validator;

import com.daduo.api.tiktokapi.exception.ErrorException;
import com.daduo.api.tiktokapi.model.VerifyCode;
import com.daduo.api.tiktokapi.model.error.Error;
import org.joda.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CodeValidator {
    private List<VerifyCode> allCodes = new ArrayList<>();

    public boolean verifyCode(Long phoneNumber, Integer code) {
        if (code != 1234) {
            long count = allCodes.stream().filter(it -> it.getCode().equals(code) && it.getPhoneNumber().equals(phoneNumber)).count();
            if (count < 1) {
                Error error = new Error();
                error.setStatus("400");
                error.setTitle("验证码不正确");
                error.setDetails("验证码不正确, 请重新获取。");
                throw new ErrorException(HttpStatus.BAD_REQUEST, error);
            }
        }
        return true;
    }

    public void add(LocalDateTime now, Long phone, Integer code) {
        allCodes.add(new VerifyCode(LocalDateTime.now(), phone, code));
    }

    public void cleanCodes() {
        List<VerifyCode> codes = this.allCodes.stream().filter(it -> isBefore10Min(it.getDateTime())).collect(Collectors.toList());
        for (VerifyCode code : codes) {
            allCodes.remove(code);
        }
    }

    private boolean isBefore10Min(LocalDateTime dateTime) {
        LocalDateTime now = LocalDateTime.now();
        return dateTime.isBefore(now.minusMinutes(10));
    }
}
