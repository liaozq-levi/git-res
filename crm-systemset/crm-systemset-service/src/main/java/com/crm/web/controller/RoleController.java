package com.crm.web.controller;

import com.crm.bean.Role;
import com.crm.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    RoleService roleService;

    @PostMapping("/add")
    public void addRole(Role role) {
        roleService.addRole(role);
    }

}
