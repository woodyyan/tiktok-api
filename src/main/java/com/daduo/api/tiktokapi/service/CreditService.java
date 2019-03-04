package com.daduo.api.tiktokapi.service;

import com.daduo.api.tiktokapi.entity.*;
import com.daduo.api.tiktokapi.model.*;
import com.daduo.api.tiktokapi.repository.*;
import com.daduo.api.tiktokapi.translator.CreditOrderTranslator;
import com.daduo.api.tiktokapi.translator.CreditTranslator;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditService {
    @Autowired
    private CreditRepository repository;

    @Autowired
    private CreditTranslator translator;

    @Autowired
    private AccountService accountService;

    @Autowired
    private ReferenceValueService referenceValueService;

    @Autowired
    private CreditOrderRepository creditOrderRepository;

    @Autowired
    private CreditOrderTranslator creditOrderTranslator;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskOrderRepository taskOrderRepository;

    @Autowired
    private ProductOrderRepository exchangeProductOrderRepository;

    @Autowired
    private ExchangeOrderRepository exchangeOrderRepository;

    public CreditData getCreditById(Long userId) {
        Credit credit = repository.findByUserId(userId);
        if (credit == null) {
            credit = addDefaultCredit(userId);
        }
        String nickname = accountService.getAccountNickname(userId);
        String avatar = accountService.getAccountAvatar(userId);
        return translator.translateToCreditData(credit, nickname, avatar);
    }

    public CreditData modifyCredit(CreditRequest creditRequest) {
        Credit credit = repository.findByUserId(creditRequest.getUserId());
        if (credit == null) {
            credit = addDefaultCredit(creditRequest.getUserId());
        }
        int presentedCredit = 0;
        if (creditRequest.getCredit() != null) {
            presentedCredit = calculatePresentedCredit(creditRequest.getCredit());
            credit.setCredit(credit.getCredit() + creditRequest.getCredit());
        }
        if (creditRequest.getPoints() != null) {
            credit.setPoints(credit.getPoints() + creditRequest.getPoints());
        }
        saveCreditOrder(creditRequest, presentedCredit);
        Credit savedCredit = repository.saveAndFlush(credit);
        AccountData account = accountService.getAccount(creditRequest.getUserId());
        return translator.translateToCreditData(savedCredit, account.getNickname(), account.getAvatar());
    }

    private int calculatePresentedCredit(Integer credit) {
        if (credit >= 5000) {
            return referenceValueService.searchByName("presentedCreditFor5000");
        } else if (credit >= 1000) {
            return referenceValueService.searchByName("presentedCreditFor1000");
        } else if (credit >= 500) {
            return referenceValueService.searchByName("presentedCreditFor500");
        } else if (credit >= 200) {
            return referenceValueService.searchByName("presentedCreditFor200");
        } else if (credit >= 100) {
            return referenceValueService.searchByName("presentedCreditFor100");
        } else if (credit >= 50) {
            return referenceValueService.searchByName("presentedCreditFor50");
        } else if (credit >= 30) {
            return referenceValueService.searchByName("presentedCreditFor30");
        } else if (credit >= 10) {
            return referenceValueService.searchByName("presentedCreditFor10");
        }
        return 0;
    }

    private void saveCreditOrder(CreditRequest creditRequest, int presentedCredit) {
        CreditOrder creditOrder = new CreditOrder();
        creditOrder.setUserId(creditRequest.getUserId());
        Integer creditOfPerRmb = referenceValueService.searchByName("creditOfPerRmb");
        if (creditRequest.getCredit() != null) {
            creditOrder.setMoney(creditRequest.getCredit() / creditOfPerRmb);
            creditOrder.setCredit(creditRequest.getCredit());
        } else {
            creditOrder.setCredit(0);
        }
        creditOrder.setPresentedCredit(presentedCredit);
        creditOrder.setPoints(creditRequest.getPoints());
        creditOrder.setCreatedTime(LocalDateTime.now());
        creditOrder.setLastModifiedTime(LocalDateTime.now());
        creditOrderRepository.save(creditOrder);
    }

    private Credit addDefaultCredit(Long userId) {
        Credit credit = new Credit();
        credit.setCreatedTime(LocalDateTime.now());
        credit.setLastModifiedTime(LocalDateTime.now());
        credit.setCredit(0);
        credit.setUserId(userId);
        credit.setPoints(0);
        return repository.save(credit);
    }

    public CreditOrders getCreditOrders(Long userId) {
        List<CreditOrder> creditOrders = creditOrderRepository.findAllByUserId(userId);
        return creditOrderTranslator.toCreditOrders(creditOrders);
    }

    public MemberPointsInfo getUserPointsInfo(Long userId) {
        //刷粉积分总额，刷单积分总额，兑换商品积分总额，兑换现金积分总额
        Pageable page = PageRequest.of(0, 1000);
        Page<TaskEntity> taskEntities = taskRepository.findAllByOwnerId(userId, page);
        Page<TaskOrder> taskOrders = taskOrderRepository.findAllByUserId(userId, page);
        List<ProductOrder> productOrders = exchangeProductOrderRepository.findAllByUserId(userId);
        List<ExchangeOrder> exchangeOrders = exchangeOrderRepository.findAllByUserId(userId);

        MemberPointsInfo info = new MemberPointsInfo();
        int unit = 10000;
        info.setTotalTaskPoints(taskEntities.getContent().stream().mapToInt(TaskEntity::getTotalPoints).sum() / unit);
        info.setTotalTaskOrderPoints(taskOrders.getContent().stream().map(TaskOrder::getTask).mapToInt(TaskEntity::getTotalPoints).sum() / unit);
        info.setTotalExchangeProductPoints(productOrders.stream().mapToInt(ProductOrder::getPrice).sum() / unit);
        info.setTotalExchangeMoneyPoints(exchangeOrders.stream().mapToInt(ExchangeOrder::getPoints).sum() / unit
        );
        return info;
    }
}
