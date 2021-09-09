package view;

import model.SimpleModel;
import utils.ErrorCheckers;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class BasicView implements SimpleView {

    public SimpleModel model;


    public BasicView(SimpleModel model) throws IllegalArgumentException {
        ErrorCheckers.checkNull(model, "null model given");
        this.model = model;
    }

    @Override
    public void outputImage(String filename) throws IOException {
        ImageIO.write(model.getImage(), "png", new File(filename));
    }
}
