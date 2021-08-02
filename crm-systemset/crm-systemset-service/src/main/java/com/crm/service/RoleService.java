package com.crm.service;

import com.crm.bean.Role;
import com.crm.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    private RoleMapper roleMapper;

    public void addRole(Role role) {
        roleMapper.insert(role);
    }

}
