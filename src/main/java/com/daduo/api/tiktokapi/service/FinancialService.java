package com.daduo.api.tiktokapi.service;

import com.daduo.api.tiktokapi.entity.*;
import com.daduo.api.tiktokapi.model.*;
import com.daduo.api.tiktokapi.model.error.ErrorBuilder;
import com.daduo.api.tiktokapi.repository.*;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FinancialService {
    @Autowired
    private CreditOrderRepository creditOrderRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ReferenceValueService referenceValueService;

    @Autowired
    private CreditRepository creditRepository;

    @Autowired
    private ExchangeOrderRepository exchangeOrderRepository;

    @Autowired
    private ProductOrderRepository productOrderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private AccountRepository accountRepository;

    public FinancialInfo getAllFinancialInfo() {
        FinancialInfo info = new FinancialInfo();

        //充值现金总额：充值币订单总数加起来
        List<CreditOrder> creditOrders = creditOrderRepository.findAll();
        int totalCashMoney = creditOrders.stream().mapToInt(CreditOrder::getCredit).sum();
        info.setTopUpMoneyAmount(totalCashMoney);

        //佣金总额：任务订单credit*佣金比例 + 任务订单points*佣金比例
        List<TaskEntity> tasks = taskRepository.findAll();
        int totalCredit = tasks.stream().mapToInt(TaskEntity::getTotalCredit).sum();
        int totalPoints = tasks.stream().mapToInt(TaskEntity::getTotalPoints).sum();
        Integer commissionPercent = referenceValueService.searchByName("commissionPercent");
        Integer creditOfPerRmb = referenceValueService.searchByName("creditOfPerRmb");
        Integer pointsOfPerRmb = referenceValueService.searchByName("pointsOfPerRmb");
        double percent = ((100.0 + commissionPercent) / 100.0);
        int totalCommissionMoney = (int) (totalCredit * percent / creditOfPerRmb + totalPoints * percent / pointsOfPerRmb);
        info.setCommissionMoneyAmount(totalCommissionMoney);

        //扣除会员积分总额: 总金额 - 所有会员积分换成人民币
        List<Credit> credits = creditRepository.findAll();
        int pointsMoney = credits.stream().mapToInt(Credit::getPoints).sum() / pointsOfPerRmb;
        info.setMoneyAmountWithoutPoints(totalCashMoney - pointsMoney);

        //会员兑换现金总额: ExchangeOrder的总金额
        List<ExchangeOrder> exchangeOrders = exchangeOrderRepository.findAll();
        info.setExchangeMoneyAmount(exchangeOrders.stream().mapToInt(ExchangeOrder::getMoney).sum());

        //会员兑换商品总额: 商品订单的商品单价全部加起来
        List<ProductOrder> productOrders = productOrderRepository.findAll();
        List<Product> products = productRepository.findAllById(productOrders.stream().map(ProductOrder::getProductId).collect(Collectors.toList()));
        int totalPointsPrice = products.stream().mapToInt(Product::getPrice).sum();
        info.setExchangeProductAmount(totalPointsPrice / pointsOfPerRmb);

        //充值赠送充值币总额: 任务订单presentedCredit加起来 换成人民币
        int topUpPresentedCreditAmount = creditOrders.stream().mapToInt(CreditOrder::getPresentedCredit).sum() / creditOfPerRmb;
        info.setTopUpPresentedCreditAmount(topUpPresentedCreditAmount);

        //TODO 还有几个值没有实现
        //毛利总额:
        info.setGrossProfitAmount(1);

        //充值币总额
        info.setCreditAmount(1);

        //积分总额
        info.setPointsAmount(1);

        //扣除会员积分总额
        info.setPointsAmountWithoutPoints(1);

        //佣金积分总额
        info.setCommissionPointsAmount(1);

        //充值币余额
        info.setCreditBalance(1);

        //积分余额
        info.setPointsBalance(1);

        //会员兑换现金积分总额
        info.setExchangeMoneyPointsAmount(1);

        //会员兑换商品积分总额
        info.setExchangeProductPointsAmount(1);
        return info;
    }

    public UserFinancialInfoResponse getAllUserFinancialInfo() {
        List<Account> accounts = accountRepository.findAll();
        List<UserFinancialInfo> data = new ArrayList<>();
        for (Account account : accounts) {
            UserFinancialInfo info = new UserFinancialInfo();
            info.setUserId(account.getId());
            info.setNickname(account.getNickname());
            info.setTopUpMoney(getTopUpMoney(account.getId()));
            info.setCommissionMoney(getCommissionMoney(account.getId()));
            info.setMoneyWithoutPoints(getMoneyWithoutPoints(account.getId()));
            info.setExchangeMoney(getExchangeMoney(account.getId()));
            info.setProductMoney(getProductMoney(account.getId()));
            info.setPresentedCreditMoney(getPresentedCreditMoney(account.getId()));
            data.add(info);
        }
        UserFinancialInfoResponse response = new UserFinancialInfoResponse();
        response.setData(data);
        return response;
    }

    private Integer getPresentedCreditMoney(Long id) {
        List<CreditOrder> orders = creditOrderRepository.findAllByUserId(id);
        return orders.stream().mapToInt(CreditOrder::getPresentedCredit).sum();
    }

    private Integer getProductMoney(Long id) {
        List<ProductOrder> orders = productOrderRepository.findAllByUserId(id);
        return orders.stream().mapToInt(ProductOrder::getPrice).sum();
    }

    //TODO 还有4个方法没实现
    private Integer getExchangeMoney(Long id) {
        return 1;
    }

    private Integer getMoneyWithoutPoints(Long id) {
        return 1;
    }

    private Integer getCommissionMoney(Long id) {
        return 1;
    }

    private Integer getTopUpMoney(Long id) {
        return 1;
    }

    public MainDataDetail getMainDataDetail(Date startDate, Date endDate, Pageable page) {
        MainDataDetail detail = new MainDataDetail();
        Page<Account> accounts = getAccounts(startDate, endDate, page);
        for (Account account : accounts) {
            detail.getData().add(getMainDataDetailData(account));
        }
        detail.setMeta(getPagingMeta(accounts));
        return detail;
    }

    public OtherDataDetail getOtherDataDetail(Date startDate, Date endDate, Pageable page) {
        Page<Account> accounts = getAccounts(startDate, endDate, page);
        OtherDataDetail detail = new OtherDataDetail();
        for (Account account : accounts) {
            detail.getData().add(getOtherDataDetailData(account));
        }
        detail.setMeta(getPagingMeta(accounts));
        return detail;
    }

    private PagingMeta getPagingMeta(Page<Account> accounts) {
        PagingMeta meta = new PagingMeta();
        meta.setPageNumber(accounts.getNumber());
        meta.setPageSize(accounts.getSize());
        meta.setTotalElements(accounts.getTotalElements());
        meta.setTotalPages(accounts.getTotalPages());
        return meta;
    }

    private Page<Account> getAccounts(Date startDate, Date endDate, Pageable page) {
        Page<Account> accounts;
        if (startDate != null && endDate != null) {
            accounts = accountRepository.findByCreatedTimeBetween(new LocalDateTime(startDate.getTime()), new LocalDateTime(endDate.getTime()), page);
        } else {
            accounts = accountRepository.findAll(page);
        }
        return accounts;
    }

    private OtherDataDetailData getOtherDataDetailData(Account account) {
        OtherDataDetailData data = new OtherDataDetailData();
        data.setAccountId(account.getId());
        data.setAccountNickname(account.getNickname());
        //充值币余额/万个
        data.setCreditBalance(10);
        //充值币总额/万个
        data.setCreditSum(10);
        //积分余额/万个
        data.setPointsBalance(10);
        //积分总额/万个
        data.setPointsSum(10);
        //刷粉额/元
        data.setTaskCash(100);
        //刷纷积分/万个
        data.setTaskPoints(10);
        //刷单额/元
        data.setTaskOrderCash(100);
        //刷单积分/万个
        data.setTaskOrderPoints(10);
        //兑换现金积分额/万个
        data.setExchangeCashPoints(10);
        //兑换商品积分额/万个
        data.setExchangeProductPoints(10);
        //佣金积分额/万个
        data.setCommissionPoints(10);
        //自动刷积分额/万个
        data.setAutoTaskPoints(10);
        //扣除会员积分额/万个
        data.setCostAccountPoints(10);
        //充值赠送充值币额/万个
        data.setPresentedCredit(10);
        return data;
    }

    public MainDataDetailData getMainDataDetailByUserId(Long userId) {
        Optional<Account> optionalAccount = accountRepository.findById(userId);
        if (optionalAccount.isPresent()) {
            return getMainDataDetailData(optionalAccount.get());
        } else {
            throw ErrorBuilder.buildNotFoundErrorException("会员ID未找到。");
        }
    }

    private MainDataDetailData getMainDataDetailData(Account account) {
        MainDataDetailData data = new MainDataDetailData();
        data.setAccountId(account.getId());
        data.setAccountNickname(account.getNickname());
        //TODO
        //充值现金额（元）
        data.setCash(1000);
        //佣金额（元）
        data.setCommissionMoney(1000);
        //扣除积分额
        data.setCostPoints(1000);
        //自动刷任务额（元）
        data.setAutoTaskCash(1000);
        // 兑换现金额（元）
        data.setExchangeCash(1000);
        // 兑换商品额（元）
        data.setExchangeProductCash(1000);
        // 充值赠送充值币额（元）
        data.setPresentedCreditCash(1000);
        return data;
    }

    public OtherDataDetailData getOtherDataDetailByUserId(Long userId) {
        Optional<Account> optionalAccount = accountRepository.findById(userId);
        if (optionalAccount.isPresent()) {
            return getOtherDataDetailData(optionalAccount.get());
        } else {
            throw ErrorBuilder.buildNotFoundErrorException("会员ID未找到。");
        }
    }
}
