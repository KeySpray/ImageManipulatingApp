package controller.imageio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import model.imagestrategy.BufferedConverter;
import model.imagestrategy.ImageType;
import model.imagestrategy.PPMImage;
import model.transformationstrategy.RGBPixel;

/**
 * The IO object for a ppm ImageType.
 */
public class PPMImageIO implements MyImageIO {

  /**
   * Read an image file in the PPM format and store the pixels in a 2d array.
   *
   * @param filePath the path of the file.
   * @return - PPM ImageType.
   */
  @Override
  public ImageType read(String filePath) throws FileNotFoundException, IllegalArgumentException {
    if (filePath == null) {
      throw new IllegalArgumentException("File path cannot be null.");
    }
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filePath));
    }

    catch (FileNotFoundException e) {
      throw new FileNotFoundException(filePath + " not found!");
    }

    StringBuilder builder = new StringBuilder();

    // read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    // now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      throw new IllegalArgumentException("Invalid PPM file: plain RAW file should begin with P3");
    }

    int width = sc.nextInt();
    int height = sc.nextInt();
    int maxValue = sc.nextInt();

    ImageType ppm = new PPMImage(height, width, maxValue);

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        ppm.setPixel(i, j, new RGBPixel(r, g, b));
      }
    }

    return ppm;
  }

  /**
   * Writes an image file in the PPM format and saves under given name.
   *
   * @param fileName - the name of the file as stored in database.
   * @param image - the ImageType being saved.
   */
  @Override
  public void write(String fileName, ImageType image) throws FileNotFoundException {
    if (fileName == null || image == null) { // ensures parameters provided
      throw new IllegalArgumentException("fileName or image not found.");
    }

    try { // attempts to write/save new image
      PrintWriter out = new PrintWriter(fileName);

      // writes required ppm format information
      out.println("P3");
      out.println(image.getWidth() + " " + image.getHeight());
      out.println(image.getMaxColorValue());

      // writes pixel information
      for (int i = 0; i < image.getHeight(); i++) {
        for (int j = 0; j < image.getWidth(); j++) {
          RGBPixel pixel = (RGBPixel) image.getPixel(i, j);
          out.println(pixel.getRed() + " " + pixel.getGreen() + " " + pixel.getBlue());
        }
      }

      out.close(); // closes PrintWriter
    }

    catch (FileNotFoundException e) {
      throw new FileNotFoundException("File: " + fileName + "not found!");
    }
  }
}
