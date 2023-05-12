package com.hlh.contentsmart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = {"com.hlh.contentsmart"})
public class ContentSmartApplication {
    public static void main(String[] args) {
        SpringApplication.run(ContentSmartApplication.class, args);
    }

}
