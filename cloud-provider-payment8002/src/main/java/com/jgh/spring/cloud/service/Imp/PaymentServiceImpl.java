package com.jgh.spring.cloud.service.Imp;

import com.jgh.spring.cloud.dao.PaymentDao;
import com.jgh.spring.cloud.entities.Payment;
import com.jgh.spring.cloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Resource
    PaymentDao paymentDao;


    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
