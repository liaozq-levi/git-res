package com.crm.service.impl;

import com.crm.bean.Role;
import com.crm.exception.CrmException;
import com.crm.exceptionEnum.ExceptionEnum;
import com.crm.mapper.RoleMapper;
import com.crm.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Role findRoleById(Long id) {
        if(null==id){
            throw new CrmException(ExceptionEnum.PARAM_IS_NULL);
        }
        return roleMapper.selectByPrimaryKey(id);
    }
}
