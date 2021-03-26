package com.jgh.spring.cloud.lb.Imp;

import com.jgh.spring.cloud.lb.LoadBalancer;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * description 轮询算法替代syn和lock
 * param
 * return
 * author jgh
 **/
@Component
public class LoadBalancerImpl implements LoadBalancer {

//初始化原子数
      private  AtomicInteger atomicInteger = new AtomicInteger(0);

      //访问次数
      private final int getAndIncrement(){
          int current;
          int next;
          do{
              current=this.atomicInteger.get();
              next=current>=Integer.MAX_VALUE ? 0 : (current+1);
          }while (!this.atomicInteger.compareAndSet(current,next));
          System.out.println("####第几次访问==next"+next);
          return next;
      }

    @Override
    public ServiceInstance instances(List<ServiceInstance> serviceInstanceList) {
          //服务器的下标
          int index =getAndIncrement() % serviceInstanceList.size();
          //返回这个下标的服务器
        return serviceInstanceList.get(index);
    }
}
