package com.service.oss.controller;

import com.common.utils.R;
import com.service.oss.service.IFileService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author
 * @date 2020/5/2
 */
@RestController
@RequestMapping("/admin/oss/file")
public class FileController {

    @Autowired
    IFileService fileService;
    @PostMapping("/upload")
    public R upload(@ApiParam(name = "file", value = "文件上传") @RequestParam("file") MultipartFile file) throws Exception {
        String url = fileService.upload(file);
        return R.ok().put("url", url);
    }
}
