package com.jgh.spring.cloud.service;


import org.springframework.stereotype.Component;

@Component
public class PaymentHystrixServiceImpl implements PaymentHystrixService{
    @Override
    public String paymentInfo_OK(Integer id) {
        return "OK,宕机";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "timeout,";
    }
}
