package com.xgf.serviceorder.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.xgf.serviceorder.entity.User;
import com.xgf.serviceorder.feign.UserFeignClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author: xu gang feng
 * @create: 2019-12-30 20:54
 * @Description:
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserFeignClients userFeignClients;

    @GetMapping(value = "/getEurekaUser/{userId}")
    public Object getEurekaUser(@PathVariable Integer userId){
        return userFeignClients.getUser(userId);
    }


    @HystrixCommand(fallbackMethod = "getFallBack",
            groupKey = "orderGroup",
            threadPoolKey = "orderThreadPool",
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize",value = "2"),
                    @HystrixProperty(name = "maxQueueSize",value = "1"),
                    @HystrixProperty(name = "queueSizeRejectionThreshold",value = "6")
            },
            commandProperties = {
                @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "20000")
                //@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "3")
                //@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000")
            })
    @GetMapping(value = "/getUser/{id}")
    public Object getUser(@PathVariable Integer id){
        return restTemplate.getForEntity("http://service-user/user//getUser/"+id, Object.class);
    }

    public User getFallBack(Integer id){
        User user = new User();
        user.setId(-1);
        user.setAge(20);
        user.setName(null);
        return user;
    }
}
