package com.daduo.api.tiktokapi.controller;

import com.daduo.api.tiktokapi.oss.OSSUtils;
import com.daduo.api.tiktokapi.service.FileUploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
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
    @ApiOperation(value = "上传文件", notes = "头像文件夹：avatar， 产品文件夹：product，任务文件夹：task， 收款码文件夹：qrcode")
    public String upload(@PathVariable @ApiParam("文件夹名字") String folder, @RequestParam("file") MultipartFile file) throws IOException {
        return OSSUtils.upload(folder, file);
    }
}
