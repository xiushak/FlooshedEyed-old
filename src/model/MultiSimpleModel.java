package model;

import java.awt.image.BufferedImage;

/**
 * A model to hold and process multiple images together
 */

public interface MultiSimpleModel {
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
     * Gets the width of the image
     *
     * @param index the index of the image
     * @return the width of the image
     * @throws IllegalArgumentException if an invalid index is given
     * @throws IllegalStateException    if no image has been set
     */
    int getWidth(int index) throws IllegalArgumentException, IllegalStateException;

    /**
     * Gets the height of the image
     *
     * @param index the index of the image
     * @return the height of the image
     * @throws IllegalArgumentException if an invalid index is given
     * @throws IllegalStateException    if no image has been set
     */

    int getHeight(int index) throws IllegalArgumentException, IllegalStateException;

    /**
     * Gets an image in the model
     *
     * @param index the index of the image to be copied
     * @return a copy of the image
     * @throws IllegalArgumentException if an invalid index is given
     * @throws IllegalStateException    if no image has been set
     */
    BufferedImage getImage(int index) throws IllegalArgumentException, IllegalStateException;

    /**
     * Gets the delegate in the model
     *
     * @param index the index of the delegate to be returned
     * @return the delegate (Not a copy)
     * @throws IllegalArgumentException if an invalid index is given
     */
    SimpleModel getDelegate(int index) throws IllegalArgumentException;

    /**
     * Adds the image at the end
     *
     * @param bi the image to be added
     * @throws IllegalArgumentException if a null image or invalid index is given
     */
    void addImage(BufferedImage bi) throws IllegalArgumentException;

    /**
     * Adds the image at the given index
     *
     * @param index the index to add the image at
     * @param bi    the image to be added
     * @throws IllegalArgumentException if a null image or invalid index is given
     */
    void addImage(int index, BufferedImage bi) throws IllegalArgumentException;

    /**
     * Adds the image at the given index
     *
     * @param index    the index to add the image at
     * @param bi       the image to be added
     * @param delegate the delegate used to determine what type of effect will be applied
     * @throws IllegalArgumentException if a null image, null delegate, or invalid index is given
     */
    void addImage(int index, BufferedImage bi, SimpleModel delegate) throws IllegalArgumentException;

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
     * @param bi    the image to be added
     * @throws IllegalArgumentException if a null image or invalid index is given
     */
    void setImage(int index, BufferedImage bi) throws IllegalArgumentException;

    /**
     * Sets the image at the given index
     *
     * @param index    the index to add the image at
     * @param bi       the image to be added
     * @param delegate the delegate used to determine what type of effect will be applied
     * @throws IllegalArgumentException if a null image, null delegate, or invalid index is given
     */
    void setImage(int index, BufferedImage bi, SimpleModel delegate) throws IllegalArgumentException;

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
}
