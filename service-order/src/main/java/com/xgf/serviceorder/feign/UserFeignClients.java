package com.xgf.serviceorder.feign;

//import com.xgf.serviceorder.config.FooConfiguration;
import com.xgf.serviceorder.entity.User;
import com.xgf.serviceorder.feign.impl.UserFeignClientsFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author: xu gang feng
 * @create: 2019-12-30 23:36
 */
@FeignClient(value = "service-user",fallback = UserFeignClientsFallBack.class)
public interface UserFeignClients {

    @GetMapping("/user/getUser/{id}")
    public User getUser(@PathVariable Integer id);
}
