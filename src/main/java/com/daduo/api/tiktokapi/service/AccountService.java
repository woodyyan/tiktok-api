package com.daduo.api.tiktokapi.service;

import com.daduo.api.tiktokapi.entity.Account;
import com.daduo.api.tiktokapi.entity.AccountOnline;
import com.daduo.api.tiktokapi.enums.AccountStatus;
import com.daduo.api.tiktokapi.exception.ErrorException;
import com.daduo.api.tiktokapi.model.AccountData;
import com.daduo.api.tiktokapi.model.AccountRequest;
import com.daduo.api.tiktokapi.model.Accounts;
import com.daduo.api.tiktokapi.model.OnlineAccounts;
import com.daduo.api.tiktokapi.repository.AccountRepository;
import com.daduo.api.tiktokapi.repository.OnlineRepository;
import com.daduo.api.tiktokapi.translator.AccountTranslator;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.daduo.api.tiktokapi.model.error.ErrorBuilder.buildNotFoundErrorException;

@Service
public class AccountService {
    @Autowired
    private AccountTranslator translator;

    @Autowired
    private AccountRepository repository;

    @Autowired
    private OnlineRepository onlineRepository;

    @Autowired
    private MessageService messageService;

    public AccountService() {
    }

    public AccountData updateAccount(Long userId, AccountRequest accountRequest) throws ErrorException {
        Optional<Account> account = repository.findById(userId);
        if (account.isPresent()) {
            Account existingAccount = account.get();
            existingAccount.setLastModifiedTime(LocalDateTime.now());
            if (!StringUtils.isEmpty(accountRequest.getNickname())) {
                existingAccount.setNickname(accountRequest.getNickname());
            }
            if (!StringUtils.isEmpty(accountRequest.getQq())) {
                existingAccount.setQq(accountRequest.getQq());
            }
            if (!StringUtils.isEmpty(accountRequest.getWechat())) {
                existingAccount.setWechat(accountRequest.getWechat());
            }
            if (!StringUtils.isEmpty(accountRequest.getAvatar())) {
                existingAccount.setAvatar(accountRequest.getAvatar());
            }
            if (!StringUtils.isEmpty(accountRequest.getAddress())) {
                existingAccount.setAddress(accountRequest.getAddress());
            }
            if (!StringUtils.isEmpty(accountRequest.getName())) {
                existingAccount.setName(accountRequest.getName());
            }
            if (!StringUtils.isEmpty(accountRequest.getShippingPhone())) {
                existingAccount.setShippingPhone(accountRequest.getShippingPhone());
            }
            if (!StringUtils.isEmpty(accountRequest.getCanTask())) {
                existingAccount.setCanTask(accountRequest.getCanTask());
            }
            if (accountRequest.getStatus() != null) {
                existingAccount.setStatus(accountRequest.getStatus());
                switch (accountRequest.getStatus()) {
                    case ACTIVE:
                        messageService.logMessage(userId, "账号已激活。");
                    case FORBIDDEN:
                        messageService.logMessage(userId, "账号已封禁，请联系管理员。");
                }
            }

            Account savedAccount = repository.saveAndFlush(existingAccount);
            return translator.toAccountData(savedAccount);
        } else {
            throw buildNotFoundErrorException("账号找不到，请确认ID是否正确。");
        }
    }

    public boolean activateAccount(Long userId) {
        Optional<Account> account = repository.findById(userId);
        if (account.isPresent()) {
            messageService.logMessage(userId, "账号已激活。");
            Account existingAccount = account.get();
            existingAccount.setStatus(AccountStatus.ACTIVE);
            repository.saveAndFlush(existingAccount);
            return true;
        } else {
            throw buildNotFoundErrorException("账号找不到，请确认ID是否正确。");
        }
    }

    public void activateAccountTask(Long userId) {
        Optional<Account> account = repository.findById(userId);
        if (account.isPresent()) {
            messageService.logMessage(userId, "账号刷单已激活。");
            Account existingAccount = account.get();
            existingAccount.setCanTask(true);
            repository.saveAndFlush(existingAccount);
        } else {
            throw buildNotFoundErrorException("账号找不到，请确认ID是否正确。");
        }
    }

    public Accounts searchAccount(Date startDate, Date endDate, Pageable page) {
        Page<Account> pagedAccounts;
        if (startDate != null && endDate != null) {
            pagedAccounts = repository.findByCreatedTimeBetween(new LocalDateTime(startDate.getTime()), new LocalDateTime(endDate.getTime()), page);
        } else {
            pagedAccounts = repository.findAll(page);
        }
        return translator.toAccounts(pagedAccounts);
    }

    public AccountData getAccount(Long userId) {
        //userID可以是ID也可以是电话号
        Optional<Account> account = repository.findById(userId);
        if (account.isPresent()) {
            return translator.toAccountData(account.get());
        } else {
            Account accountByPhone = repository.findOneByPhoneNumber(userId);
            if (accountByPhone != null) {
                return translator.toAccountData(accountByPhone);
            } else {
                throw buildNotFoundErrorException("用户找不到，请确认参数是否正确。");
            }
        }
    }

    public void storeOnlineStatus(Long userId) {
        Optional<Account> account = repository.findById(userId);
        if (account.isPresent()) {
            AccountOnline online = new AccountOnline();
            online.setUserId(userId);
            online.setStatus("ONLINE");
            online.setCreatedTime(LocalDateTime.now());
            onlineRepository.save(online);
        } else {
            throw buildNotFoundErrorException("账号找不到，请确认ID是否正确。");
        }
        clearOffline();
    }

    public String getAccountNickname(Long userId) {
        Optional<Account> account = repository.findById(userId);
        if (account.isPresent()) {
            return account.get().getNickname();
        } else {
            throw buildNotFoundErrorException("账号找不到，请确认ID是否正确。");
        }
    }

    String getAccountAvatar(Long userId) {
        Optional<Account> account = repository.findById(userId);
        if (account.isPresent()) {
            return account.get().getAvatar();
        } else {
            throw buildNotFoundErrorException("账号找不到，请确认ID是否正确。");
        }
    }

    public OnlineAccounts getAllOnlineAccounts() {
        clearOffline();
        List<AccountOnline> all = onlineRepository.findAll();
        List<Long> ids = all.stream().map(AccountOnline::getUserId).collect(Collectors.toList());
        OnlineAccounts accounts = new OnlineAccounts();
        accounts.setOnlineMemberIds(ids);
        return accounts;
    }

    private void clearOffline() {
        List<AccountOnline> all = onlineRepository.findAll();
        for (AccountOnline online : all) {
            if (isBefore10Min(online.getCreatedTime())) {
                onlineRepository.delete(online);
            }
        }
    }

    private boolean isBefore10Min(LocalDateTime dateTime) {
        LocalDateTime now = LocalDateTime.now();
        return dateTime.isBefore(now.minusMinutes(10));
    }
}
