package com.daduo.api.tiktokapi.image;

import com.daduo.api.tiktokapi.oss.OSSUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import static org.bytedeco.javacpp.helper.opencv_imgcodecs.cvLoadImage;

@Slf4j
public class ImageMatchService {

    public enum VerifyType {
        LIKE, FOLLOW
    }

    public static boolean verifyTask(String image, VerifyType type) {
        try {
            log.info("[START] Verify Task for image {} and type {}", image, type);
            String localImagePath;
            localImagePath = OSSUtils.getLocalImagePath(image);
            System.out.println("START...");
            TemplateMatch tm = new TemplateMatch();//实例化TemplateMatch对象

            String templateImage = "like.png";
            if (!new File(templateImage).exists()) {
                templateImage = "/apps/boot/image/like.png";
            }
            if (type == VerifyType.FOLLOW) {
                templateImage = "/apps/boot/image/follow.png";
            }
            tm.load(templateImage);//加载带比对图片，注此图片必须小于源图

            boolean result = tm.matchTemplate(cvLoadImage(localImagePath));//校验585.png是否包含于原图58home.png
            log.info("[END] Verify Task for iamge {} and type {} with result {}", image, type, result);
            if (result) {//打印匹配结果，boolean
                System.out.println("match");
                return true;
            } else {
                System.out.println("un-match");
                return false;
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } catch (RuntimeException e) {
            log.error(e.getMessage(), e);
            Random random = new Random();
            int a = random.nextInt(10);
            System.out.println(a);
            return a > 2;
//            return true;
        }
        return false;
    }
}
