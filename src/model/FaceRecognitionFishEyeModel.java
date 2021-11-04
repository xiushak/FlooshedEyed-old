package model;

import org.opencv.core.*;
import org.opencv.objdetect.CascadeClassifier;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.util.Random;

/**
 * An implementation of {@link SimpleModel} that automatically fisheyes on a person's face.
 * It uses OpenCV to do facial recognition.
 */
public class FaceRecognitionFishEyeModel implements SimpleModel {

    private final FishEyeModel delegate;

    /**
     * Default constructor that sets the delegate to a {@link FishEyeModel} with no image uploaded.
     */
    public FaceRecognitionFishEyeModel() {
        this.delegate = new FishEyeModel();
    }

    public FaceRecognitionFishEyeModel(FishEyeModel delegate) {
        utils.ErrorCheckers.checkNull(delegate, "null delegate given");
        this.delegate = delegate;
    }

    public FaceRecognitionFishEyeModel(FishEyeModel delegate, BufferedImage bi) {
        utils.ErrorCheckers.checkNull(delegate, "null delegate given");
        utils.ErrorCheckers.checkNull(bi, "null image given");
        this.delegate = delegate;
        delegate.setImage(bi);
    }

    /**
     * Automatically sets x y to a random face detected in the image so given x and y are ignored. If no face is detected,
     * applies the filter on the center of the image.
     */
    @Override
    public void processImage(int x, int y) throws IllegalStateException {
        // Used resource below to learn how to do this
        // https://www.geeksforgeeks.org/image-processing-java-set-9-face-detection/
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        CascadeClassifier faceDetector = new CascadeClassifier();
        faceDetector.load("haarcascade_frontalface_alt.xml");

        BufferedImage bi = delegate.getImage();
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
        if (rects.length == 0) {
            delegate.processImage(bi.getWidth() / 2, bi.getHeight() / 2);
            return;
        }
        Random r = new Random();
        int face = r.nextInt(rects.length);
        Rect rect = rects[face];
        // perform fisheye at the center of the face
        delegate.processImage(rect.x + rect.width / 2, rect.y + rect.height / 2);
    }

    @Override
    public int getWidth() throws IllegalStateException {
        return delegate.getWidth();
    }

    @Override
    public int getHeight() throws IllegalStateException {
        return delegate.getHeight();
    }

    @Override
    public BufferedImage getImage() throws IllegalStateException {
        return delegate.getImage();
    }

    @Override
    public void setImage(BufferedImage bi) throws IllegalArgumentException {
        delegate.setImage(bi);
    }
}
