package com.linny.blog.utils;


import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableAsync
public class ExecutorConfig {

    @Bean("Executor")
    public Executor getExecutor(){
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                5,
                10,
                60,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10)
        );
        return threadPoolExecutor;
    }

    @Test
    public void testBCryptPasswordEncoder() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encode = passwordEncoder.encode("1234");
        String encode2 = passwordEncoder.encode("123456");
        boolean matches = passwordEncoder.matches("1234", "$2a$10$WCD7xp6lxrS.PvGmL86nhuFHMKJTc58Sh0dG1EQw0zSHjlLFyFvde");
        System.out.println(encode);
        System.out.println(encode2);
        System.out.println(matches);
    }


}
