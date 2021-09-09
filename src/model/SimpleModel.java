package model;

import java.awt.image.BufferedImage;

public interface SimpleModel {
    void setImage(BufferedImage bi);

    void processImage();

    int getWidth();

    int getHeight();

    BufferedImage getImage();
}

