package com.jgh.spring.cloud.controller;

import com.jgh.spring.cloud.entities.CommonResult;
import com.jgh.spring.cloud.entities.Payment;
import com.jgh.spring.cloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@Slf4j
public class PaymentController {

    @Resource
    PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;
    /**
     * description  因为前后端分离，所以这里的返回类型是CommonResult 是返回给前端看的数据，
     * 后端的是service（自己看）
     * param [payment]
     * return com.jgh.spring.cloud.entities.CommonResult
     * author king
     **/
    @PostMapping(value = "/payment/create")
    public CommonResult create(Payment payment){
        int result = paymentService.create(payment);
        log.info("插入结果*********"+result);
        if(result>0){
            return  new CommonResult(200,"成功",result);
        }else {
            return new CommonResult(404,"失败",null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById( @PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("查询*********"+payment+"1");
        if(payment !=null){
            return  new CommonResult(200,"成功id" + serverPort,payment);
        }else {
            return new CommonResult(404,"失败id" + id,null);
        }
    }

    @GetMapping(value = "/payment/lb")
    public String getPaymentLB(){
        return serverPort;
    }

}
