package com.crm.web.controller;

import com.crm.bean.Role;
import com.crm.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/crm")
@Api(value="角色管理",tags={"角色管理"})
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping("/getRole")
    @ApiOperation("根据ID查询角色")
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

    @GetMapping("/getRole")
    @ApiOperation("根据ID查询角色")
    public JSONObject getRoleById2(Long id){

        /*try {
            Role role = roleService.findRoleById(id);
            return ResponseEntity.status(200).body(role);
        } catch (CrmException e) {
            e.printStackTrace();
            return ResponseEntity.status(e.getExceptionEnum().getCode())
                    .body(e.getExceptionEnum().getMsg());}*/

        Role role = roleService.findRoleById(id);

        return null;


    }


}
