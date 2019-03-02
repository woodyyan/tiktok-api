package com.daduo.api.tiktokapi.controller;

import com.daduo.api.tiktokapi.exception.ErrorException;
import com.daduo.api.tiktokapi.model.error.Error;
import com.daduo.api.tiktokapi.service.FileUploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequestMapping("/upload")
@RestController
@Slf4j
@Api(tags = "上传接口", description = "上传图片")
public class FileUploadController {

    @Autowired
    private FileUploadService storageService;

    @PostMapping("/avatar")
    @ApiOperation(value = "上传头像")
    public String handleFileUpload(@RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                return storageService.store(file);
            } catch (IOException | RuntimeException e) {
                Error error = new Error();
                error.setTitle("上传失败");
                error.setDetails(e.getLocalizedMessage());
                error.setStatus("400");
                throw new ErrorException(HttpStatus.OK, error);
            }
        } else {
            Error error = new Error();
            error.setTitle("上传失败");
            error.setDetails("文件不能为空。");
            error.setStatus("400");
            throw new ErrorException(HttpStatus.OK, error);
        }
    }
}
