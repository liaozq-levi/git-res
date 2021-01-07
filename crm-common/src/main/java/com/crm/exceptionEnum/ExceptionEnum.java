package com.crm.exceptionEnum;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionEnum {
    SUCCESS(200,"请求成功"),
    PARAM_IS_NULL(400,"请求参数为空"),
    UN_LOGIN(401,"未登录"),
    USERNAME_OR_PASSWORD_ERROR(402,"用户名或密码错误"),
    TOKEN_ERROR(403,"令牌失效");

    private int code;
    private String msg;



}
