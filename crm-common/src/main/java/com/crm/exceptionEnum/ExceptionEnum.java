package com.crm.exceptionEnum;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionEnum {
    SUCCESS(200,"请求成功"),
    PARAM_IS_NULL(400,"请求参数为空");

    private int code;
    private String msg;



}
