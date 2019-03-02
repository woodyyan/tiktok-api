package com.daduo.api.tiktokapi.controller;

import com.aliyun.oss.OSSClient;
import com.daduo.api.tiktokapi.service.FileUploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequestMapping("/upload")
@RestController
@Slf4j
@Api(tags = "上传接口", description = "上传图片")
public class FileUploadController {

    @Autowired
    private FileUploadService storageService;

    @Autowired
    private ResourceLoader resourceLoader;

    @PostMapping("/file/{folder}")
    @ApiOperation(value = "上传文件", notes = "头像文件夹：avatar， 产品文件夹：product，任务文件夹：task")
    public String upload(@PathVariable @ApiParam("文件夹名字") String folder, @RequestParam("file") MultipartFile file) throws IOException {
        String endpoint = "http://oss-cn-shanghai.aliyuncs.com";
        String accessKeyId = "LTAISI1YFdZQQtI9";
        String accessKeySecret = "zcozoj7mTOZNbAjzLRV74C76gaO8u5";
        String bucketName = "tiktok";
        String objectName = folder + "/" + StringUtils.cleanPath(file.getOriginalFilename());
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        ossClient.putObject(bucketName, objectName, file.getInputStream());
        ossClient.shutdown();
        return "https://tiktok.oss-cn-shanghai.aliyuncs.com/" + objectName;
    }
}
