package com.hlh.upload.utils;

import io.minio.*;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.InputStream;

/**
 * @author hlh
 */
@Component
@Configuration
@Data
@ConfigurationProperties(prefix = "minio")
public class MinIoTemplate {

    private String endpoint;
    private String accessKey;
    private String secretKey;


    private MinioClient instance;

    @PostConstruct //minio操作对象实例化
    public void init() {
        instance = MinioClient.builder()
                .endpoint(endpoint)
                .credentials(accessKey, secretKey)
                .build();
    }

    /**
     * 判断 bucket是否存在
     */
    public boolean bucketExists(String bucketName) throws Exception {

        return instance.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
    }

    /**
     * 创建 bucket
     */
    public void makeBucket(String bucketName) throws Exception {

        boolean isExist = this.bucketExists(bucketName);
        if (!isExist) {
            instance.makeBucket(MakeBucketArgs.builder()
                    .bucket(bucketName)
                    .build());
        }
    }

    /**
     * 文件上传
     *
     * @param bucketName bucket名称
     * @param objectName 对象名称，文件名称
     * @param filepath   文件路径
     */
    public ObjectWriteResponse putObject(String bucketName, String objectName, String filepath)
            throws Exception {

        return instance.uploadObject(
                UploadObjectArgs.builder()
                        .bucket(bucketName)
                        .object(objectName)
                        .filename(filepath).build());
    }

    /**
     * 文件上传
     *
     * @param bucketName  bucket名称
     * @param objectName  对象名称，文件名称
     * @param inputStream 文件输入流
     */
    public ObjectWriteResponse putObject(String bucketName, String objectName, InputStream inputStream)
            throws Exception {

        return instance.putObject(
                PutObjectArgs.builder()
                        .bucket(bucketName)
                        .object(objectName).stream(
                                inputStream, -1, 10485760)
                        .build());

    }

    /**
     * 删除文件
     *
     * @param bucketName bucket名称
     * @param objectName 对象名称
     */
    public void removeObject(String bucketName, String objectName)
            throws Exception {

        instance.removeObject(RemoveObjectArgs.builder()
                .bucket(bucketName)
                .object(objectName)
                .build());

    }
}