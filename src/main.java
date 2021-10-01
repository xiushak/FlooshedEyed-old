import controller.BasicController;
import controller.SimpleController;
import model.EllipseFishEyeModel;
import model.FishEyeModel;
import model.SimpleModel;
import view.BasicView;
import view.SimpleView;

import java.io.File;
import java.io.IOException;

public class main {
    public static void main(String[] args) throws IOException {
        //SimpleModel model = new EllipseFishEyeModel();
        SimpleModel model = new FishEyeModel();
        SimpleView view = new BasicView(model);
        SimpleController controller = new BasicController(model);

        controller.setImage(new File("grid.jpg"));
        controller.processImage();

        view.outputImage("FisheyeGrid.png");
    }
}
