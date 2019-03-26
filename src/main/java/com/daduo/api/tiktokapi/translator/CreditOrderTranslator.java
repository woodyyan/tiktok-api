package com.daduo.api.tiktokapi.translator;

import com.daduo.api.tiktokapi.entity.CreditOrder;
import com.daduo.api.tiktokapi.model.CreditOrderData;
import com.daduo.api.tiktokapi.model.CreditOrders;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CreditOrderTranslator {
    public CreditOrders toCreditOrders(List<CreditOrder> creditOrders) {
        CreditOrders result = new CreditOrders();
        List<CreditOrderData> data = new ArrayList<>();
        for (CreditOrder creditOrder : creditOrders) {
            CreditOrderData creditOrderData = new CreditOrderData();
            creditOrderData.setCredit(creditOrder.getCredit() == null ? 0 : creditOrder.getCredit());
            creditOrderData.setPresentedCredit(creditOrder.getPresentedCredit());
            creditOrderData.setCreatedTime(creditOrder.getCreatedTime().toDateTime());
            creditOrderData.setLastModifiedTime(creditOrder.getLastModifiedTime().toDateTime());
            creditOrderData.setId(creditOrder.getId());
            creditOrderData.setPoints(creditOrder.getPoints() == null ? 0 : creditOrder.getPoints());
            creditOrderData.setMoney(creditOrder.getMoney() == null ? 0 : creditOrder.getMoney());
            creditOrderData.setUserId(creditOrder.getUserId());
            data.add(creditOrderData);
        }
        result.setData(data);

        result.setTotalPoints(data.stream().mapToInt(CreditOrderData::getPoints).sum());
        return result;
    }
}
