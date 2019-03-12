package com.daduo.api.tiktokapi.oss;

import com.aliyun.oss.OSSClient;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class OSSUtils {

    public static String upload(String folder, MultipartFile file) throws IOException {

        String objectName = folder + "/" + StringUtils.cleanPath(file.getOriginalFilename());
        String endpoint = "http://oss-cn-shanghai.aliyuncs.com";
        String accessKeyId = "LTAISI1YFdZQQtI9";
        String accessKeySecret = "zcozoj7mTOZNbAjzLRV74C76gaO8u5";
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        String bucketName = "tiktok";
        ossClient.putObject(bucketName, objectName, file.getInputStream());
        ossClient.shutdown();
        return "https://tiktok.oss-cn-shanghai.aliyuncs.com/" + objectName;
    }

    public static String getLocalImagePath(String imageUrl) throws IOException {
        URL url = new URL(imageUrl);
        BufferedImage image = ImageIO.read(url);
        File file = new File("tmp/" + url.getFile());
        ImageIO.write(image, "png", file);
        return file.getPath();
    }
}
