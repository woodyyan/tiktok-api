package com.daduo.api.tiktokapi.service;

import com.daduo.api.tiktokapi.entity.Account;
import com.daduo.api.tiktokapi.exception.ErrorException;
import com.daduo.api.tiktokapi.model.AuthenticationCodeResponse;
import com.daduo.api.tiktokapi.model.SignUpRequest;
import com.daduo.api.tiktokapi.model.SignUpResponse;
import com.daduo.api.tiktokapi.model.error.Error;
import com.daduo.api.tiktokapi.repository.AccountRepository;
import com.daduo.api.tiktokapi.translator.AccountTranslator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SignUpService {

    @Autowired
    private AccountRepository repository;

    @Autowired
    private AccountTranslator translator;

    public AuthenticationCodeResponse sendMessageAuthenticationCode(Long number) {
        AuthenticationCodeResponse result = new AuthenticationCodeResponse();
        try {
            send(number);
            result.setSuccess(true);
            result.setTitle("发送成功");
        } catch (Exception ex) {
            log.error("验证码发送失败", ex);
            result.setSuccess(false);
            result.setTitle("发送失败");
            result.setMessage(ex.getMessage());
        }
        return result;
    }

    public SignUpResponse signUp(SignUpRequest signUpRequest) {
        //TODO 判断验证码正确
        if (signUpRequest.getCode().equals("")) {
            Account account = signUp(signUpRequest.getNumber(), signUpRequest.getPassword());
            return translator.translateToSignUpResponse(account);
        } else {
            Error error = new Error();
            error.setStatus("400");
            error.setTitle("验证码不正确");
            error.setDetails("验证码不正确，请输入正确验证码。");
            throw new ErrorException(HttpStatus.BAD_REQUEST, error);
        }
    }

    private Account signUp(Long phoneNumber, String password) {
        checkPhoneNumberExists(phoneNumber);

        Account account = new Account();
        account.setId(System.currentTimeMillis());
        account.setPassword(password);
        account.setPhoneNumber(phoneNumber);

        try {
            return repository.save(account);
        } catch (Exception ex) {
            log.error("注册失败，Account保存失败.", ex);
            Error error = new Error();
            error.setStatus("502");
            error.setDetails("注册失败，请重试");
            error.setTitle("注册失败");
            throw new ErrorException(HttpStatus.BAD_GATEWAY, error);
        }
    }

    private void checkPhoneNumberExists(Long phoneNumber) {
        Account account = repository.findOneByPhoneNumber(phoneNumber);
        if (account != null) {
            Error error = new Error();
            error.setStatus("400");
            error.setTitle("手机号已注册");
            error.setDetails("手机号已注册，请直接登陆。");
            throw new ErrorException(HttpStatus.BAD_REQUEST, error);
        }
    }

    private void send(Long number) {
        //TODO 发送短信验证码
    }
}
