package view;

import model.SimpleModel;
import utils.ErrorCheckers;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

/**
 * A basic implementation of the {@link SimpleView} interface.
 */

public class BasicView implements SimpleView {

    private final SimpleModel model;

    /**
     * Creates a view with the given model
     *
     * @param model the model to be set
     * @throws IllegalArgumentException if a null model is given
     */
    public BasicView(SimpleModel model) throws IllegalArgumentException {
        ErrorCheckers.checkNull(model, "null model given");
        this.model = model;
    }

    @Override
    public void outputImage(String filename) throws IllegalArgumentException, IOException {
        ErrorCheckers.checkNull(filename, "null filename given");
        File outFile = new File(filename);
        if (outFile.getParentFile() != null)
            outFile.getParentFile().mkdirs();
        ImageIO.write(model.getImage(), "png", outFile);
    }
}
