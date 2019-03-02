package com.daduo.api.tiktokapi.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileUploadService {
    public void store(MultipartFile file) throws IOException {
        Path rootLocation = Paths.get("files/avatars");
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        Files.copy(file.getInputStream(), rootLocation.resolve(filename),
                StandardCopyOption.REPLACE_EXISTING);

    }
}
