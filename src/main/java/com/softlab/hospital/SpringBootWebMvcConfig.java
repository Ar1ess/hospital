package com.softlab.hospital;

import com.softlab.hospital.web.interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by LiXiwen on 2019/7/3 18:14.
 **/
@Configuration
public class SpringBootWebMvcConfig implements WebMvcConfigurer {
    private final TokenInterceptor tokenInterceptor;

    @Autowired
    public SpringBootWebMvcConfig(TokenInterceptor tokenInterceptor) {
        this.tokenInterceptor = tokenInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // addPathPatterns("/**") 表示拦截所有的请求,
        // addPathPatterns 用来设置拦截路径，
        // excludePathPatterns 用来设置白名单，也就是不需要触发这个拦截器的路径。
        registry.addInterceptor(tokenInterceptor).addPathPatterns("/**");
    }
}
