package view;

import java.io.IOException;

/**
 * Simple view that exports the image.
 */

public interface SimpleView {
    /**
     * Exports the image with the given filename
     *
     * @param filename the name of the file being exported
     * @throws IllegalArgumentException if the given name is null
     * @throws IllegalStateException    if an image has not been set in the model yet
     * @throws IOException              if the image fails to be exported
     */
    void outputImage(String filename) throws IllegalArgumentException, IllegalStateException, IOException;
}
