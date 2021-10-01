package controller;

import java.io.File;
import java.io.IOException;

/**
 * Simple controller that reads a file and tells the model to apply the effect.
 */

public interface SimpleController {
    /**
     * Sets the image in the model
     *
     * @param file the image to be uploaded
     * @throws IOException              if fails to read
     * @throws IllegalArgumentException if given a null file
     */
    void setImage(File file) throws IOException, IllegalArgumentException;

    /**
     * Applys the fisheye effect on the image on the center of the image
     *
     * @throws IllegalStateException if no image has been uploaded yet
     */
    void processImage() throws IllegalStateException;

    /**
     * Applys the fisheye effect on the image and lets you choose the location
     *
     * @param x the x coordinate to apply the fisheye
     * @param y the y coordinate to apply the fisheye
     * @throws IllegalStateException if no image has been uploaded yet
     */
    void processImage(int x, int y) throws IllegalStateException;

    /**
     * Exports the image with the given filename
     *
     * @param filename the name of the file being exported
     * @throws IllegalArgumentException if the given name is null
     * @throws IllegalStateException    if no image has been uploaded yet
     * @throws IOException              if the image fails to be exported
     */
    void outputImage(String filename) throws IllegalArgumentException, IllegalStateException, IOException;
}