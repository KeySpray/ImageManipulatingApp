package model.imagestrategy;

import model.transformationstrategy.Pixel;
import model.transformationstrategy.RGBPixel;
import model.transformationstrategy.TransformationStrategy;

/**
 * A class that stores methods for ppm ImageType and implements ImageType interface.
 */
public class PPMImage implements ImageType {

  private final Pixel[][] pixels;
  private final int height;
  private final int width;
  private final int maxColorValue;
  public String fileType;

  /**
   * A constructor class for a ppm ImageType.
   *
   * @param height        - the height of the image in pixels.
   * @param width         - the width of the image in pixels.
   * @param maxColorValue - the max color value of the pixels of this image type.
   */
  public PPMImage(int height, int width, int maxColorValue) {
    if (height < 0 || width < 0 || maxColorValue < 0) {
      throw new IllegalArgumentException("Image parameters cannot be less than 0.");
    }

    this.height = height;
    this.width = width;
    this.maxColorValue = maxColorValue;
    this.pixels = new Pixel[height][width];
    fileType = "PPM";
  }

  /**
   * The method to fetch a pixel at a particular row and column.
   *
   * @param row - the row at which the desired pixel sits.
   * @param col - the column at which the desired pixel sits.
   * @return - Pixel
   */
  @Override
  public Pixel getPixel(int row, int col) {
    return pixels[row][col];
  }

  /**
   * A method to set/place the pixel at a given row/column in the 2d pixel array.
   *
   * @param row - the row at which the pixel will sit.
   * @param col - the column at which the pixel will sit.
   */
  @Override
  public void setPixel(int row, int col, Pixel pixel) {
    pixels[row][col] = pixel;
  }

  /**
   * A method to return the height of the ppm image.
   *
   * @return - the height parameter of the image.
   */
  @Override
  public int getHeight() {
    return this.height;
  }

  /**
   * A method to return the width of the ppm image.
   *
   * @return - the width parameter of the image.
   */
  @Override
  public int getWidth() {
    return this.width;
  }

  /**
   * A method to return the max color value of a pixel of the ppm image.
   *
   * @return - the max color value of the pixels in the image.
   */
  @Override
  public int getMaxColorValue() {
    return this.maxColorValue;
  }

  /**
   * A method to apply the given image transformation.
   *
   * @param transformation - the desired transformation strategy.
   */
  @Override
  public ImageType applyTransformation(TransformationStrategy transformation) {
    return transformation.transform(this);
  }

  @Override
  public ImageType clone() {
    PPMImage imageCopy = new PPMImage(this.getHeight(), this.getWidth(), this.getMaxColorValue());
    for (int i = 0; i < this.getHeight(); i++) {
      for (int j = 0; j < this.getWidth(); j++) {
        RGBPixel pixel = (RGBPixel) this.getPixel(i, j);
        imageCopy.setPixel(i, j, pixel);
      }
    }
    return imageCopy;
  }
}
