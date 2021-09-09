package controller;

import java.io.File;
import java.io.IOException;

public interface SimpleController {
    void setImage(File filename) throws IOException, IllegalArgumentException;

    void processImage();
}