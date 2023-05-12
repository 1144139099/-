package com.hlh.file.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.hlh.file.service.FileService;
import com.hlh.file.utils.AliyunResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    @Resource
    private AliyunResource aliyunResource;
    /**
     * 文件上传
     *
     * @param file 文件对象
     * @return 上传后的url
     */
    @Override
    public String uploadFile(MultipartFile file) {
        String bucketName = aliyunResource.getBucket();
        String endPoint = aliyunResource.getEndpoint();
        String accessKeyId = aliyunResource.getAccessKeyId();
        String accessKeySecret = aliyunResource.getAccessKeySecret();

        OSS ossClient = new OSSClientBuilder().build(endPoint, accessKeyId, accessKeySecret);
        String fileName = file.getOriginalFilename();
        String[] fileNameArr = fileName.split("\\.");
        String suffix = fileNameArr[fileNameArr.length - 1];
        String uploadFileName = bucketName + UUID.randomUUID().toString() + "." + suffix;

        InputStream inputStream = null;
        try{
            inputStream = file.getInputStream();
        }catch (IOException e){
            System.err.println("文件上传出现异常");
        }
        //上传oss
        ossClient.putObject(bucketName,uploadFileName,inputStream);
        ossClient.shutdown();
        return "https://" + bucketName + "." + endPoint + "/" + uploadFileName;
    }
}
