package com.daduo.api.tiktokapi.service;

import com.daduo.api.tiktokapi.entity.*;
import com.daduo.api.tiktokapi.model.FinancialInfo;
import com.daduo.api.tiktokapi.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

        //充值赠送积分总额:
        info.setTopUpPresentedCreditAmount(1);
        info.setGrossProfitAmount(1);
        info.setCreditAmount(1);
        info.setPointsAmount(1);
        info.setPointsAmountWithoutPoints(1);
        info.setCommissionPointsAmount(1);
        info.setCreditBalance(1);
        info.setPointsBalance(1);
        info.setExchangeMoneyPointsAmount(1);
        info.setExchangeProductPointsAmount(1);
        return info;
    }
}

// 充值现金总额 TopUpMoneyAmount：¥50000
// 佣金总额 commissionMoneyAmount：¥20000
// 扣除会员积分总额 MoneyAmountWithoutPoints：¥200
// 会员兑换现金总额 exchangeMoneyAmount：¥10000
// 会员兑换商品总额 exchangeProductAmount：¥5000
// 充值赠送积分总额 TopUpPresentedCreditAmount：¥500
// 毛利总额 GrossProfitAmount：¥20000
// 充值币总额 CreditAmount：5000万个
// 积分总额 PointsAmount：5000万个
// 扣除会员积分总额 PointsAmountWithoutPoints：5000万个
// 佣金积分总额 CommissionPointsAmount：20000万个
// 充值币余额 CreditBalance：200万个
// 积分余额 PointsBalance：200万个
// 会员兑换现金积分总额 exchangeMoneyPointsAmount：1000万个
// 会员兑换商品积分总额 exchangeProductPointsAmount：1000万个
