package com.daduo.api.tiktokapi.service;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.daduo.api.tiktokapi.entity.Account;
import com.daduo.api.tiktokapi.exception.ErrorException;
import com.daduo.api.tiktokapi.model.AuthenticationCodeResponse;
import com.daduo.api.tiktokapi.model.LoginRequest;
import com.daduo.api.tiktokapi.model.LoginResponse;
import com.daduo.api.tiktokapi.model.PlatformLoginRequest;
import com.daduo.api.tiktokapi.model.error.Error;
import com.daduo.api.tiktokapi.repository.AccountRepository;
import com.daduo.api.tiktokapi.translator.AccountTranslator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
@Slf4j
public class LoginService {
    @Autowired
    private AccountRepository repository;

    @Autowired
    private AccountTranslator translator;

    public LoginResponse login(LoginRequest loginRequest) {
        verifyCode(loginRequest);
        Account account = repository.findOneByPhoneNumber(loginRequest.getPhoneNumber());
        if (account != null) {
            return translator.translateToLoginResponse(account, generateToken(account));
        } else {
            Account newAccount = translator.translateToAccount(loginRequest.getPhoneNumber());
            Account savedAccount = repository.save(newAccount);
            return translator.translateToLoginResponse(savedAccount, generateToken(savedAccount));
        }
    }

    private String generateToken(Account account) {
        String original = String.format("%s:%s", account.getId().toString(), account.getPhoneNumber());
        return Base64.getEncoder().encodeToString(original.getBytes());
    }

    private void verifyCode(LoginRequest loginRequest) {
        //TODO 临时代码
        if (loginRequest.getCode() != 1234) {
            Error error = new Error();
            error.setStatus("400");
            error.setTitle("验证码不正确");
            error.setDetails("验证码不正确, 请重新获取。");
            throw new ErrorException(HttpStatus.BAD_REQUEST, error);
        }
    }

    public AuthenticationCodeResponse sendMessageAuthenticationCode(Long number) {
        AuthenticationCodeResponse result = new AuthenticationCodeResponse();
        try {
//            send(number);
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

    public LoginResponse platformLogin(PlatformLoginRequest platformLoginRequest) {
        return null;
    }

    private void send(Long number) {
        //TODO 发送短信验证码
        DefaultProfile profile = DefaultProfile.getProfile("default", "<accessKeyId>", "<accessSecret>");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        //request.setProtocol(ProtocolType.HTTPS);
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("PhoneNumbers", "186234452233");
        request.putQueryParameter("SignName", "abc");
        request.putQueryParameter("TemplateCode", "123");
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
}
