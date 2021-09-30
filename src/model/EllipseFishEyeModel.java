package model;

import utils.ErrorCheckers;

import java.awt.image.BufferedImage;

/**
 * A basic implementation of the {@link SimpleModel} interface.This one just maps each
 * pixel of the image onto a circle/oval to create a fisheye effect.
 */
public class EllipseFishEyeModel implements SimpleModel {

    private BufferedImage bi;

    /**
     * creates a model with no image
     */
    public EllipseFishEyeModel() {
        this.bi = null;
    }


    /**
     * creates a model with an image
     *
     * @param bi the image to be used
     * @throws IllegalArgumentException if a null image is given
     */
    public EllipseFishEyeModel(BufferedImage bi) throws IllegalArgumentException {
        ErrorCheckers.checkNull(bi, "image given is null");
        this.bi = bi;
    }

    /**
     * This implementation applies the filter on the center of the image, x and y inputs are not used.
     */
    @Override
    public void processImage(int x, int y) throws IllegalStateException {
        checkImageSet();
        double width = bi.getWidth();
        double height = bi.getHeight();
        BufferedImage pbi = new BufferedImage((int) width, (int) height, BufferedImage.TYPE_INT_ARGB);
        // sets all pixels to black transparent for the background
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                pbi.setRGB(i, j, 0);
            }
        }

        // between 0 and 1, lower the value the more zoomed in it is
        double scale = 1;
        // (x,y) on graph = (i, j)
        for (int j = 0; j < height; j++) {
            // normalize j to -1 to 1
            double nj = ((2 * j) / height) - 1;
            double nj2 = nj * nj;
            for (int i = 0; i < width; i++) {
                // normalize i to -1 to 1
                double ni = ((2 * i) / width) - 1;
                double ni2 = ni * ni;
                // radius to center for polar coords
                double r = Math.sqrt(ni2 + nj2);
                if (0.0 <= r && r <= 1.0) {
                    // find new radius to center
                    double nr = Math.sqrt(1.0 - r * r);
                    nr = (r + (1.0 - nr)) / 2.0 * scale;
                    if (nr <= 1.0) {
                        // changes new values to cartesian coords
                        double theta = Math.atan2(nj, ni);
                        double nin = nr * Math.cos(theta);
                        double njn = nr * Math.sin(theta);
                        int x2 = (int) (((nin + 1) * width) / 2.0);
                        int y2 = (int) (((njn + 1) * height) / 2.0);
                        if (y2 >= 0 && y2 < height && x2 >= 0 && x2 < width) {
                            pbi.setRGB(i, j, bi.getRGB(x2, y2));
                        }
                    }
                }
            }
        }
        this.bi = pbi;
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

    @Override
    public void setImage(BufferedImage bi) throws IllegalArgumentException {
        ErrorCheckers.checkNull(bi, "null image given");
        this.bi = bi;
    }

    private void checkImageSet() {
        if (this.bi == null) {
            throw new IllegalArgumentException("Image has not been uploaded yet");
        }
    }
}
