package com.crm.web.controller;

import com.crm.bean.User;
import com.crm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/findUserByNameAndPwd")
    public User findUserByNameAndPwd(String name,String password){
        return userService.findUserByNameAndPwd(name,password);
    }
}
