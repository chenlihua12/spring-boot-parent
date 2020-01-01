package com.xgf.serviceorder.feign.impl;

import com.xgf.serviceorder.entity.User;
import com.xgf.serviceorder.feign.UserFeignClients;
import org.springframework.stereotype.Component;

/**
 * @author: xu gang feng
 * @create: 2019-12-31 13:56
 * @Description:
 */
@Component
public class UserFeignClientsFallBack implements UserFeignClients {
    @Override
    public User getUser(Integer id) {
        User user = new User();
        user.setId(-1);
        user.setAge(20);
        user.setName(null);
        return user;
    }
}
