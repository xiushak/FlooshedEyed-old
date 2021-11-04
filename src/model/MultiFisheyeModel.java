package model;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class MultiFisheyeModel implements MultiSimpleModel {

    private List<SimpleModel> models;

    public MultiFisheyeModel() {
        models = new ArrayList<>();
    }

    @Override
    public void processImage(int index, int x, int y) throws IllegalStateException {
        models.get(index).processImage(x, y);
    }

    @Override
    public void processAll() {
        for (SimpleModel model : models) {
            model.processImage(model.getWidth() / 2, model.getHeight() / 2);
        }
    }

    @Override
    public int getWidth(int index) throws IllegalArgumentException, IllegalStateException {
        return models.get(index).getWidth();
    }

    @Override
    public int getHeight(int index) throws IllegalArgumentException, IllegalStateException {
        return models.get(index).getHeight();
    }

    @Override
    public BufferedImage getImage(int index) throws IllegalArgumentException, IllegalStateException {
        return models.get(index).getImage();
    }

    @Override
    public SimpleModel getDelegate(int index) throws IllegalArgumentException, IllegalStateException {
        return models.get(index);
    }

    /**
     * Adds image with the FishEyeModel delegate
     */
    @Override
    public void addImage(BufferedImage bi) throws IllegalArgumentException {
        models.add(new FishEyeModel(bi));
    }

    /**
     * Adds image with the FishEyeModel delegate
     */
    @Override
    public void addImage(int index, BufferedImage bi) throws IllegalArgumentException {
        models.add(index, new FishEyeModel(bi));
    }

    @Override
    public void addImage(int index, BufferedImage bi, SimpleModel delegate) throws IllegalArgumentException {
        delegate.setImage(bi);
        models.add(index, delegate);
    }

    @Override
    public void addDelegate(SimpleModel delegate) throws IllegalArgumentException {
        models.add(delegate);
    }

    @Override
    public void addDelegate(int index, SimpleModel delegate) throws IllegalArgumentException {
        models.add(index, delegate);
    }

    @Override
    public void setImage(int index, BufferedImage bi) throws IllegalArgumentException {
        models.get(index).setImage(bi);
    }

    @Override
    public void setImage(int index, BufferedImage bi, SimpleModel delegate) throws IllegalArgumentException {
        delegate.setImage(bi);
        models.set(index, delegate);
    }

    @Override
    public void setDelegate(int index, SimpleModel delegate) throws IllegalArgumentException {
        delegate.setImage(models.get(index).getImage());
        models.set(index, delegate);
    }

    @Override
    public SimpleModel removeImage(int index) throws IllegalArgumentException {
        return models.remove(index);
    }

    @Override
    public int size() {
        return models.size();
    }

    @Override
    public void clear() {
        models.clear();
    }
}
