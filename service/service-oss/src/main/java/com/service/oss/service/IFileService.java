package com.service.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author
 * @date 2020/5/2
 */
public interface IFileService {
    /**
     * OSS上传文件
     * @param file
     */
    String upload(MultipartFile file) throws Exception;
}
