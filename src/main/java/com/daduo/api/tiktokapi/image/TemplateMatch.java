package com.daduo.api.tiktokapi.image;

import org.bytedeco.javacpp.opencv_core;

import static org.bytedeco.javacpp.helper.opencv_imgcodecs.cvLoadImage;
import static org.bytedeco.javacpp.opencv_core.*;
import static org.bytedeco.javacpp.opencv_imgproc.CV_TM_SQDIFF;
import static org.bytedeco.javacpp.opencv_imgproc.cvMatchTemplate;

public class TemplateMatch {
    private IplImage image;

    public void load(String filename) {
        image = cvLoadImage(filename);
    }

    public boolean matchTemplate(IplImage source) {
        boolean matchRes;
        IplImage result = cvCreateImage(opencv_core.cvSize(
                source.width() - this.image.width() + 1,
                source.height() - this.image.height() + 1),
                opencv_core.IPL_DEPTH_32F, 1);

        opencv_core.cvZero(result);
        cvMatchTemplate(source, this.image, result, CV_TM_SQDIFF);
        CvPoint maxLoc = new CvPoint();
        CvPoint minLoc = new CvPoint();
        double[] minVal = new double[2];
        double[] maxVal = new double[2];

        cvMinMaxLoc(result, minVal, maxVal, minLoc.getStringCodePoints(), maxLoc.getStringCodePoints(), null);
        matchRes = maxVal[0] > 0.99f;
        cvReleaseImage(result);
        return matchRes;
    }
}
