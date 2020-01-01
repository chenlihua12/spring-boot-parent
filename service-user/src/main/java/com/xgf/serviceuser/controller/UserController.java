package com.xgf.serviceuser.controller;

import com.xgf.serviceuser.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * @author: xu gang feng
 * @create: 2019-12-30 20:44
 * @Description:
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/getUser/{id}")
    public User getUser(@PathVariable Integer id) throws InterruptedException {
//        //超时回退
//        int sleepTime = new Random().nextInt(3000);
//        logger.info("sleepTime:"+ sleepTime);
//        Thread.sleep(sleepTime);
//        //熔断
//        if(id == 1){
//            throw new NullPointerException();
//        }
//        //限流
//        Thread.sleep(3000);

        User user = new User(1,"xgf",20);
        return user;
    }
}
