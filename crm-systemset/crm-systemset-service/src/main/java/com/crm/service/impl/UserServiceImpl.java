package com.crm.service.impl;

import com.crm.bean.User;
import com.crm.bean.UserExample;
import com.crm.mapper.UserMapper;
import com.crm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User findUserByNameAndPwd(String name,String password) {



        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsrNameEqualTo(name)
                .andUsrPasswordEqualTo(password);
        List<User> userList = userMapper.selectByExample(userExample);

        if( null!=userList && userList.size()>0 ){

             return userList.get(0);
        }

        return null;
    }
}
