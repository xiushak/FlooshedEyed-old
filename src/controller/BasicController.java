package controller;

import model.SimpleModel;
import utils.ErrorCheckers;
import view.SimpleView;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BasicController implements SimpleController {

    private SimpleModel model;

    public BasicController(SimpleModel model) {
        this.model = model;
    }

    @Override
    public void setImage(File file) throws IOException, IllegalStateException {
        ErrorCheckers.checkNull(file, "file given is null");
        BufferedImage image = ImageIO.read(file);
        model.setImage(image);
    }

    @Override
    public void processImage() {
        model.processImage();
    }
}
