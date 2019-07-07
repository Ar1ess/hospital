/*
 * Copyright (c) 2014-2019 www.itgardener.cn. All rights reserved.
 */

package com.softlab.hospital.web.interceptor;

import com.softlab.hospital.common.ErrorMessage;
import com.softlab.hospital.common.RestData;
import com.softlab.hospital.common.util.JsonUtil;
import com.softlab.hospital.common.util.TokenUtil;
import com.softlab.hospital.core.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Created by LiXiwen on 2019/7/3 18:02.
 * //方式一
 * implements HandlerInterceptor
 * //方式二
 * extends HandlerInterceptorAdapter
 * HandlerInterceptorAdapter实现了HandlerInterceptor接口得子接口
 * 当让了有的时候
 * 只是实现一个方法，那我们就继承类HandlerInterceptorAdapter
 * 如果全部实现实现接口HandlerInterceptor
 * TokenInterceptor--preHandle:在请求处理之前进行调用（Controller方法调用之前）
 */
@Component
public class TokenInterceptor extends HandlerInterceptorAdapter {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String path = request.getRequestURI();
        logger.debug("TokenInterceptor");

        boolean result = path.contains("login") || "OPTIONS".equals(request.getMethod());

        if (result) {
            return true;
        } else {
            User user = TokenUtil.getUserByToken(request);
            if (null == user) {
                logger.info("TokenInterceptor failed, token=" + request.getHeader("token"));
                try {
                    responseJson(response, new RestData(2, ErrorMessage.PLEASE_RELOGIN));
                } catch (Exception e) {
                    logger.error(e.getLocalizedMessage());
                }
                return false;
            } else {
                logger.info("TokenInterceptor success, token=" + request.getHeader("token"));
                return true;
            }
        }
    }

    private void responseJson(HttpServletResponse response, RestData restData) throws Exception {
        response.setContentType("application/json; charset=utf-8");
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "*");
        response.addHeader("Access-Control-Allow-Headers", "*");
        response.addHeader("Access-Control-Allow-Credentials", "true");
        PrintWriter writer = response.getWriter();
        writer.print(JsonUtil.getJsonString(restData));
        response.flushBuffer();
        writer.close();
    }
}
