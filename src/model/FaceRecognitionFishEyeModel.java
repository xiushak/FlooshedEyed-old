package model;

import model.facerecognition.FaceRecognition;
import model.facerecognition.FaceRecognitionOPENCV;
import model.facerecognition.Rectangle;

import java.awt.image.BufferedImage;

/**
 * An implementation of {@link SimpleModel} that automatically fisheyes on the center of a person's face.
 */
public class FaceRecognitionFishEyeModel implements SimpleModel {

    protected final FishEyeModel delegate;
    protected final FaceRecognition faceRecognitionDelegate;

    /**
     * Default constructor that sets the delegate to a {@link FishEyeModel} with no image uploaded.
     */
    public FaceRecognitionFishEyeModel() {
        this.delegate = new FishEyeModel();
        this.faceRecognitionDelegate = new FaceRecognitionOPENCV();
    }

    /**
     * Constructor that sets the delegate to given {@link SimpleModel}.
     */
    public FaceRecognitionFishEyeModel(FishEyeModel delegate) {
        utils.ErrorCheckers.checkNull(delegate, "null delegate given");
        this.delegate = delegate;
        this.faceRecognitionDelegate = new FaceRecognitionOPENCV();
    }

    /**
     * Constructor that sets the delegate to given {@link SimpleModel} and given image.
     */
    public FaceRecognitionFishEyeModel(FishEyeModel delegate, BufferedImage bi) {
        utils.ErrorCheckers.checkNull(delegate, "null delegate given");
        utils.ErrorCheckers.checkNull(bi, "null image given");
        this.delegate = delegate;
        delegate.setImage(bi);
        this.faceRecognitionDelegate = new FaceRecognitionOPENCV();
    }

    /**
     * Constructor that sets the delegate to a {@link FishEyeModel} with no image uploaded and sets the {@link FaceRecognition} delegate.
     */
    public FaceRecognitionFishEyeModel(FaceRecognition faceRecognitionDelegate) {
        utils.ErrorCheckers.checkNull(faceRecognitionDelegate, "null face recognition delegate given");
        this.delegate = new FishEyeModel();
        this.faceRecognitionDelegate = faceRecognitionDelegate;
    }

    /**
     * Constructor that sets the delegate to given {@link SimpleModel} and {@link FaceRecognition} delegate.
     */
    public FaceRecognitionFishEyeModel(FishEyeModel delegate, FaceRecognition faceRecognitionDelegate) {
        utils.ErrorCheckers.checkNull(delegate, "null delegate given");
        utils.ErrorCheckers.checkNull(faceRecognitionDelegate, "null face recognition delegate given");
        this.delegate = delegate;
        this.faceRecognitionDelegate = faceRecognitionDelegate;
    }

    /**
     * Constructor that sets the delegate to given {@link SimpleModel}, image, and {@link FaceRecognition} delegate.
     */
    public FaceRecognitionFishEyeModel(FishEyeModel delegate, BufferedImage bi, FaceRecognition faceRecognitionDelegate) {
        utils.ErrorCheckers.checkNull(delegate, "null delegate given");
        utils.ErrorCheckers.checkNull(bi, "null image given");
        utils.ErrorCheckers.checkNull(faceRecognitionDelegate, "null face recognition delegate given");
        this.delegate = delegate;
        delegate.setImage(bi);
        this.faceRecognitionDelegate = faceRecognitionDelegate;
    }


    /**
     * Automatically sets x y to a random face detected in the image so given x and y are ignored. If no face is detected,
     * applies the filter on the center of the image.
     */
    @Override
    public void processImage(int x, int y) throws IllegalStateException {
        Rectangle rect = faceRecognitionDelegate.findFace(delegate.getImage());
        delegate.processImage(rect.x() + rect.width() / 2, rect.y() + rect.height() / 2);
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
