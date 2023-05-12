package com.hlh.usercloud.config;
import cn.authing.core.auth.AuthenticationClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author hlh
 */
@Configuration
public class AuthenticationConfig {
    @Bean
    public AuthenticationClient getClientInstance(){
        return new AuthenticationClient("6369fe8ffcc7a6cc9f2027ac","https://smartschool.authing.cn");
    }
}

