package com.hlh.file.controller;

import com.hlh.file.common.ResponseResult;
import com.hlh.file.common.ResultCode;
import com.hlh.file.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@RestController
@RequestMapping("/file")
@Slf4j
public class FileApiController {
    @Resource
    private FileService fileService;

    @PostMapping("/upload")
    public ResponseResult uploadFile(MultipartFile file){
        System.out.println("enter.....");
        String path = null;
        if (file != null) {
            log.info(file.toString());
            String fileName = file.getOriginalFilename();
            log.info(fileName);
            path = fileService.uploadFile(file);
            return ResponseResult.success(path);
        }else{
            return ResponseResult.failure(ResultCode.UPLOAD_ERROR,"文件上传失败");
        }

    }
}

