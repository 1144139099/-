package com.hlh.content.config;


import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogConfig {
    @Bean
    Logger.Level feignLogger(){
        return Logger.Level.FULL;
    }
}
