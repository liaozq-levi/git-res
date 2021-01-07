package com.crm.service;

import com.crm.bean.User;


public interface UserService {

    User findUserByNameAndPwd(String name,String password);

}
