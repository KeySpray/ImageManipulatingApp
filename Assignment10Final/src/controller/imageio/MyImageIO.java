package controller.imageio;

import java.io.FileNotFoundException;
import java.io.IOException;

import model.imagestrategy.ImageType;

/**
 * Simple interface to store methods needed by an IO class.
 */
public interface MyImageIO {

  ImageType read(String filename) throws FileNotFoundException, IOException;

  void write(String filename, ImageType image) throws FileNotFoundException;
}
