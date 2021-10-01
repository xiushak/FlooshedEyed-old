package model;

import java.awt.image.BufferedImage;

/**
 * A basic implementation of the {@link SimpleModel} interface.
 */

public class FishEyeModel implements SimpleModel {

    private final SimpleModel delegate;

    /**
     * Creates a model with no image
     */
    public FishEyeModel() {
        delegate = new EllipseFishEyeModel();
    }

    /**
     * creates a model with an image
     *
     * @param bi the image to be used
     * @throws IllegalArgumentException if a null image is given
     */
    public FishEyeModel(BufferedImage bi) throws IllegalArgumentException {
        delegate = new EllipseFishEyeModel(bi);
    }

    @Override
    public void processImage(int x, int y) throws IllegalStateException {
        BufferedImage bi = delegate.getImage();
        int width = bi.getWidth();
        int height = bi.getHeight();
        BufferedImage pbi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        // sets all pixels to black transparent for the background
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                pbi.setRGB(i, j, 0);
            }
        }

        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                double di = i - x;
                double dj = j - y;
                double theta = Math.atan2(dj, di);
                double r = Math.sqrt(di * di + dj * dj);

                double nr = r * r / Math.max(x, y);
                double ni = x + (nr * Math.cos(theta));
                double nj = y + (nr * Math.sin(theta));
                if (ni >= 0 && ni < width && nj >= 0 && nj < height) {
                    pbi.setRGB(i, j, bi.getRGB((int) ni, (int) nj));
                }
            }
        }
        delegate.setImage(pbi);
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
