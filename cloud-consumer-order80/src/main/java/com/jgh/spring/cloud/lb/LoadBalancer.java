package com.jgh.spring.cloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

public interface LoadBalancer {

    /**
     * description 获取服务注册用户的所有信息
     * author jgh
     **/
    ServiceInstance instances(List<ServiceInstance> serviceInstanceList);
}
