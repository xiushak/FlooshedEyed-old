import controller.BasicController;
import controller.SimpleController;
import model.FaceRecognitionFishEyeModel;
import model.FishEyeModel;
import model.SimpleModel;
import view.BasicView;
import view.SimpleView;

import java.io.File;
import java.io.IOException;

public class main {
    public static void main(String[] args) throws IOException {
        //SimpleModel model = new EllipseFishEyeModel();
        //SimpleModel model = new FishEyeModel();
        SimpleModel model = new FaceRecognitionFishEyeModel(new FishEyeModel());
        SimpleView view = new BasicView(model);
        SimpleController controller = new BasicController(model);

        controller.setImage(new File("face.jpg"));
        controller.processImage(model.getWidth() / 2, model.getHeight() / 2);

        view.outputImage("faceFisheye.png");
    }
}
