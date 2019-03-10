package com.daduo.api.tiktokapi.image;

import com.daduo.api.tiktokapi.oss.OSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.lept;
import org.bytedeco.javacpp.tesseract;

import java.io.File;
import java.io.IOException;

import static org.bytedeco.javacpp.lept.pixDestroy;
import static org.bytedeco.javacpp.lept.pixRead;

@Slf4j
public class OCRService {

    private static final String VERIFY_WORD = "NICE";

    public static boolean doOCR(String imageFilename) {
        try {
            BytePointer outText;

            tesseract.TessBaseAPI api = new tesseract.TessBaseAPI();
            // Initialize tesseract-ocr with English, without specifying tessdata path
            //TODO tessdata无法找到
            String data = "/Users/songbai.yan/Documents/Projects/tiktok-api/tessdata";
            if (!new File(data).exists()) {
                data = "/apps/boot/tessdata";
            }

            if (api.Init(data, "eng") != 0) {
                System.err.println("Could not initialize tesseract.");
                System.exit(1);
            }

            // Open input image with leptonica library
            lept.PIX image = pixRead(imageFilename);
            api.SetImage(image);
            // Get OCR result
            outText = api.GetUTF8Text();
            String result = outText.getString();
            System.out.println("OCR output:\n" + result);

            // Destroy used object and release memory
            api.End();
            outText.deallocate();
            pixDestroy(image);

            if (result.contains(VERIFY_WORD)) {
                return true;
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return false;
        }
        return false;
    }

    public static boolean verifyTask(String commentImage) {
        if (commentImage != null) {
            try {
                String localImagePath = OSSUtils.getLocalImagePath(commentImage);
                if (localImagePath != null) {
                    boolean result = doOCR(localImagePath);
                    File file = new File(localImagePath);
                    if (file.exists()) {
                        file.delete();
                    }
                    return result;
                }
            } catch (IOException e) {
                log.error(e.getMessage());
                return false;
            }

        }
        return false;
    }
}
