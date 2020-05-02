package com.service.oss.service;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.PutObjectResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.UUID;

/**
 * @author
 * @date 2020/5/2
 */
@Service
public class FileServiceImpl implements IFileService {

    @Resource
    OSS ossClient;

    @Value("${spring.cloud.alicloud.oss.bucket}")
    String bucketName;
    @Value("${spring.cloud.alicloud.oss.endpoint}")
    String endpoint;

    @Override
    public String upload(MultipartFile file) throws Exception{
        String filePath = "http://";
        String fileName = UUID.randomUUID().toString() + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        // 上传文件
        ossClient.putObject(bucketName, fileName, file.getInputStream());
        // 关闭OSSClient。
        filePath = filePath + bucketName + "." + endpoint + "/" + fileName;
        return filePath;
    }
}
