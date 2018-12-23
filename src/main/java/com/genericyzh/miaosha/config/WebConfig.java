package com.genericyzh.miaosha.config;

import com.genericyzh.miaosha.access.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author genericyzh
 * @date 2018/10/6 12:56
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration loginInterceptor = registry.addInterceptor(loginInterceptor());
        loginInterceptor.addPathPatterns("/**")
                .excludePathPatterns("/login/**", "/static/**", "/bootstrap/**", "/img/**", "/jquery-validation/**", "/js/**", "/layer/**");
    }

    @Bean
    public LoginInterceptor loginInterceptor() {
        return new LoginInterceptor();
    }

}
