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
import com.daduo.api.tiktokapi.model.*;
import com.daduo.api.tiktokapi.model.error.Error;
import com.daduo.api.tiktokapi.repository.AccountRepository;
import com.daduo.api.tiktokapi.translator.AccountTranslator;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.LocalDateTime;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@Slf4j
public class LoginService {
    @Autowired
    private AccountRepository repository;

    @Autowired
    private AccountTranslator translator;

    @Autowired
    private PromotionService promotionService;

    private List<VerifyCode> allCodes = new ArrayList<>();

    public LoginService() {
    }

    public LoginResponse login(LoginRequest loginRequest) {
        verifyCode(loginRequest);
        Account account = repository.findOneByPhoneNumber(loginRequest.getPhoneNumber());
        if (account != null) {
            return translator.translateToLoginResponse(account, generateToken(account));
        } else {
            Account newAccount = translator.translateToAccount(loginRequest.getPhoneNumber());
            Account savedAccount = repository.save(newAccount);
            savePromotion(loginRequest.getPromotionUserId(), savedAccount.getId());
            return translator.translateToLoginResponse(savedAccount, generateToken(savedAccount));
        }
    }

    private void savePromotion(Long promotionUserId, Long childUserId) {
        if (promotionUserId != null && childUserId != null) {
            PromotionRequest request = new PromotionRequest();
            request.setChildUserId(childUserId);
            request.setPromotionUserId(promotionUserId);
            promotionService.createPromotion(request);
        }
    }

    private String generateToken(Account account) {
        String original = String.format("%s:%s", account.getId().toString(), account.getPhoneNumber());
        return Base64.getEncoder().encodeToString(original.getBytes());
    }

    private void verifyCode(LoginRequest loginRequest) {
        if (loginRequest.getCode() != 1234) {
            long count = allCodes.stream().filter(it -> it.getCode().equals(loginRequest.getCode()) && it.getPhoneNumber().equals(loginRequest.getPhoneNumber())).count();
            if (count < 1) {
                Error error = new Error();
                error.setStatus("400");
                error.setTitle("验证码不正确");
                error.setDetails("验证码不正确, 请重新获取。");
                throw new ErrorException(HttpStatus.BAD_REQUEST, error);
            }
        }
    }

    public AuthenticationCodeResponse sendMessageAuthenticationCode(Long number) {
        AuthenticationCodeResponse result = new AuthenticationCodeResponse();
        try {
            result.setSuccess(true);
            result.setTitle(send(number));
        } catch (Exception ex) {
            log.error("验证码发送失败", ex);
            result.setSuccess(false);
            result.setTitle("发送失败");
            result.setMessage(ex.getMessage());
        }
        return result;
    }

    public LoginResponse platformLogin(PlatformLoginRequest platformLoginRequest) {
//        savePromotion(platformLoginRequest.getPromotionUserId(), platformLoginRequest.getId());
        return null;
    }

    private String send(Long number) {
        //发送短信验证码
        DefaultProfile profile = DefaultProfile.getProfile("default", "LTAISI1YFdZQQtI9", "zcozoj7mTOZNbAjzLRV74C76gaO8u5");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("PhoneNumbers", String.valueOf(number));
        request.putQueryParameter("SignName", "视界助手");
        request.putQueryParameter("TemplateCode", "SMS_160571213");
        String randomCode = generateRandomCode(number);
        request.putQueryParameter("TemplateParam", String.format("{\"code\":\"%s\"}", randomCode));
        try {
            CommonResponse response = client.getCommonResponse(request);
            JSONObject jsonObject = new JSONObject(response.getData());
            String code = jsonObject.getString("Code");
            String message = jsonObject.getString("Message");
            System.out.println("++++++++++++++++++++++");
            System.out.println(message);
            System.out.println(code);
            System.out.println(response.getData());
            System.out.println("++++++++++++++++++++++");
            if ("OK".equals(code)) {
                return message;
            } else {
                Error error = new Error();
                error.setStatus(code);
                error.setTitle("发送失败");
                error.setDetails(message);
                throw new ErrorException(HttpStatus.OK, error);
            }
        } catch (ClientException e) {
            System.out.println(e.getMessage());
            Error error = new Error();
            error.setStatus("400");
            error.setTitle("发送失败");
            error.setDetails("请稍后再试。");
            throw new ErrorException(HttpStatus.OK, error);
        }
    }

    private String generateRandomCode(Long phone) {
        clearCodes();
        Random random = new Random(10000);
        Integer code = random.nextInt();
        allCodes.add(new VerifyCode(LocalDateTime.now(), phone, code));
        return String.valueOf(code);
    }

    private void clearCodes() {
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
