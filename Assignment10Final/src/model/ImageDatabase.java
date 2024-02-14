package model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import controller.ImageIOFactory;
import controller.imageio.MyImageIO;
import model.imagestrategy.BufferedConverter;
import model.imagestrategy.ImageType;
import model.transformationstrategy.TransformationStrategy;

/**
 * A database class to load, transform, and save ImageTypes.
 */
public class ImageDatabase {

  private final Map<String, ImageType> imageDB;

  /**
   * A simple constructor for the ImageType database.
   */
  public ImageDatabase() {
    this.imageDB = new HashMap<>(); // Creates and assigns map to store images by given name.
  }

  /**
   * The database method to read and add the image to the hashmap.
   * @param fileName - the desired name under which to store the image.
   * @param filePath - the relative/local path to the image.
   */
  public void addImage(String filePath, String fileName) throws NullPointerException {
    if (filePath == null || fileName == null) {
      throw new IllegalArgumentException("File path and file name must not be null.");
    }

    else {
      try {
        MyImageIO io = new ImageIOFactory().getImageIO(filePath);
        ImageType newImage = io.read(filePath); // Attempts to read file

        if (newImage == null) { // checks if ImageType not returned
          System.out.println("Failed to add image to database: could not read file: " + filePath);
          return;
        }

        imageDB.put(fileName, newImage); // Puts the new image in the map under given name.
      }

      catch (IllegalArgumentException | IOException e) { // catches exceptions from IO
        System.out.println("Failed to add image to database: " + e.getMessage());
      }
    }
  }

  /**
   * The database method to return an image from the database. Enables applying transformation.
   * @param fileName - the 'nickname' or map key under which the image was stored.
   */
  public ImageType getImage(String fileName) {
    if (fileName == null) {
      return null;
    }

    return imageDB.get(fileName); // returns image from database that matches key
  }

  /**
   * The database method to apply a transformation to an image from the database.
   *
   * @param imageKey       - the 'nickname' or map key under which the image was stored.
   * @param transformation - the desired transformation method to be applied.
   * @throws IllegalStateException    - if the transformation does not get applied.
   * @throws IllegalArgumentException - if the file name is not found in the database.
   */
  public void applyTransformation(String imageKey, TransformationStrategy transformation)
          throws IllegalStateException, IllegalArgumentException {
    ImageType image = getImage(imageKey);

    if (image == null || transformation == null) { // ensures image not null
      throw new IllegalArgumentException("No file in database called: " + imageKey);
    }

    try {
      // attempts to apply transformation and overwrites original value at key index
      imageDB.put(imageKey, image.applyTransformation(transformation));
    }

    catch (Exception e) {
      throw new IllegalStateException(e.getMessage());
    }
  }

  /**
   * The database method to save an image from the database.
   * @param imageKey - the 'nickname' or map key under which the image was stored.
   * @param fileName - the desired output name.
   * @throws IllegalArgumentException - if the imageKey does not map to a record in the database.
   */
  public void saveImage(String imageKey, String fileName) throws IllegalArgumentException {
    ImageType image = getImage(imageKey);
    if (image == null) { // ensures image not null
      throw new IllegalArgumentException("No file in database called: " + imageKey);
    }

    if (fileName == null) { // ensures output name not null
      throw new IllegalArgumentException("Output name required.");
    }

    try {
      ImageType saveImage;
      if (image instanceof BufferedConverter) { // checks if image put through converter
        saveImage = image; // keeps image type
      }
      else {
        saveImage = new BufferedConverter(image); // converts image if not already done so
      }

      MyImageIO io = new ImageIOFactory().getImageIO(fileName); // creates proper ImageIO
      io.write(fileName, saveImage); // attempts to write/save modified image
    }

    catch (FileNotFoundException e) {
      System.out.println("File not found: " + fileName);
    }
  }
}
