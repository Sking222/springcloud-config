package com.jgh.spring.cloud.service;

import com.jgh.spring.cloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

public interface PaymentService {
    public int create(Payment payment);


    public Payment getPaymentById(Long id);
}
