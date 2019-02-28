package com.daduo.api.tiktokapi.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/upload")
@RestController
@Slf4j
@Api(tags = "上传接口", description = "上传图片")
public class FileUploadController {
}
