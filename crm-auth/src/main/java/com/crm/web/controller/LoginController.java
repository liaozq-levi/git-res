package com.crm.web.controller;


import com.crm.client.UserClient;
import com.crm.exceptionEnum.ExceptionEnum;
import com.crm.utils.JwtTokenUtils;
import com.crm.utils.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@Data
@NoArgsConstructor
@AllArgsConstructor
class User{
    private String username;
    private String password;
}

@RestController
@RequestMapping("/auth")
@Api(value="登录认证",tags={"登录认证"})
public class LoginController {

    /*
    * 专门处理String类型数据的RedisTemplate
    * */
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private UserClient userClient;

    @PostMapping("/login")
    @ApiOperation("令牌认证登录")
    public ResponseEntity login(@RequestBody User user){
        String username = user.getUsername();
        String password = user.getPassword();
        System.out.println(username);
        System.out.println(password);

        Object curruser = userClient.login(username,password);
        if(null!=curruser){
            //登录成功 创建token
            String token = JwtTokenUtils.createToken(username);
            //将token放入redis中
            redisTemplate.opsForValue().set(username,token);
            //设置过期时间
            //expire(key,时间,单位)
            redisTemplate.expire(username,JwtTokenUtils.EXPIRATION, TimeUnit.MILLISECONDS);

            return ResponseEntity.ok(token);

        }else{
            //如果登录失败返回错误信息
            return ResponseEntity
                    .status(
                            ExceptionEnum.USERNAME_OR_PASSWORD_ERROR.getCode())
                    .body(
                            //返回Jeson类型的错误信息
                            new ResponseResult(ExceptionEnum.USERNAME_OR_PASSWORD_ERROR));
        }

    }
}
