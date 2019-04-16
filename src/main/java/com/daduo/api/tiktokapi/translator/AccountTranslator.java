package com.daduo.api.tiktokapi.translator;

import com.daduo.api.tiktokapi.entity.Account;
import com.daduo.api.tiktokapi.entity.AccountOnline;
import com.daduo.api.tiktokapi.enums.AccountStatus;
import com.daduo.api.tiktokapi.model.*;
import com.daduo.api.tiktokapi.repository.OnlineRepository;
import com.daduo.api.tiktokapi.service.CreditService;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AccountTranslator {
    @Autowired
    private CreditService creditService;

    @Autowired
    private OnlineRepository onlineRepository;

    public LoginResponse translateToLoginResponse(Account account, String token) {
        LoginResponse response = new LoginResponse();
        response.setCreatedTime(account.getCreatedTime().toDateTime());
        response.setId(account.getId());
        response.setLastModifiedTime(account.getLastModifiedTime().toDateTime());
        response.setPhoneNumber(account.getPhoneNumber());
        response.setSessionToken(token);
        response.setNickname(account.getNickname());
        response.setStatus(account.getStatus());
        response.setIsForbidden(account.getStatus() == AccountStatus.INACTIVE);
        response.setAccessToken(account.getAccessToken());
        response.setExpiresIn(account.getExpiresIn());
        response.setOpenId(account.getOpenId());

        return response;
    }

    public Account translateToAccount(Long phoneNumber) {
        Account account = new Account();
        account.setNickname(phoneNumber.toString());
        account.setPhoneNumber(phoneNumber);
        LocalDateTime now = LocalDateTime.now();
        account.setCreatedTime(now);
        account.setLastModifiedTime(now);
        account.setId(System.currentTimeMillis());
        account.setStatus(AccountStatus.INACTIVE);
        return account;
    }

    public Accounts toAccounts(Page<Account> pagedAccounts) {
        Accounts accounts = new Accounts();
        for (Account account : pagedAccounts.getContent()) {
            accounts.getData().add(toAccountData(account));
        }
        PagingMeta meta = new PagingMeta();
        meta.setPageNumber(pagedAccounts.getNumber());
        meta.setPageSize(pagedAccounts.getSize());
        meta.setTotalElements(pagedAccounts.getTotalElements());
        meta.setTotalPages(pagedAccounts.getTotalPages());
        accounts.setMeta(meta);
        return accounts;
    }

    public AccountData toAccountData(Account account) {
        CreditData credit = creditService.getCreditById(account.getId());
        AccountData data = new AccountData();
        data.setAddress(account.getAddress());
        data.setAvatar(account.getAvatar());
        data.setCreatedTime(account.getCreatedTime().toDateTime());
        data.setId(account.getId());
        data.setLastModifiedTime(account.getLastModifiedTime().toDateTime());
        data.setPhoneNumber(account.getPhoneNumber());
        data.setQq(account.getQq());
        data.setShippingPhone(account.getShippingPhone());
        data.setNickname(account.getNickname());
        data.setName(account.getName());
        data.setWechat(account.getWechat());
        data.setCanTask(account.getCanTask());
        data.setStatus(account.getStatus());
        data.setCredit(credit.getCredit() / 10000);
        data.setPoints(credit.getPoints() / 10000);
        //TODO 扣充值币/万个
        data.setCostCredit(2);
        //TODO 扣积分/万个
        data.setCostPoints(3);
        List<AccountOnline> onlineList = onlineRepository.findAllByUserId(account.getId());
        data.setOnline(onlineList.size() > 0);
        return data;
    }
}
