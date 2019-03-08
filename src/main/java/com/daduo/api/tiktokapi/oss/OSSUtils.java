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
    private static String endpoint = "http://oss-cn-shanghai.aliyuncs.com";
    private static String accessKeyId = "LTAISI1YFdZQQtI9";
    private static String accessKeySecret = "zcozoj7mTOZNbAjzLRV74C76gaO8u5";
    private static String bucketName = "tiktok";

    public static String upload(String folder, MultipartFile file) throws IOException {

        String objectName = folder + "/" + StringUtils.cleanPath(file.getOriginalFilename());
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        ossClient.putObject(bucketName, objectName, file.getInputStream());
        ossClient.shutdown();
        return "https://tiktok.oss-cn-shanghai.aliyuncs.com/" + objectName;
    }

    public static String getLocalImagePath(String imageUrl) throws IOException {
        // 创建OSSClient实例。
//        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
//
//        int startIndex = imageUrl.indexOf("/task/") + 1;
//        int endIndex = imageUrl.length();
//        //https://tiktok.oss-cn-shanghai.aliyuncs.com/avatar/961ad10137d3039d468ce8e0f96c78d2.jpg
//        String objectName = imageUrl.substring(startIndex, endIndex);
//// 调用ossClient.getObject返回一个OSSObject实例，该实例包含文件内容及文件元信息。
//        OSSObject ossObject = ossClient.getObject(bucketName, objectName);
//// 调用ossObject.getObjectContent获取文件输入流，可读取此输入流获取其内容。
//        InputStream content = ossObject.getObjectContent();
//        if (content != null) {
//
//            byte[] buffer = new byte[content.available()];
//            content.read(buffer);
//            File targetFile = new File("/tmp/" + objectName);
//            if (!targetFile.exists()) {
//                targetFile.getParentFile().mkdirs();
//                targetFile.createNewFile();
//            }
//            OutputStream outStream = new FileOutputStream(targetFile);
//            outStream.write(buffer);
//            outStream.flush();
//            outStream.close();

        URL url = new URL(imageUrl);
        BufferedImage image = ImageIO.read(url);
        File file = new File("tmp/" + url.getFile());
        ImageIO.write(image, "png", file);
        return file.getPath();
//        }

// 关闭OSSClient。
//        ossClient.shutdown();
//        return null;
    }
}
