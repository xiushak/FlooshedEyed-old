package controller;

import model.SimpleModel;
import utils.ErrorCheckers;
import view.BasicView;
import view.SimpleView;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * A basic implementation of the {@link SimpleController} interface.
 */

public class BasicController implements SimpleController {

    private final SimpleModel model;
    private final SimpleView view;

    public BasicController(SimpleModel model) throws IllegalArgumentException {
        this.model = model;
        this.view = new BasicView(model);
    }

    @Override
    public void setImage(File file) throws IOException, IllegalStateException {
        ErrorCheckers.checkNull(file, "file given is null");
        BufferedImage image = ImageIO.read(file);
        model.setImage(image);
    }

    @Override
    public void processImage() throws IllegalStateException {
        model.processImage(model.getWidth() / 2, model.getHeight() / 2);
    }

    @Override
    public void processImage(int x, int y) throws IllegalStateException {
        model.processImage(x, y);
    }

    @Override
    public void outputImage(String filename) throws IllegalArgumentException, IllegalStateException, IOException {
        ErrorCheckers.checkNull(filename, "null filename given");
        view.outputImage(filename);
    }
}
