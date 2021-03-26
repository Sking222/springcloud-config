package com.jgh.spring.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer //开启服务注册
public class EurekaMain7002 {

        public static void main(String[] args) {
            SpringApplication.run(EurekaMain7002.class,args);
        }


}
