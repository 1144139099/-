package com.hlh.upload.service.impl;

import com.hlh.upload.common.ResponseResult;
import com.hlh.upload.service.UploadService;
import com.hlh.upload.utils.MinIoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class UploadServiceImpl implements UploadService {
    @Resource
    private MinIoTemplate minIoTemplate;
    @Override
    public String uploadFile(MultipartFile file) throws Exception {
        minIoTemplate.makeBucket("upload");
        String originalFileName = file.getOriginalFilename();
        minIoTemplate.putObject("upload",originalFileName,file.getInputStream());
        return "http://124.221.232.15:9090" + "/" + "upload" + "/" + originalFileName;
    }
}
