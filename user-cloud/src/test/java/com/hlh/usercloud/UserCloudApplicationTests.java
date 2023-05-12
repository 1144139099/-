package com.hlh.usercloud;

import com.hlh.usercloud.utils.MinIoTemplate;
import io.minio.errors.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@SpringBootTest
class UserCloudApplicationTests {

    @Resource
    MinIoTemplate minIoTemplate;

    @Test
    void contextLoads() {

        try {
            minIoTemplate.makeBucket("hlh");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
