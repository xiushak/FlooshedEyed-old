import controller.BasicController;
import controller.MultiBasicController;
import controller.MultiSimpleController;
import controller.SimpleController;
import model.*;
import view.BasicView;
import view.SimpleView;

import java.io.File;
import java.io.IOException;

public class main {
    public static void main(String[] args) throws IOException {
        useEllipseModel();
        useFisheyeModel();
        useFaceRecognitionModel();
        useFaceRecognitionBrainModel();
        useMultiModel();
    }

    private static void useEllipseModel() throws IOException {
        SimpleModel model = new EllipseFishEyeModel();
        SimpleView view = new BasicView(model);
        SimpleController controller = new BasicController(model);

        controller.setImage(new File("grid.jpg"));
        controller.processImage(model.getWidth() / 2, model.getHeight() / 2);

        view.outputImage("ellipseFisheyeGrid.png");
    }

    private static void useFisheyeModel() throws IOException {
        SimpleModel model = new FishEyeModel();
        SimpleView view = new BasicView(model);
        SimpleController controller = new BasicController(model);

        controller.setImage(new File("grid.jpg"));
        controller.processImage(model.getWidth() / 2, model.getHeight() / 2);

        view.outputImage("FisheyeGrid.png");
    }

    private static void useFaceRecognitionModel() throws IOException {
        SimpleModel model = new FaceRecognitionFishEyeModel();
        SimpleView view = new BasicView(model);
        SimpleController controller = new BasicController(model);

        controller.setImage(new File("face.jpg"));
        controller.processImage(model.getWidth() / 2, model.getHeight() / 2);

        view.outputImage("faceFisheye.png");
    }

    private static void useFaceRecognitionBrainModel() throws IOException {
        SimpleModel model = new FaceRecognitionBigBrainModel();
        SimpleView view = new BasicView(model);
        SimpleController controller = new BasicController(model);

        controller.setImage(new File("face.jpg"));
        controller.processImage(model.getWidth() / 2, model.getHeight() / 2);

        view.outputImage("faceFisheyeBrain.png");
    }

    private static void useMultiModel() throws IOException {
        MultiSimpleController cont = new MultiBasicController();
        cont.uploadFolder(new File("face.jpg"));
        cont.processAll();
        cont.outputAll();
    }
}
