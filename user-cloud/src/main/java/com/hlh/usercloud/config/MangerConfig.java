package com.hlh.usercloud.config;

import cn.authing.core.mgmt.ManagementClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author hlh
 */
@Configuration
public class MangerConfig {
    @Bean
    public ManagementClient getClientInstance1(){
        return new ManagementClient("6368b106c55c8b3934a8e0fe","138148c18c21cd64f678c6485d498594");
    }
}

