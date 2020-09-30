package com.crm.web;

import com.crm.bean.Role;
import com.crm.exception.CrmException;
import com.crm.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/crm")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping("/getRole")
    public ResponseEntity getRoleById(Long id){

        /*try {
            Role role = roleService.findRoleById(id);
            return ResponseEntity.status(200).body(role);
        } catch (CrmException e) {
            e.printStackTrace();
            return ResponseEntity.status(e.getExceptionEnum().getCode())
                    .body(e.getExceptionEnum().getMsg());}*/

        Role role = roleService.findRoleById(id);
        return ResponseEntity.status(200).body(role);


    }
}
