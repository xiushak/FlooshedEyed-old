package controller;

import model.SimpleModel;

import java.io.File;
import java.io.IOException;

/**
 * Simple controller that reads a file or folder and tells the model to apply the effect.
 */
public interface MultiSimpleController {
    /**
     * Processes the image by applying the fisheye filter
     *
     * @param index the index of the image to process
     * @param x     the x coordinate for the center of the fisheye effect
     * @param y     the y coordinate for the center of the fisheye effect
     * @throws IllegalStateException if no image has been set
     */
    void processImage(int index, int x, int y) throws IllegalStateException;

    /**
     * Processes all images inside the model and defaults the x and y parameters to the center.
     */
    void processAll();

    /**
     * Adds the image at the end
     *
     * @param file the image to be uploaded
     * @throws IllegalArgumentException if a null image or invalid index is given
     */
    void addImage(File file) throws IllegalArgumentException, IOException;

    /**
     * Adds the image at the given index
     *
     * @param index the index to add the image at
     * @param file  the image to be uploaded
     * @throws IllegalArgumentException if a null image or invalid index is given
     */
    void addImage(int index, File file) throws IllegalArgumentException, IOException;

    /**
     * Adds the image at the given index
     *
     * @param index    the index to add the image at
     * @param file     the image to be uploaded
     * @param delegate the delegate used to determine what type of effect will be applied
     * @throws IllegalArgumentException if a nul
     * @throws IllegalArgumentException if a null image, null delegate, or invalid index is given
     */
    void addImage(int index, File file, SimpleModel delegate) throws IllegalArgumentException, IOException;

    /**
     * Adds a delegate at end
     *
     * @param delegate the delegate used to determine what type of effect will be applied
     * @throws IllegalArgumentException if a null delegate is given
     */
    void addDelegate(SimpleModel delegate) throws IllegalArgumentException;

    /**
     * Adds a delegate at the given index
     *
     * @param index    the index to add the image at
     * @param delegate the delegate used to determine what type of effect will be applied
     * @throws IllegalArgumentException if a null delegate or invalid index is given
     */
    void addDelegate(int index, SimpleModel delegate) throws IllegalArgumentException;

    /**
     * Sets the image at the given index
     *
     * @param index the index to add the image at
     * @param file  the image to be uploaded
     * @throws IllegalArgumentException if a null image or invalid index is given
     */
    void setImage(int index, File file) throws IllegalArgumentException, IOException;

    /**
     * Sets the image at the given index
     *
     * @param index    the index to add the image at
     * @param file     the image to be uploaded
     * @param delegate the delegate used to determine what type of effect will be applied
     * @throws IllegalArgumentException if a null image, null delegate, or invalid index is given
     */
    void setImage(int index, File file, SimpleModel delegate) throws IllegalArgumentException, IOException;

    /**
     * Sets the image at the given index
     *
     * @param index    the index to add the image at
     * @param delegate the delegate used to determine what type of effect will be applied
     * @throws IllegalArgumentException if a null delegate or invalid index is given
     */
    void setDelegate(int index, SimpleModel delegate) throws IllegalArgumentException;

    /**
     * Removes the image at the given index
     *
     * @param index the index of the image to be removed
     * @return the Model that was originally at that index
     * @throws IllegalArgumentException if an invalid index is given
     */
    SimpleModel removeImage(int index) throws IllegalArgumentException;

    /**
     * Gets the number of images in the model
     *
     * @return the number of images
     */
    int size();

    /**
     * Removes all images in the model
     */
    void clear();

    /**
     * Uploads every .png/.jpg image into the model with the facial recognition model
     *
     * @param folder The folder to upload
     * @throws IllegalArgumentException if a null folder is given
     */
    void uploadFolder(File folder) throws IllegalArgumentException, IOException;

    /**
     * Exports the image at the given index with the given filename
     *
     * @param index    the index of the image to be exported
     * @param filename the name of the file being exported
     * @throws IllegalArgumentException if the given name is null
     * @throws IllegalStateException    if no image has been uploaded yet
     * @throws IOException              if the image fails to be exported
     */
    void outputImage(int index, String filename) throws IllegalArgumentException, IllegalStateException, IOException;

    /**
     * Exports all images with the same name into a new folder called "fisheyed". All images that throw an error when
     * being exported will be skipped.
     */
    void outputAll();
}
