package controller;

import model.FaceRecognitionFishEyeModel;
import model.MultiFisheyeModel;
import model.MultiSimpleModel;
import model.SimpleModel;
import utils.ErrorCheckers;
import view.BasicView;
import view.SimpleView;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MultiBasicController implements MultiSimpleController {

    private final MultiSimpleModel model;
    private final List<SimpleView> view;
    private final List<File> filelocations;

    public MultiBasicController() {
        this.model = new MultiFisheyeModel();
        view = new ArrayList<>();
        filelocations = new ArrayList<>();
    }

    @Override
    public void processImage(int index, int x, int y) throws IllegalStateException {
        model.processImage(index, x, y);
    }

    @Override
    public void processAll() {
        model.processAll();
    }

    @Override
    public void addImage(File file) throws IllegalArgumentException, IOException {
        ErrorCheckers.checkNull(file, "file given is null");
        BufferedImage image = ImageIO.read(file);
        filelocations.add(file);
        model.addImage(image);
        view.add(new BasicView(model.getDelegate(model.size() - 1)));
    }

    @Override
    public void addImage(int index, File file) throws IllegalArgumentException, IOException {
        ErrorCheckers.checkNull(file, "file given is null");
        BufferedImage image = ImageIO.read(file);
        filelocations.add(file);
        model.addImage(index, image);
        view.add(index, new BasicView(model.getDelegate(model.size() - 1)));
    }

    @Override
    public void addImage(int index, File file, SimpleModel delegate) throws IllegalArgumentException, IOException {
        ErrorCheckers.checkNull(file, "file given is null");
        ErrorCheckers.checkNull(delegate, "delegate given is null");
        BufferedImage image = ImageIO.read(file);
        delegate.setImage(image);
        filelocations.add(index, file);
        model.addDelegate(index, delegate);
        view.add(index, new BasicView(delegate));
    }

    @Override
    public void addDelegate(SimpleModel delegate) throws IllegalArgumentException {
        ErrorCheckers.checkNull(delegate, "delegate given is null");
        filelocations.add(null);
        model.addDelegate(delegate);
        view.add(new BasicView(delegate));
    }

    @Override
    public void addDelegate(int index, SimpleModel delegate) throws IllegalArgumentException {
        ErrorCheckers.checkNull(delegate, "delegate given is null");
        filelocations.add(index, null);
        model.addDelegate(index, delegate);
        view.add(index, new BasicView(delegate));
    }

    @Override
    public void setImage(int index, File file) throws IllegalArgumentException, IOException {
        ErrorCheckers.checkNull(file, "file given is null");
        BufferedImage image = ImageIO.read(file);
        filelocations.set(index, file);
        model.setImage(index, image);
    }

    @Override
    public void setImage(int index, File file, SimpleModel delegate) throws IllegalArgumentException, IOException {
        ErrorCheckers.checkNull(file, "file given is null");
        ErrorCheckers.checkNull(delegate, "delegate given is null");
        BufferedImage image = ImageIO.read(file);
        delegate.setImage(image);
        filelocations.set(index, file);
        model.setDelegate(index, delegate);
        view.set(index, new BasicView(delegate));
    }

    @Override
    public void setDelegate(int index, SimpleModel delegate) throws IllegalArgumentException {
        ErrorCheckers.checkNull(delegate, "delegate given is null");
        model.setDelegate(index, delegate);
        view.set(index, new BasicView(delegate));
    }

    @Override
    public SimpleModel removeImage(int index) throws IllegalArgumentException {
        filelocations.remove(index);
        view.remove(index);
        return model.removeImage(index);
    }

    @Override
    public int size() {
        return model.size();
    }

    @Override
    public void clear() {
        filelocations.clear();
        model.clear();
        filelocations.clear();
    }

    @Override
    public void uploadFolder(File folder) throws IllegalArgumentException {
        if (folder.isDirectory()) {
            for (File file : folder.listFiles()) {
                uploadFolder(file);
            }
        } else {
            try {
                SimpleModel m = new FaceRecognitionFishEyeModel();
                this.addImage(model.size(), folder, m);
            } catch (IOException e) {
                // do nothing
            }
        }
    }

    @Override
    public void outputImage(int index, String filename) throws IOException {
        view.get(index).outputImage(filename);
    }

    @Override
    public void outputAll() {
        for (int i = 0; i < filelocations.size(); i++) {
            try {
                outputImage(i, "fisheye/" + filelocations.get(i).getPath());
            } catch (IllegalStateException | IOException e) {
                // do nothing
            }
        }
    }
}
