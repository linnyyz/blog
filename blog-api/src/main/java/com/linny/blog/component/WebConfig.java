package com.linny.blog.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;


@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final Logger logger = LoggerFactory.getLogger(WebConfig.class);// 配置日志

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")// 允许所有请求方法
                .allowedOrigins("*")//允许所有的源
                .allowedMethods("GET","POST","OPTIONS")// 允许的请求方法
                .allowedHeaders("*")// 允许所有请求头
                .exposedHeaders("Access-Control-Allow-Headers",
                        "Access-Control-Allow-Origin",
                        "Access-Control-Methods",
                        "Access-Control-Max-Age",
                        "X-Frame-Options")
                .allowCredentials(false)
                .maxAge(3600);
    }


    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }
}
