package model.facerecognition;

import org.opencv.core.*;
import org.opencv.objdetect.CascadeClassifier;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.util.Random;

/**
 * Uses OpenCV to find a face. Returns a rectangle that contains the whole image if no face is found
 */

public class FaceRecognitionOPENCV implements FaceRecognition {

    public Rectangle findFace(BufferedImage bi) {
        // Used resource below to learn how to do this
        // https://www.geeksforgeeks.org/image-processing-java-set-9-face-detection/
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        CascadeClassifier faceDetector = new CascadeClassifier();
        faceDetector.load("haarcascade_frontalface_alt.xml");

        // removes alpha values to convert BufferedImage into OpenCV Mat
        BufferedImage rgbImage = new BufferedImage(bi.getWidth(), bi.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
        rgbImage.getGraphics().drawImage(bi, 0, 0, null);

        byte[] pixels = ((DataBufferByte) rgbImage.getRaster().getDataBuffer()).getData();
        Mat image = new Mat(rgbImage.getHeight(), bi.getWidth(), CvType.CV_8UC3);

        image.put(0, 0, pixels);

        MatOfRect faceDetections = new MatOfRect();
        faceDetector.detectMultiScale(image, faceDetections);

        Rect[] rects = faceDetections.toArray();
        // choose a random face
        int rectCount = rects.length;
        // if no face found
        if (rects.length == 0) {
            ;
            return new Rectangle(0, 0, bi.getWidth() / 2, bi.getHeight() / 2);
        }
        Random r = new Random();
        int face = r.nextInt(rects.length);
        Rect rect = rects[face];
        // returns rectangle
        return new Rectangle(rect.x, rect.y, rect.width, rect.height);
    }

}
