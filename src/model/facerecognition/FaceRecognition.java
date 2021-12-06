package model.facerecognition;

import java.awt.image.BufferedImage;

/**
 * Class to find the face from an image
 */

public interface FaceRecognition {
    /**
     * Finds the face of the given image
     *
     * @param bi the image to search
     * @return A {@link Rectangle} that surrounds the face found
     */
    public Rectangle findFace(BufferedImage bi);
}
