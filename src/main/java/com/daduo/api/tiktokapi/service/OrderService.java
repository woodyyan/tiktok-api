package com.daduo.api.tiktokapi.service;

import com.daduo.api.tiktokapi.entity.Credit;
import com.daduo.api.tiktokapi.entity.ExchangeOrder;
import com.daduo.api.tiktokapi.entity.ProductOrder;
import com.daduo.api.tiktokapi.enums.OrderStatus;
import com.daduo.api.tiktokapi.exception.ErrorException;
import com.daduo.api.tiktokapi.model.*;
import com.daduo.api.tiktokapi.model.error.Error;
import com.daduo.api.tiktokapi.repository.CreditRepository;
import com.daduo.api.tiktokapi.repository.ExchangeOrderRepository;
import com.daduo.api.tiktokapi.repository.ProductOrderRepository;
import com.daduo.api.tiktokapi.translator.ExchangeTranslator;
import com.daduo.api.tiktokapi.translator.ProductOrderTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.daduo.api.tiktokapi.model.error.ErrorBuilder.buildNotFoundErrorException;

@Service
public class OrderService {

    @Autowired
    private ExchangeTranslator translator;

    @Autowired
    private ExchangeOrderRepository repository;

    @Autowired
    private ProductOrderTranslator productTranslator;

    @Autowired
    private ProductOrderRepository productOrderRepository;

    @Autowired
    private CreditRepository creditRepository;

    @Autowired
    private ReferenceValueService referenceValueService;

    public ExchangeResponse createExchangeMoneyOrder(ExchangeRequest exchangeRequest) {
        Integer pointsOfPerRmb = referenceValueService.searchByName("pointsOfPerRmb");
        Integer points = pointsOfPerRmb * exchangeRequest.getMoney();
        Credit credit = creditRepository.findByUserId(exchangeRequest.getUserId());
        if (credit.getPoints() < points) {
            Error error = new Error();
            error.setTitle("余额不足");
            error.setDetails("余额不足，请充值。");
            error.setStatus("412");
            throw new ErrorException(HttpStatus.OK, error);
        }
        ExchangeOrder order = translator.translateToExchangeOrder(exchangeRequest, points);
        repository.save(order);
        ExchangeResponse response = new ExchangeResponse();
        response.setMessage("提交成功，请等客服审核之后付款。");
        response.setStatus("SUCCESS");
        return response;
    }

    public ExchangeOrders getExchangeMoneyOrders(Long userId) {
        List<ExchangeOrder> orders = repository.findAllByUserId(userId);
        return translator.toExchangeOrders(orders);
    }

    public void updateExchangeOrderStatus(Long id, OrderStatus status) {
        Optional<ExchangeOrder> order = repository.findById(id);
        if (order.isPresent()) {
            ExchangeOrder exchangeOrder = order.get();
            exchangeOrder.setStatus(status);
            repository.saveAndFlush(exchangeOrder);
        } else {
            throw buildNotFoundErrorException("ID找不到，请确认ID是否正确。");
        }
    }

    public ProductOrderResponse createProductOrder(ProductOrderRequest productOrderRequest) {
        ProductOrder productOrder = productTranslator.translate(productOrderRequest);
        deductPoints(productOrder.getUserId(), productOrder.getPrice());
        ProductOrder savedOrder = productOrderRepository.save(productOrder);
        return productTranslator.translateToResponse(savedOrder);
    }

    private void deductPoints(Long userId, Integer price) {
        Credit credit = creditRepository.findByUserId(userId);
        credit.setPoints(credit.getPoints() - price);
        creditRepository.saveAndFlush(credit);
    }

    public ProductOrders getProductOrders(Pageable page) {
        Page<ProductOrder> productOrderPage = productOrderRepository.findAll(page);
        return productTranslator.toProductOrders(productOrderPage);
    }

    public ProductOrders getUserProductOrders(Long userId, Pageable page) {
        Page<ProductOrder> productOrderPage = productOrderRepository.findAllByUserId(userId, page);
        return productTranslator.toProductOrders(productOrderPage);
    }

    public ProductOrderResponse updateProductOrder(Integer productOrderId, ProductOrderRequest productOrderRequest) {
        Optional<ProductOrder> optionalProductOrder = productOrderRepository.findById(Long.valueOf(productOrderId));
        if (optionalProductOrder.isPresent()) {
            ProductOrder productOrder = optionalProductOrder.get();
            if (productOrderRequest.getStatus() != null) {
                productOrder.setStatus(productOrderRequest.getStatus());
            }
            if (productOrderRequest.getCount() != null) {
                productOrder.setCount(productOrderRequest.getCount());
            }
            if (productOrderRequest.getTotalPrice() != null) {
                productOrder.setPrice(productOrderRequest.getTotalPrice());
            }
            ProductOrder savedProductOrder = productOrderRepository.saveAndFlush(productOrder);
            return productTranslator.translateToResponse(savedProductOrder);
        } else {
            throw buildNotFoundErrorException("订单ID找不到，请确认ID是否正确。");
        }
    }
}
