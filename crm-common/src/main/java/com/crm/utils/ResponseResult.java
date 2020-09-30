package com.crm.utils;

import com.crm.exceptionEnum.ExceptionEnum;
import lombok.Data;

import java.util.Date;
@Data
public class ResponseResult {
    private int code;
    private String msg;
    private Date date;

    public  ResponseResult(ExceptionEnum exceptionEnum){
        this.code = exceptionEnum.getCode();
        this.msg = exceptionEnum.getMsg();
        this.date = new Date();
    }
}
