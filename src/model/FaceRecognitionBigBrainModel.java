package model;

import model.facerecognition.FaceRecognition;
import model.facerecognition.Rectangle;

import java.awt.image.BufferedImage;

/**
 * An extension of {@link FaceRecognitionFishEyeModel} that automatically fisheyes on a person's forehead (or tries to).
 */
public class FaceRecognitionBigBrainModel extends FaceRecognitionFishEyeModel {

    /**
     * Default constructor that sets the delegate to a {@link FishEyeModel} with no image uploaded.
     */
    public FaceRecognitionBigBrainModel() {
        super();
    }

    /**
     * Constructor that sets the delegate to given {@link SimpleModel}.
     */
    public FaceRecognitionBigBrainModel(FishEyeModel delegate) {
        super(delegate);
    }

    /**
     * Constructor that sets the delegate to given {@link SimpleModel} and given image.
     */
    public FaceRecognitionBigBrainModel(FishEyeModel delegate, BufferedImage bi) {
        super(delegate, bi);
    }

    /**
     * Constructor that sets the delegate to a {@link FishEyeModel} with no image uploaded and sets the {@link FaceRecognition} delegate.
     */
    public FaceRecognitionBigBrainModel(FaceRecognition faceRecognitionDelegate) {
        super(faceRecognitionDelegate);
    }

    /**
     * Constructor that sets the delegate to given {@link SimpleModel} and {@link FaceRecognition} delegate.
     */
    public FaceRecognitionBigBrainModel(FishEyeModel delegate, FaceRecognition faceRecognitionDelegate) {
        super(delegate, faceRecognitionDelegate);
    }

    /**
     * Constructor that sets the delegate to given {@link SimpleModel}, image, and {@link FaceRecognition} delegate.
     */
    public FaceRecognitionBigBrainModel(FishEyeModel delegate, BufferedImage bi, FaceRecognition faceRecognitionDelegate) {
        super(delegate, bi, faceRecognitionDelegate);
    }

    /**
     * Automatically sets x y to a random face detected in the image so given x and y are ignored. If no face is detected,
     * applies the filter at coordinate (width/2, height/5) on the image.
     */
    @Override
    public void processImage(int x, int y) throws IllegalStateException {
        Rectangle rect = faceRecognitionDelegate.findFace(delegate.getImage());
        delegate.processImage(rect.x() + rect.width() / 2, rect.y() + rect.height() / 5);
    }
}
