package com.jgh.spring.cloud.controller;

import com.jgh.spring.cloud.entities.CommonResult;
import com.jgh.spring.cloud.entities.Payment;
import com.jgh.spring.cloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;


@RestController
@Slf4j
public class PaymentController {

    @Resource
    PaymentService paymentService;

    //读取yml配置文件对应的信息
    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;

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
            return  new CommonResult(200,"成功"+serverPort,result);
        }else {
            return new CommonResult(404,"失败",null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById( @PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("查询*********"+payment+"1");
        if(payment !=null){
            return  new CommonResult(200,"成功id"+serverPort,payment);
        }else {
            return new CommonResult(404,"失败id" + id,null);
        }
    }

    @RequestMapping("/payment/discovery")
    public Object discovery(){
        //获取有多少个服务站点
        List<String> services = discoveryClient.getServices();
        for (String element :  services)
        {
            log.info("ele======"+element);
        }

        //返回的是一个服务里面有多少个占用端口
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance element : instances){
            log.info("ins===="+instances);
        }
        //返回当前对象的discovery信息
        return this.discoveryClient;
    }

    @GetMapping(value = "/payment/lb")
    public String getPaymentLB(){
        return serverPort;
    }


    @GetMapping(value = "/payment/feign/timeout")
    public String timeOut() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        return serverPort;
    }

}
