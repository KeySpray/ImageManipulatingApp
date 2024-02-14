package controller.imageio;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import model.imagestrategy.BufferedConverter;
import model.imagestrategy.ImageType;

/**
 * A Java class to read/write several image types, implements MyImageIO for abstraction.
 */
public class JpegPngImageIO implements MyImageIO {

  /**
   * The read() method from Java's ImageIO class.
   * @param filepath - the path of the image.
   * @return - Abstract ImageType representing the image.
   * @throws IOException - throws IOException if Java's class runs into errors.
   */
  @Override
  public ImageType read(String filepath) throws IOException {
    BufferedImage image = null;
    try { // attempts to read file
      image = ImageIO.read(new File(filepath));
    }
    catch (IOException e) { // catches exception
      throw new IOException(e.getMessage()); // throws exception with default message
    }
    return new BufferedConverter(image); // runs through BufferedConverter to give proper set/gets.
  }

  /**
   * Java's ImageIO write() method.
   * @param fileName - desired output file name.
   * @param image - the image to be written/saved.
   */
  @Override
  public void write(String fileName, ImageType image) {
    BufferedImage bufferedImage = ((BufferedConverter) image).getBufferedImage();
    String extension = getFileExtension(fileName); // calls helper to get extension from file name
    try {
      ImageIO.write(bufferedImage, extension, new File(fileName)); // writes image
    }
    catch (IOException e) { // catches exceptions from Java's ImageIO write() method
      System.err.println("Error in writing file"); // prints helpful message to error log
    }
  }

  /**
   * A helper method to get the file extension from a file path/file name.
   * @param filePath - the path of the file, or the desired file name.
   * @return - the extension/format of the file in question.
   */
  private String getFileExtension(String filePath) {
    int extensionIndex = filePath.lastIndexOf("."); // finds last period
    // ensures period is not at beginning or end of string
    if (extensionIndex > 0 && extensionIndex < filePath.length() - 1) {
      // returns the extension
      return filePath.substring(extensionIndex + 1);

    }
    throw new IllegalArgumentException("Extension not found.");
  }
}
