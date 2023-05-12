package com.hlh.weipanserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.hlh.weipanserver.general.mapper")
public class WeipanServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeipanServerApplication.class, args);
    }

}
