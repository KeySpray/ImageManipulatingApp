package controller;

import controller.imageio.JpegPngImageIO;
import controller.imageio.MyImageIO;
import controller.imageio.PPMImageIO;

/**
 * A simple factory class to create the appropriate ImageIO class to read/write images.
 */
public class ImageIOFactory {

  /**
   * A simple constructor that returns the appropriate ImageIO class.
   * @param filePath - the path of the file being read/written.
   * @return - the appropriate ImageIO class: PPMImageIO or JpegPngImageIO.
   */
  public MyImageIO getImageIO(String filePath) {
    String extension = getFileExtension(filePath);

    switch (extension.toLowerCase()) {
      case "ppm":
        return new PPMImageIO();
      case "jpg":
      case "png":
      case "jpeg":
        return new JpegPngImageIO();
      default:
        throw new IllegalArgumentException("Invalid image format: " + extension);
    }
  }

  /**
   * A simple helper method to extract file extension from file path/file name.
   * @param filePath - the file path or output file name.
   * @return - returns the file extension.
   */
  private String getFileExtension(String filePath) {
    int extensionIndex = filePath.lastIndexOf(".");
    if (extensionIndex > 0 && extensionIndex < filePath.length() - 1) {
      return filePath.substring(extensionIndex + 1);

    }
    throw new IllegalArgumentException("Extension not found.");
  }
}
