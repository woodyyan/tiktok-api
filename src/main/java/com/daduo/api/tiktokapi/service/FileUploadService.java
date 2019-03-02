package com.daduo.api.tiktokapi.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileUploadService {
    public String store(MultipartFile file) throws IOException {
        Path rootLocation = Paths.get("files/avatars");
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        File dest = new File(String.valueOf(rootLocation.resolve(filename)));
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            boolean mkdirs = dest.getParentFile().mkdirs();// 新建文件夹
        }
        String path = rootLocation.resolve(filename).toString();
        Files.copy(file.getInputStream(), rootLocation.resolve(filename), StandardCopyOption.REPLACE_EXISTING);
        return path;
    }
}
