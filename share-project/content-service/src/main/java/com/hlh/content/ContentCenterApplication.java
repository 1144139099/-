package com.hlh.content;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(clients = {com.hlh.content.openfeign.UserService.class})
public class ContentCenterApplication {
    public static void main(String[] args){
        SpringApplication.run(ContentCenterApplication.class, args);
    }
}
