package com.jgh.spring.cloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig {

    //负载均衡，因为配了集群，访问的地址是服务器的名字，但是不知道服务器的端口号，所以要用这个注解
    //自行选择服务器的端口，否则会报错
//    @LoadBalanced
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
