package com.hlh.file.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    /**
     * 文件上传
     * @param file 文件对象
     * @return 上传后的url
     */
    String uploadFile(MultipartFile file);
}
