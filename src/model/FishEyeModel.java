package model;

import utils.ErrorCheckers;

import java.awt.image.BufferedImage;

public class FishEyeModel implements SimpleModel {

    private BufferedImage bi;

    @Override
    public void setImage(BufferedImage bi) {
        ErrorCheckers.checkNull(bi, "null image given");
        this.bi = bi;
    }

    @Override
    public void processImage() {
        BufferedImage pbi = new BufferedImage(bi.getWidth(), bi.getHeight(),
                BufferedImage.TYPE_INT_ARGB);
    }

    @Override
    public int getWidth() {
        return bi.getWidth();
    }

    @Override
    public int getHeight() {
        return bi.getHeight();
    }

    @Override
    public BufferedImage getImage() {
        return new BufferedImage(bi.getColorModel(), bi.copyData(null), bi.getColorModel().isAlphaPremultiplied(), null);
    }
}
