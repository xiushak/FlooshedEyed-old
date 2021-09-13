package model;

import utils.ErrorCheckers;

import java.awt.image.BufferedImage;

/**
 * A basic implementation of the {@link SimpleModel} interface.
 */
public class FishEyeModel implements SimpleModel {

    private BufferedImage bi;

    /**
     * creates a model with no image
     */
    public FishEyeModel() {
        this.bi = null;
    }


    /**
     * creates a model with an image
     *
     * @param bi the image to be used
     * @throws IllegalArgumentException if a null image is given
     */
    public FishEyeModel(BufferedImage bi) throws IllegalArgumentException {
        ErrorCheckers.checkNull(bi, "image given is null");
        this.bi = bi;
    }

    @Override
    public void setImage(BufferedImage bi) throws IllegalArgumentException {
        ErrorCheckers.checkNull(bi, "null image given");
        this.bi = bi;
    }

    /**
     * implemntation that applies the filter in the center of the image
     */
    @Override
    public void processImage(int x, int y) throws IllegalStateException {
        checkImageSet();
        BufferedImage pbi = new BufferedImage(bi.getWidth(), bi.getHeight(),
                BufferedImage.TYPE_INT_ARGB);
    }

    @Override
    public int getWidth() throws IllegalStateException {
        checkImageSet();
        return bi.getWidth();
    }

    @Override
    public int getHeight() throws IllegalStateException {
        checkImageSet();
        return bi.getHeight();
    }

    @Override
    public BufferedImage getImage() throws IllegalStateException {
        checkImageSet();
        return new BufferedImage(bi.getColorModel(), bi.copyData(null), bi.getColorModel().isAlphaPremultiplied(), null);
    }

    private void checkImageSet() {
        if (this.bi == null) {
            throw new IllegalArgumentException("Image has not been uploaded yet");
        }
    }
}
