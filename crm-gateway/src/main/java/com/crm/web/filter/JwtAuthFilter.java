package com.crm.web.filter;

import com.crm.exceptionEnum.ExceptionEnum;
import com.crm.utils.JsonUtils;
import com.crm.utils.JwtTokenUtils;
import com.crm.utils.ResponseResult;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.data.redis.core.StringRedisTemplate;


import javax.servlet.http.HttpServletRequest;

public class JwtAuthFilter extends ZuulFilter {

    private StringRedisTemplate redisTemplate;

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext=RequestContext.getCurrentContext();
        try {
            HttpServletRequest request = requestContext.getRequest();
            //如果是访问swagger页面 或者是登陆认证直接放行不需要token认证
            if(request.getRequestURI().contains("/crm-auth/") ||
                    request.getRequestURI().contains("/v2/api-docs") ){
                return null;
            }
            //读取请求头中的token
            String token =request.getHeader(JwtTokenUtils.TOKEN_HEADER);
            if(StringUtils.isNotBlank(token)){//判断token是否非空
                //根据token获取用户名
                String username =JwtTokenUtils.getUsername(token);

                //根据用户名获取redis中的token
                String redisToken = redisTemplate.opsForValue().get(username);

                //如果说redis里面的token为空，说明token过期了
                if(StringUtils.isBlank(redisToken)){
                    errorRespone(requestContext, ExceptionEnum.UN_LOGIN); // 返回登录结果
                }else{
                    //如果token与redis中存储的redisToken一致且token没过期说明登录成功
                    if(token.equals(redisToken) &&
                    !JwtTokenUtils.isExpiration(token)){
                        return null;

                    }
                    errorRespone(requestContext,ExceptionEnum.UN_LOGIN);
                }

            }else{
                errorRespone(requestContext,ExceptionEnum.UN_LOGIN);
            }
            return  null;


        }catch (Exception e){
            errorRespone(requestContext,ExceptionEnum.TOKEN_ERROR);
            return null;
        }

    }

    public void errorRespone(RequestContext requestContext,ExceptionEnum exception){

        //返回校验的方法
        requestContext.addZuulResponseHeader("content- type",
                    "application/json;charset=utf-8");
        requestContext.setResponseBody(JsonUtils.serialize(new ResponseResult(exception)));
        requestContext.setResponseStatusCode(exception.getCode());
        requestContext.setSendZuulResponse(false);
    }


}
